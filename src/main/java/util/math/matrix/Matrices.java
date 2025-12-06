package util.math.matrix;

public class Matrices {
    public static FloatMatrix createFloatMatrix(int rows, int columns) {
        return new FloatMatrixImpl(rows, columns);
    }

    public static FloatMatrix createFloatMatrix(int rows, int columns, float[] matrix) {
        return new FloatMatrixImpl(rows, columns, matrix);
    }

    public static FloatMatrix createFloatMatrix(float[][] matrix) {
        return new FloatMatrixImpl(matrix);
    }

    public static DoubleMatrix createDoubleMatrix(int rows, int columns) {
        return new DoubleMatrixImpl(rows, columns);
    }

    public static DoubleMatrix createDoubleMatrix(int rows, int columns, double[] matrix) {
        return new DoubleMatrixImpl(rows, columns, matrix);
    }

    public static DoubleMatrix createDoubleMatrix(double[][] matrix) {
        return new DoubleMatrixImpl(matrix);
    }

    private static class FloatMatrixImpl implements FloatMatrix {
        final int rows, columns;

        final float[] matrix;

        @Override
        public int getRows() {
            return rows;
        }

        @Override
        public int getColumns() {
            return columns;
        }

        @Override
        public float[] getData() {
            return matrix;
        }

        @Override
        public float get(int row, int column) {
            return matrix[row * columns + column];
        }

        @Override
        public void set(int row, int column, float value) {
            matrix[row * columns + column] = value;
        }

        FloatMatrixImpl(int rows, int columns) {
            this.rows = rows;
            this.columns = columns;
            matrix = new float[rows * columns];
        }

        FloatMatrixImpl(int rows, int columns, float[] matrix) {
            if (matrix.length != rows * columns) {
                throw new IllegalArgumentException("matrix must be rectangular");
            }
            this.rows = rows;
            this.columns = columns;
            this.matrix = matrix;
        }

        FloatMatrixImpl(float[][] matrix) {
            this.rows = matrix.length;
            this.columns = matrix[0].length;
            for (int i = 0; i < rows; i++) {
                if (matrix[i].length != columns) {
                    throw new IllegalArgumentException("matrix must be rectangular");
                }
            }
            this.matrix = new float[rows * columns];
            for (int i = 0; i < rows; i++) {
                System.arraycopy(matrix[i], 0, this.matrix, i * columns, columns);
            }
        }
    }

    private static class DoubleMatrixImpl implements DoubleMatrix {
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
        public double[] getData() {
            return matrix;
        }

        @Override
        public double get(int row, int column) {
            return matrix[row * columns + column];
        }

        @Override
        public void set(int row, int column, double value) {
            matrix[row * columns + column] = value;
        }

        DoubleMatrixImpl(int rows, int columns) {
            this.rows = rows;
            this.columns = columns;
            matrix = new double[rows * columns];
        }

        DoubleMatrixImpl(int rows, int columns, double[] matrix) {
            if (matrix.length != rows * columns) {
                throw new IllegalArgumentException("matrix must be rectangular");
            }
            this.rows = rows;
            this.columns = columns;
            this.matrix = matrix;
        }

        DoubleMatrixImpl(double[][] matrix) {
            this.rows = matrix.length;
            this.columns = matrix[0].length;
            for (int i = 0; i < rows; i++) {
                if (matrix[i].length != columns) {
                    throw new IllegalArgumentException("matrix must be rectangular");
                }
            }
            this.matrix = new double[rows * columns];
            for (int i = 0; i < rows; i++) {
                System.arraycopy(matrix[i], 0, this.matrix, i * columns, columns);
            }
        }
    }

    protected Matrices() {}
}
