package util.math.matrix;

import java.util.Objects;
import java.util.concurrent.ExecutorService;

public class Matrices {
    public static Matrix createMatrix(int rows, int columns) {
        return new MatrixImpl(rows, columns);
    }

    public static Matrix createMatrix(double[][] matrix) {
        if (matrix == null) {
            throw new IllegalArgumentException("Matrix must not be null");
        }
        for (double[] row : matrix) {
            if (row.length != matrix[0].length) {
                throw new IllegalArgumentException("Matrix must be rectangular");
            }
        }
        return new MatrixImpl(matrix);
    }

    private static class MatrixImpl implements Matrix {
        private final double[][] matrix;

        @Override
        public int getRows() {
            return matrix.length;
        }

        @Override
        public int getColumns() {
            return matrix[0].length;
        }

        @Override
        public double get(int row, int column) {
            return matrix[row][column];
        }

        @Override
        public void set(int row, int column, double value) {
            matrix[row][column] = value;
        }

        MatrixImpl(int rows, int columns) {
            matrix = new double[rows][columns];
        }

        MatrixImpl(double[][] matrix) {
            this.matrix = matrix;
        }
    }

    protected Matrices() {}
}
