package util.matrix;

import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

public class Matrices {
    public static ExecutorService getThreadPool() {
        return MatrixHelper.executor;
    }

    public static void setThreadPool(ExecutorService executor) {
        if (executor == null) {
            throw new IllegalArgumentException("Executor must not be null");
        }
        MatrixHelper.executor = executor;
    }

    public static Matrix createMatrix(int rows, int columns) {
        return new MatrixImpl(rows, columns);
    }

    public static ComputingMode getComputingMode() {
        return MatrixHelper.computingMode;
    }

    public static void setComputingMode(ComputingMode mode) {
        MatrixHelper.computingMode = Objects.requireNonNull(mode);
    }

    public static Matrix createMatrix(double[][] matrix) {
        if (matrix == null) {
            throw new IllegalArgumentException("Matrix must not be null");
        }
        for (double[] row : matrix) {
            if (row == null) {
                throw new IllegalArgumentException("Matrix must not contain null rows");
            }
            if (row.length != matrix[0].length) {
                throw new IllegalArgumentException("Matrix must be rectangular");
            }
        }
        return new MatrixImpl(matrix);
    }

    protected Matrices() {}
}
