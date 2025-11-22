package util.math.matrix;

import java.util.Objects;
import java.util.concurrent.ExecutorService;

public class Matrices {
    public static Matrix createMatrix(int rows, int columns) {
        return new MatrixImpl(rows, columns);
    }

//    public static Matrix createMatrix(double[][] matrix) {
//        if (matrix == null) {
//            throw new IllegalArgumentException("Matrix must not be null");
//        }
//        for (double[] row : matrix) {
//            if (row.length != matrix[0].length) {
//                throw new IllegalArgumentException("Matrix must be rectangular");
//            }
//        }
//        return new MatrixImpl(matrix);
//    }

    private static class MatrixImpl implements Matrix {
        final int rows, columns;

        final double[] matrix;

        @Override
        public int getRows() {
            return rows;
        }

        @Override
        public int getColumns() {
            return columns;
        }

        @Override
        public double get(int row, int column) {
            return matrix[row * columns + column];
        }

        @Override
        public void set(int row, int column, double value) {
            matrix[row * columns + column] = value;
        }

        MatrixImpl(int rows, int columns) {
            this.rows = rows;
            this.columns = columns;
            matrix = new double[rows * columns];
        }
    }

    protected Matrices() {}
}
