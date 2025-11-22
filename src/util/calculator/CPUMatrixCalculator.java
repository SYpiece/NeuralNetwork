package util.calculator;

import util.math.function.Function;
import util.math.matrix.Matrix;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicIntegerArray;

public class CPUMatrixCalculator implements MatrixCalculator {
    static final int THREADS_COUNT = Runtime.getRuntime().availableProcessors() * 2;

    static final Thread[] threads = new Thread[THREADS_COUNT];

    static {
        for (int i = 0; i < threads.length; i++) {
            int finalI = i;
            threads[i] = new Thread(() -> threadFunction(finalI));
        }
    }

    private static void threadFunction(int id) {

    }

    private volatile CalculationTask nextTask, nextReusedTask;


    public CPUMatrixCalculator() {
    }

    @Override
    public Matrix transpose(Matrix m) {
        return null;
    }

    @Override
    public Matrix add(Matrix m1, Matrix m2) {
        return null;
    }

    @Override
    public Matrix subtract(Matrix m1, Matrix m2) {
        return null;
    }

    @Override
    public Matrix multiply(Matrix m, double scalar) {
        return null;
    }

    @Override
    public Matrix multiply(Matrix m1, Matrix m2) {
        return null;
    }

    @Override
    public Matrix dot(Matrix m1, Matrix m2) {
        return null;
    }

    @Override
    public Matrix function(Matrix m, Function f) {
        return null;
    }

    private static class CalculationTask implements Future<Matrix> {
        public final int
                NOP_TASK = 0,
                TRANSPOSE_TASK = 1,
                ADD_TASK = 2,
                SUBTRACT_TASK = 3,
                MULTIPLY_SCALAR_TASK = 4,
                MULTIPLY_TASK = 5,
                DOT_TASK = 6,
                FUNCTION_TASK = 7;

        final int taskType;
        volatile boolean isDone = false;
        final Matrix argument1, argument2, result;
        final AtomicIntegerArray completedFlags = new AtomicIntegerArray(THREADS_COUNT);

        CalculationTask(int taskType, Matrix argument1, Matrix argument2, Matrix result, int threadCount) {
            this.taskType = taskType;
            this.argument1 = argument1;
            this.argument2 = argument2;
            this.result = result;
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
