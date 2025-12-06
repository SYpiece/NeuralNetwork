package util.calculator;

import util.math.function.Function;
import util.math.matrix.Matrices;
import util.math.matrix.Matrix;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class MatrixMultipleCalculator implements MatrixCalculator {

    private static final ThreadPoolExecutor executor = new ThreadPoolExecutor(
            Runtime.getRuntime().availableProcessors(),
            Runtime.getRuntime().availableProcessors(),
            1,
            TimeUnit.MINUTES,
            new LinkedBlockingQueue<>(),
            new ThreadFactory() {
                private final ThreadGroup threadGroup;
                private final AtomicInteger threadNumber = new AtomicInteger(1);

                {
                    SecurityManager securityManager = System.getSecurityManager();
                    threadGroup = securityManager != null ? securityManager.getThreadGroup() : Thread.currentThread().getThreadGroup();
                }

                @Override
                public Thread newThread(Runnable r) {
                    Thread thread = new Thread(threadGroup, r, "Matrix.java Calculator Thread-" + threadNumber.getAndIncrement());
                    if (thread.isDaemon()) {
                        thread.setDaemon(false);
                    }
                    if (thread.getPriority() != Thread.NORM_PRIORITY) {
                        thread.setPriority(Thread.NORM_PRIORITY);
                    }
                    return thread;
                }
            });

    private int getBlockRows(Matrix m) {
        return Math.max(1, m.getRows() / Runtime.getRuntime().availableProcessors());
    }

    @Override
    public Matrix transpose(Matrix m) {
        Matrix result = Matrices.createMatrix(m.getColumns(), m.getRows());
        try {
            List<Future<?>> futures = new LinkedList<>();
            int rows = getBlockRows(result);
            for (int i = 0; i < result.getRows(); i += rows) {
                final int start = i, end = Math.min(i + rows, result.getRows());
                futures.add(executor.submit(() -> {
                    for (int j = start; j < end; j++) {
                        for (int k = 0; k < result.getColumns(); k++) {
                            result.set(j, k, m.get(k, j));
                        }
                    }
                }));
            }
            for (Future<?> future : futures) {
                future.get();
            }
        } catch (ExecutionException | InterruptedException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    @Override
    public Matrix add(Matrix m1, Matrix m2) {
        if (m1.getRows() != m2.getRows() || m1.getColumns() != m2.getColumns()) {
            throw new IllegalArgumentException("Matrix.java size mismatch");
        }
        Matrix result = Matrices.createMatrix(m1.getRows(), m1.getColumns());
        try {
            List<Future<?>> futures = new LinkedList<>();
            int rows = getBlockRows(result);
            for (int i = 0; i < result.getRows(); i += rows) {
                final int start = i, end = Math.min(i + rows, result.getRows());
                futures.add(executor.submit(() -> {
                    for (int j = start; j < end; j++) {
                        for (int k = 0; k < result.getColumns(); k++) {
                            result.set(j, k, m1.get(j, k) + m2.get(j, k));
                        }
                    }
                }));
            }
            for (Future<?> future : futures) {
                future.get();
            }
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    @Override
    public Matrix subtract(Matrix m1, Matrix m2) {
        if (m1.getRows() != m2.getRows() || m1.getColumns() != m2.getColumns()) {
            throw new IllegalArgumentException("Matrix.java size mismatch");
        }
        Matrix result = Matrices.createMatrix(m1.getRows(), m1.getColumns());
        try {
            List<Future<?>> futures = new LinkedList<>();
            int rows = getBlockRows(result);
            for (int i = 0; i < result.getRows(); i += rows) {
                final int start = i, end = Math.min(i + rows, result.getRows());
                futures.add(executor.submit(() -> {
                    for (int j = start; j < end; j++) {
                        for (int k = 0; k < result.getColumns(); k++) {
                            result.set(j, k, m1.get(j, k) - m2.get(j, k));
                        }
                    }
                }));
            }
            for (Future<?> future : futures) {
                future.get();
            }
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    @Override
    public Matrix multiply(Matrix m, double scalar) {
        Matrix result = Matrices.createMatrix(m.getRows(), m.getColumns());
        try {
            List<Future<?>> futures = new LinkedList<>();
            int rows = getBlockRows(result);
            for (int i = 0; i < result.getRows(); i += rows) {
                final int start = i, end = Math.min(i + rows, result.getRows());
                futures.add(executor.submit(() -> {
                    for (int j = start; j < end; j++) {
                        for (int k = 0; k < result.getColumns(); k++) {
                            result.set(j, k, m.get(j, k) * scalar);
                       }
                    }
                }));
            }
            for (Future<?> future : futures) {
                future.get();
            }
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    @Override
    public Matrix multiply(Matrix m1, Matrix m2) {
        if (m1.getColumns() != m2.getRows()) {
            throw new IllegalArgumentException("Matrix.java size mismatch");
        }
        Matrix result = Matrices.createMatrix(m1.getRows(), m2.getColumns());
        try {
            List<Future<?>> futures = new LinkedList<>();
            int rows = getBlockRows(result);
            for (int i = 0; i < result.getRows(); i += rows) {
                final int start = i, end = Math.min(i + rows, result.getRows());
                futures.add(executor.submit(() -> {
                    for (int j = start; j < end; j++) {
                        for (int k = 0; k < result.getColumns(); k++) {
                            double sum = 0;
                            for (int l = 0; l < m1.getColumns(); l++) {
                                sum += m1.get(j, l) * m2.get(l, k);
                            }
                            result.set(j, k, sum);
                        }
                    }
                }));
            }
            for (Future<?> future : futures) {
                future.get();
            }
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    @Override
    public Matrix dot(Matrix m1, Matrix m2) {
        if (m1.getRows() != m2.getRows() || m1.getColumns() != m2.getColumns()) {
            throw new IllegalArgumentException("Matrix.java size mismatch");
        }
        Matrix result = Matrices.createMatrix(m1.getRows(), m1.getColumns());
        try {
            List<Future<?>> futures = new LinkedList<>();
            int rows = getBlockRows(result);
            for (int i = 0; i < result.getRows(); i += rows) {
                final int start = i, end = Math.min(i + rows, result.getRows());
                futures.add(executor.submit(() -> {
                    for (int j = start; j < end; j++) {
                        for (int k = 0; k < result.getColumns(); k++) {
                            result.set(j, k, m1.get(j, k) * m2.get(j, k));
                        }
                    }
                }));
            }
            for (Future<?> future : futures) {
                future.get();
            }
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    @Override
    public Matrix function(Matrix m, Function f) {
        Matrix result = Matrices.createMatrix(m.getRows(), m.getColumns());
        try {
            List<Future<?>> futures = new LinkedList<>();
            int rows = getBlockRows(result);
            for (int i = 0; i < result.getRows(); i += rows) {
                final int start = i, end = Math.min(i + rows, result.getRows());
                futures.add(executor.submit(() -> {
                    for (int j = start; j < end; j++) {
                        for (int k = 0; k < result.getColumns(); k++) {
                            result.set(j, k, f.calculate(m.get(j, k)));
                        }
                    }
                }));
            }
            for (Future<?> future : futures) {
                future.get();
            }
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
        return result;
    }
}
