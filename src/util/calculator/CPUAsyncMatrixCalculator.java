package util.calculator;

import util.math.function.Function;
import util.math.matrix.Matrix;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicIntegerArray;
import java.util.concurrent.atomic.AtomicReference;

public class CPUAsyncMatrixCalculator extends AbstractAsyncMatrixCalculator {
    static final int THREADS_COUNT = Runtime.getRuntime().availableProcessors() * 2;

    static final Thread[] threads = new Thread[THREADS_COUNT];

    static CalculationTask taskHead = new CalculationTask(), reusedTaskHead;
    static final Object taskLock = new Object();

    static {
        ThreadGroup group = new ThreadGroup("CPU Matrix.java Calculator Threads");
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new CalculationThread(group, i, taskHead);
        }
    }

    @Override
    public Future<Matrix> transposeAsync(Matrix source, Matrix result) {
        CalculationTask task = getNextTask();
        task.matrix1 = source;
        task.result = result;
        task.taskType = CalculationTask.TRANSPOSE_TASK;
        return submitTask(task);
    }

    @Override
    public Future<Matrix> addAsync(Matrix source1, Matrix source2, Matrix result) {
        CalculationTask task = getNextTask();
        task.matrix1 = source1;
        task.matrix2 = source2;
        task.result = result;
        task.taskType = CalculationTask.ADD_TASK;
        return submitTask(task);
    }

    @Override
    public Future<Matrix> subtractAsync(Matrix source1, Matrix source2, Matrix result) {
        CalculationTask task = getNextTask();
        task.matrix1 = source1;
        task.matrix2 = source2;
        task.result = result;
        task.taskType = CalculationTask.SUBTRACT_TASK;
        return submitTask(task);
    }

    @Override
    public Future<Matrix> multiplyAsync(Matrix source, double scalar, Matrix result) {
        CalculationTask task = getNextTask();
        task.matrix1 = source;
        task.scalar = scalar;
        task.result = result;
        task.taskType = CalculationTask.MULTIPLY_SCALAR_TASK;
        return submitTask(task);
    }

    @Override
    public Future<Matrix> multiplyAsync(Matrix source1, Matrix source2, Matrix result) {
        CalculationTask task = getNextTask();
        task.matrix1 = source1;
        task.matrix2 = source2;
        task.result = result;
        task.taskType = CalculationTask.MULTIPLY_TASK;
        return submitTask(task);
    }

    @Override
    public Future<Matrix> dotAsync(Matrix source1, Matrix source2, Matrix result) {
        CalculationTask task = getNextTask();
        task.matrix1 = source1;
        task.matrix2 = source2;
        task.result = result;
        task.taskType = CalculationTask.DOT_TASK;
        return submitTask(task);
    }

    @Override
    public Future<Matrix> functionAsync(Matrix source, Function transformation, Matrix result) {
        CalculationTask task = getNextTask();
        task.matrix1 = source;
        task.transformation = transformation;
        task.result = result;
        task.taskType = CalculationTask.FUNCTION_TASK;
        return submitTask(task);
    }

    private CalculationTask getNextTask() {
        CalculationTask nextTask;
        synchronized (taskLock) {
            nextTask = taskHead.nextTask;
            if (nextTask == null) {
                taskHead.nextTask = new CalculationTask();
            }
            nextTask = taskHead = taskHead.nextTask;
            if (taskHead == reusedTaskHead) {
                reusedTaskHead = null;
            }
        }
        return nextTask;
    }

    private Future<Matrix> submitTask(CalculationTask task) {
        return task;
    }

    public void reuseFuture(Future<Matrix> future) {
        if (taskHead != future && future.isDone() && future instanceof CalculationTask) {
            CalculationTask reusedTask = (CalculationTask) future;
            if (reusedTask.taskType == CalculationTask.NOP_TASK) {
                throw new IllegalArgumentException("Future is not reusable");
            }
            reusedTask.clean();
            synchronized (taskLock) {
                if (reusedTaskHead == null) {
                    reusedTaskHead = reusedTask;
                    taskHead.nextTask = reusedTask;
                } else {
                    reusedTaskHead.nextTask = reusedTask;
                    reusedTaskHead = reusedTask;
                }
            }
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
                    while (task.nextTask == null || task.nextTask.taskType == CalculationTask.NOP_TASK) {
                        Thread.yield();
                    }
                    task = task.nextTask;
                    switch (task.taskType) {

                    }
                    task.completedFlags.set(id, 1);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static class CalculationTask implements Future<Matrix> {
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
        volatile boolean isDone = false;
        volatile double scalar;
        volatile Function transformation;
        volatile Matrix matrix1, matrix2, result;
        volatile CalculationTask nextTask;
        final AtomicIntegerArray completedFlags = new AtomicIntegerArray(THREADS_COUNT);

        void clean() {
            taskType = CalculationTask.NOP_TASK;
            nextTask = null;
            isDone = false;
            for (int i = 0; i < THREADS_COUNT; i++) {
                completedFlags.set(i, 0);
            }
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
            if (isDone) {
                return true;
            }
            for (int i = 0; i < THREADS_COUNT; i++) {
                if (completedFlags.get(i) == 0)
                    return false;
            }
            isDone = true;
            return true;
        }

        @Override
        public Matrix get() throws InterruptedException, ExecutionException {
            if (isDone) {
                return result;
            }
            for (int i = 0; i < THREADS_COUNT; i++) {
                while (completedFlags.get(i) == 0) {
                    Thread.yield();
                }
            }
            isDone = true;
            return result;
        }
        @Override
        public Matrix get(long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
            long nanos = unit.toNanos(timeout);
            long deadline = System.nanoTime() + nanos;
            for (int i = 0; i < THREADS_COUNT; i++) {
                while (completedFlags.get(i) == 0) {
                    if (System.nanoTime() >= deadline) {
                        throw new TimeoutException();
                    }
                    Thread.yield();
                }
            }
            isDone = true;
            return result;
        }
    }
}
