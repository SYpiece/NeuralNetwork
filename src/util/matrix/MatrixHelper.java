package util.matrix;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

class MatrixHelper {
    static final int LIMIT = Runtime.getRuntime().availableProcessors() * 1000;

    static ExecutorService executor = new ThreadPoolExecutor(
            Runtime.getRuntime().availableProcessors(),
            Runtime.getRuntime().availableProcessors(),
            1,
            TimeUnit.SECONDS,
            new LinkedBlockingQueue<Runnable>(),
            r -> new Thread(r, "Matrix Helper Thread"));

    static ComputingMode computingMode = ComputingMode.Auto;

    static Matrix transpose(Matrix m) {
        switch (computingMode) {
            case Single: return transposeSingly(m);
            case Multiple: return transposeMultiply(m);
            default: return m.size() < LIMIT ? transposeSingly(m) : transposeMultiply(m);
        }
    }

    private static Matrix transposeSingly(Matrix m) {
        double[][] matrix = new double[m.getColumns()][m.getRows()];
        for (int i = 0; i < m.getRows(); i++) {
            for (int j = 0; j < m.getColumns(); j++) {
                matrix[j][i] = m.get(i, j);
            }
        }
        return new MatrixImpl(matrix);
    }

    private static Matrix transposeMultiply(Matrix m) {
        Matrix transpose = Matrices.createMatrix(m.getColumns(), m.getRows());
        try {
            List<Future<?>> futures = new ArrayList<>();
            final int length = transpose.getRows() / Runtime.getRuntime().availableProcessors() + 1;
            for (int i = 0; i < transpose.getRows(); i += length) {
                futures.add(executor.submit(new TransposingRunnable(m, transpose, i, Math.min(i + length, transpose.getRows()))));
            }
            for (Future<?> future : futures) {
                future.get();
            }
        } catch (ExecutionException | InterruptedException e) {
            throw new RuntimeException(e);
        }
        return transpose;
    }

    private static class TransposingRunnable implements Runnable {
        private final Matrix m, result;
        private final int start, end;

        TransposingRunnable(Matrix m, Matrix result, int startColumn, int endColumn) {
            this.m = m;
            this.result = result;
            this.start = startColumn;
            this.end = endColumn;
        }

        @Override
        public void run() {
            for (int i = start; i < end; i++) {
                for (int j = 0; j < result.getColumns(); j++) {
                    result.set(i, j, m.get(j, i));
                }
            }
        }
    }

    static Matrix subtract(Matrix m1, Matrix m2) {
        switch (computingMode) {
            case Single: return subtractSingly(m1, m2);
            case Multiple: return subtractMultiply(m1, m2);
            default: return m1.size() < LIMIT ? subtractSingly(m1, m2) : subtractMultiply(m1, m2);
        }
    }

    private static Matrix subtractSingly(Matrix m1, Matrix m2) {
        double[][] matrix = new double[m1.getRows()][m1.getColumns()];
        for (int i = 0; i < m1.getRows(); i++) {
            for (int j = 0; j < m1.getColumns(); j++) {
                matrix[i][j] = m1.get(i, j) - m2.get(i, j);
            }
        }
        return new MatrixImpl(matrix);
    }

    private static Matrix subtractMultiply(Matrix m1, Matrix m2) {
        Matrix subtract = Matrices.createMatrix(m1.getRows(), m1.getColumns());
        try {
            List<Future<?>> futures = new ArrayList<>();
            final int length = subtract.getRows() / Runtime.getRuntime().availableProcessors() + 1;
            for (int i = 0; i < subtract.getRows(); i += length) {
                futures.add(executor.submit(new SubtractingRunnable(m1, m2, subtract, i, Math.min(i + length, subtract.getRows()))));
            }
            for (Future<?> future : futures) {
                future.get();
            }
        } catch (ExecutionException | InterruptedException e) {
            throw new RuntimeException(e);
        }
        return subtract;
    }

    private static class SubtractingRunnable implements Runnable {
        private final Matrix m1, m2, result;
        private final int start, end;

        SubtractingRunnable(Matrix m1, Matrix m2, Matrix result, int startRow, int endRow) {
            this.m1 = m1;
            this.m2 = m2;
            this.result = result;
            this.start = startRow;
            this.end = endRow;
        }

        @Override
        public void run() {
            for (int i = start; i < end; i++) {
                for (int j = 0; j < result.getColumns(); j++) {
                    result.set(i, j, m1.get(i, j) - m2.get(i, j));
                }
            }
        }
    }

