package util.calculator;

import util.math.function.FloatFunction;
import util.math.function.Function;
import util.math.matrix.FloatMatrix;
import util.math.matrix.Matrices;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public class MultithreadingFloatMatrixCalculator extends AbstractAsyncMatrixCalculator<FloatMatrix, FloatFunction> {
    static final int THREADS_COUNT = Runtime.getRuntime().availableProcessors() * 2;

    static final Thread[] threads = new Thread[THREADS_COUNT];

//    static CalculationTask taskHead = new CalculationTask(), reusedTaskHead;
//    static final Object taskLock = new Object();

    static AtomicReference<CalculationTask> taskHead = new AtomicReference<>(), reusedTaskHead = new AtomicReference<>();

    static {
        CalculationTask task = new CalculationTask();
        taskHead.set(task);
        reusedTaskHead.set(task);

        ThreadGroup group = new ThreadGroup("CPU Matrix.java Calculator Threads");
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new CalculationThread(group, i, taskHead.get());
        }
    }

    @Override
    protected FloatMatrix createMatrix(int rows, int columns) {
        return Matrices.createFloatMatrix(rows, columns);
    }


    @Override
    public Future<FloatMatrix> transposeAsync(FloatMatrix source, FloatMatrix result) {
        CalculationTask task = generateTask();
        task.matrix1 = source;
        task.result = result;
        task.taskType = CalculationTask.TRANSPOSE_TASK;
        return submitTask(task);
    }

    @Override
    public Future<FloatMatrix> addAsync(FloatMatrix source1, FloatMatrix source2, FloatMatrix result) {
        CalculationTask task = generateTask();
        task.matrix1 = source1;
        task.matrix2 = source2;
        task.result = result;
        task.taskType = CalculationTask.ADD_TASK;
        return submitTask(task);
    }

    @Override
    public Future<FloatMatrix> subtractAsync(FloatMatrix source1, FloatMatrix source2, FloatMatrix result) {
        CalculationTask task = generateTask();
        task.matrix1 = source1;
        task.matrix2 = source2;
        task.result = result;
        task.taskType = CalculationTask.SUBTRACT_TASK;
        return submitTask(task);
    }

    @Override
    public Future<FloatMatrix> multiplyAsync(FloatMatrix source, double scalar, FloatMatrix result) {
        CalculationTask task = generateTask();
        task.matrix1 = source;
        task.scalar = scalar;
        task.result = result;
        task.taskType = CalculationTask.MULTIPLY_SCALAR_TASK;
        return submitTask(task);
    }

    @Override
    public Future<FloatMatrix> multiplyAsync(FloatMatrix source1, FloatMatrix source2, FloatMatrix result) {
        CalculationTask task = generateTask();
        task.matrix1 = source1;
        task.matrix2 = source2;
        task.result = result;
        task.taskType = CalculationTask.MULTIPLY_TASK;
        return submitTask(task);
    }

    @Override
    public Future<FloatMatrix> dotAsync(FloatMatrix source1, FloatMatrix source2, FloatMatrix result) {
        CalculationTask task = generateTask();
        task.matrix1 = source1;
        task.matrix2 = source2;
        task.result = result;
        task.taskType = CalculationTask.DOT_TASK;
        return submitTask(task);
    }

    @Override
    public Future<FloatMatrix> functionAsync(FloatMatrix source, FloatFunction transformation, FloatMatrix result) {
        CalculationTask task = generateTask();
        task.matrix1 = source;
        task.transformation = transformation;
        task.result = result;
        task.taskType = CalculationTask.FUNCTION_TASK;
        return submitTask(task);
    }

    private CalculationTask generateTask() {
        CalculationTask currentTask, currentNextTask;
        do {
            currentTask = taskHead.get();
            currentNextTask = currentTask.nextTask;
            if (currentNextTask == null) {
                generateReusedTask();
            } else if (taskHead.compareAndSet(currentTask, currentNextTask)) {
                return currentTask;
            }
        } while(true);
    }

    private CalculationTask generateReusedTask() {
        CalculationTask currentReusedTask, nextReusedTask = new CalculationTask();
        do {
            currentReusedTask = reusedTaskHead.get();
        } while (!reusedTaskHead.compareAndSet(currentReusedTask, nextReusedTask));
        currentReusedTask.nextTask = nextReusedTask;
        return currentReusedTask;
    }

    private CalculationTask reuseFinishedTask(CalculationTask nextReusedTask) {
        CalculationTask currentReusedTask;
        do {
            currentReusedTask = reusedTaskHead.get();
        } while (!reusedTaskHead.compareAndSet(currentReusedTask, nextReusedTask));
        currentReusedTask.nextTask = nextReusedTask;
        return currentReusedTask;
    }

    private Future<FloatMatrix> submitTask(CalculationTask task) {
        return task;
    }

    public void reuseTask(Future<FloatMatrix> future) {
        if (taskHead != future && future.isDone() && future instanceof CalculationTask) {
            CalculationTask reusedTask = (CalculationTask) future;
            if (reusedTask.taskType == CalculationTask.NOP_TASK) {
                throw new IllegalArgumentException("Future is not reusable");
            }
            reusedTask.clean();
            reuseFinishedTask(reusedTask);
        } else {
            throw new IllegalArgumentException("Future is not a CalculationTask");
        }
    }

    private static class CalculationThread extends Thread {
        final int id;
        CalculationTask task;

        public CalculationThread(ThreadGroup group, int id, CalculationTask task) {
            super(group, "CPU Matrix.java Calculator Thread " + id);
            setPriority(Thread.MAX_PRIORITY);
            this.id = id;
            this.task = task;
        }

        @Override
        public void run() {
            while (true) {
                try {
                    while (task.taskType == CalculationTask.NOP_TASK) {
                        Thread.yield();
                    }
                    switch (task.taskType) {

                    }
                    task.finishedCounter.incrementAndGet();
                    while (task.nextTask == null) {
                        Thread.yield();
                    }
                    task = task.nextTask;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static class CalculationTask implements Future<FloatMatrix> {
        public static final int
                NOP_TASK = 0,
                TRANSPOSE_TASK = 1,
                ADD_TASK = 2,
                SUBTRACT_TASK = 3,
                MULTIPLY_SCALAR_TASK = 4,
                MULTIPLY_TASK = 5,
                DOT_TASK = 6,
                FUNCTION_TASK = 7,
                TASK_TYPE_COUNT = 8;

        volatile int taskType = NOP_TASK;
        volatile double scalar;
        volatile Function transformation;
        volatile FloatMatrix matrix1, matrix2, result;
        volatile CalculationTask nextTask;
        final AtomicInteger finishedCounter = new AtomicInteger(0);

        void clean() {
            taskType = CalculationTask.NOP_TASK;
            nextTask = null;
            finishedCounter.set(0);
        }

        @Override
        public boolean cancel(boolean mayInterruptIfRunning) {
            return false;
        }

        @Override
        public boolean isCancelled() {
            return false;
        }

        @Override
        public boolean isDone() {
            return finishedCounter.get() == THREADS_COUNT;
        }

        @Override
        public FloatMatrix get() throws InterruptedException, ExecutionException {
            while (finishedCounter.get() != THREADS_COUNT) {
                Thread.yield();
            }
            return result;
        }
        @Override
        public FloatMatrix get(long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
            if (finishedCounter.get() == THREADS_COUNT) {
                return result;
            }
            long nanos = unit.toNanos(timeout);
            long deadline = System.nanoTime() + nanos;
            while (finishedCounter.get() != THREADS_COUNT) {
                if (System.nanoTime() >= deadline) {
                    throw new TimeoutException();
                }
                Thread.yield();
            }
            return result;
        }
    }
}