    static Matrix multiply(Matrix m1, double scalar) {
        switch (computingMode) {
            case Single: return multiplySingly(m1, scalar);
            case Multiple: return multiplyMultiply(m1, scalar);
            default: return m1.size() < LIMIT ? multiplySingly(m1, scalar) : multiplyMultiply(m1, scalar);
        }
    }

    private static Matrix multiplySingly(Matrix m, double scalar) {
        double[][] matrix = new double[m.getRows()][m.getColumns()];
        for (int i = 0; i < m.getRows(); i++) {
            for (int j = 0; j < m.getColumns(); j++) {
                matrix[i][j] = m.get(i, j) * scalar;
            }
        }
        return new MatrixImpl(matrix);
    }

    private static Matrix multiplyMultiply(Matrix m, double scalar) {
        Matrix multiply = Matrices.createMatrix(m.getRows(), m.getColumns());
        try {
            List<Future<?>> futures = new ArrayList<>();
            final int length = multiply.getRows() / Runtime.getRuntime().availableProcessors() + 1;
            for (int i = 0; i < multiply.getRows(); i += length) {
                futures.add(executor.submit(new ConstantMultiplyingRunnable(m, multiply, scalar, i, Math.min(i + length, multiply.getRows()))));
            }
            for (Future<?> future : futures) {
                future.get();
            }
        } catch (ExecutionException | InterruptedException e) {
            throw new RuntimeException(e);
        }
        return multiply;
    }

    private static class ConstantMultiplyingRunnable implements Runnable {
        private final Matrix m, result;
        private final double scalar;
        private final int start, end;

        ConstantMultiplyingRunnable(Matrix m, Matrix result, double scalar, int startRow, int endRow) {
            this.m = m;
            this.result = result;
            this.scalar = scalar;
            this.start = startRow;
            this.end = endRow;
        }

        @Override
        public void run() {
            for (int i = start; i < end; i++) {
                for (int j = 0; j < result.getColumns(); j++) {
                    result.set(i, j, m.get(i, j) * scalar);
                }
            }
        }
    }

    static Matrix multiply(Matrix m1, Matrix m2) {
        switch (computingMode) {
            case Single: return multiplySingly(m1, m2);
            case Multiple: return multiplyMultiply(m1, m2);
            default: return m1.size() * m2.getColumns() < LIMIT ? multiplySingly(m1, m2) : multiplyMultiply(m1, m2);
        }
    }

    private static Matrix multiplySingly(Matrix m1, Matrix m2) {
        double[][] matrix = new double[m1.getRows()][m2.getColumns()];
        for (int i = 0; i < m1.getRows(); i++) {
            for (int j = 0; j < m2.getColumns(); j++) {
                for (int k = 0; k < m1.getColumns(); k++) {
                    matrix[i][j] += m1.get(i, k) * m2.get(k, j);
                }
            }
        }
        return new MatrixImpl(matrix);
    }

    private static Matrix multiplyMultiply(Matrix m1, Matrix m2) {
        Matrix multiply = Matrices.createMatrix(m1.getRows(), m2.getColumns());
        try {
            List<Future<?>> futures = new ArrayList<>();
            final int length = multiply.getRows() / Runtime.getRuntime().availableProcessors() + 1;
            for (int i = 0; i < multiply.getRows(); i += length) {
                futures.add(executor.submit(new MatrixMultiplyingRunnable(m1, m2, multiply, i, Math.min(i + length, multiply.getRows()))));
            }
            for (Future<?> future : futures) {
                future.get();
            }
        } catch (ExecutionException | InterruptedException e) {
            throw new RuntimeException(e);
        }
        return multiply;
    }

    private static class MatrixMultiplyingRunnable implements Runnable {
        private final Matrix m1, m2, result;
        private final int start, end;

        MatrixMultiplyingRunnable(Matrix m1, Matrix m2, Matrix result, int startRow, int endRow) {
            this.m1 = m1;
            this.m2 = m2;
            this.result = result;
            this.start = startRow;
            this.end = endRow;
        }

        @Override
        public void run() {
            for (int i = start; i < end; i++) {
                for (int j = 0; j < result.getColumns(); j++) {
                    for (int k = 0; k < m1.getColumns(); k++) {
                        result.set(i, j, result.get(i, j) + m1.get(i, k) * m2.get(k, j));
                    }
                }
            }
        }
    }

    private MatrixHelper() {}
}
