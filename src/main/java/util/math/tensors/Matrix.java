package util.math.tensors;

import java.util.Arrays;

public interface Matrix extends Data {
    static Matrix.Byte clone(Matrix.Byte matrix) {
        return new Matrix.Byte(matrix.rows, matrix.columns, Arrays.copyOf(matrix.matrix, matrix.size));
    }

    static Matrix.Short clone(Matrix.Short matrix) {
        return new Matrix.Short(matrix.rows, matrix.columns, Arrays.copyOf(matrix.matrix, matrix.size));
    }

    static Matrix.Integer clone(Matrix.Integer matrix) {
        return new Matrix.Integer(matrix.rows, matrix.columns, Arrays.copyOf(matrix.matrix, matrix.size));
    }

    static Matrix.Long clone(Matrix.Long matrix) {
        return new Matrix.Long(matrix.rows, matrix.columns, Arrays.copyOf(matrix.matrix, matrix.size));
    }

    static Matrix.Float clone(Matrix.Float matrix) {
        return new Matrix.Float(matrix.rows, matrix.columns, Arrays.copyOf(matrix.matrix, matrix.size));
    }

    static Matrix.Double clone(Matrix.Double matrix) {
        return new Matrix.Double(matrix.rows, matrix.columns, Arrays.copyOf(matrix.matrix, matrix.size));
    }

    int getRows();

    int getColumns();

    int size();

    Tensor asTensor();

    class Byte implements Matrix {
        final byte[] matrix;
        final int rows, columns, size;

        @Override
        public byte[] getData() {
            return matrix;
        }

        @Override
        public int getRows() {
            return rows;
        }

        @Override
        public int getColumns() {
            return columns;
        }

        @Override
        public int size() {
            return size;
        }

        public byte get(int row, int column) {
            return matrix[row * columns + column];
        }

        public void set(int row, int column, byte value) {
            matrix[row * columns + column] = value;
        }

        public Byte(int rows, int columns) {
            this.rows = rows;
            this.columns = columns;
            size = rows * columns;
            matrix = new byte[size];
        }

        public Byte(int rows, int columns, byte[] matrix) {
            size = rows * columns;
            if (matrix.length != size) {
                throw new IllegalArgumentException("matrix must be rectangular");
            }
            this.rows = rows;
            this.columns = columns;
            this.matrix = matrix;
        }

        public Byte(byte[][] matrix) {
            this.rows = matrix.length;
            this.columns = matrix[0].length;
            for (int i = 0; i < rows; i++) {
                if (matrix[i].length != columns) {
                    throw new IllegalArgumentException("matrix must be rectangular");
                }
            }
            size = rows * columns;
            this.matrix = new byte[size];
            for (int i = 0; i < rows; i++) {
                System.arraycopy(matrix[i], 0, this.matrix, i * columns, columns);
            }
        }

        @Override
        public Tensor.Byte asTensor() {
            return new Tensor.Byte(matrix, rows, columns);
        }
    }

    class Short implements Matrix {
        final short[] matrix;
        final int rows, columns, size;

        @Override
        public short[] getData() {
            return matrix;
        }

        @Override
        public int getRows() {
            return rows;
        }

        @Override
        public int getColumns() {
            return columns;
        }

        @Override
        public int size() {
            return size;
        }

        public short get(int row, int column) {
            return matrix[row * columns + column];
        }

        public void set(int row, int column, short value) {
            matrix[row * columns + column] = value;
        }

        public Short(int rows, int columns) {
            this.rows = rows;
            this.columns = columns;
            size = rows * columns;
            matrix = new short[size];
        }

        public Short(int rows, int columns, short[] matrix) {
            size = rows * columns;
            if (matrix.length != size) {
                throw new IllegalArgumentException("matrix must be rectangular");
            }
            this.rows = rows;
            this.columns = columns;
            this.matrix = matrix;
        }

        public Short(short[][] matrix) {
            this.rows = matrix.length;
            this.columns = matrix[0].length;
            for (int i = 0; i < rows; i++) {
                if (matrix[i].length != columns) {
                    throw new IllegalArgumentException("matrix must be rectangular");
                }
            }
            size = rows * columns;
            this.matrix = new short[size];
            for (int i = 0; i < rows; i++) {
                System.arraycopy(matrix[i], 0, this.matrix, i * columns, columns);
            }
        }

        @Override
        public Tensor.Short asTensor() {
            return new Tensor.Short(matrix, rows, columns);
        }
    }

    class Integer implements Matrix {
        final int[] matrix;
        final int rows, columns, size;

        @Override
        public int[] getData() {
            return matrix;
        }

        @Override
        public int getRows() {
            return rows;
        }

        @Override
        public int getColumns() {
            return columns;
        }

        @Override
        public int size() {
            return size;
        }

        public int get(int row, int column) {
            return matrix[row * columns + column];
        }

        public void set(int row, int column, int value) {
            matrix[row * columns + column] = value;
        }

        public Integer(int rows, int columns) {
            this.rows = rows;
            this.columns = columns;
            size = rows * columns;
            matrix = new int[size];
        }

        public Integer(int rows, int columns, int[] matrix) {
            size = rows * columns;
            if (matrix.length != size) {
                throw new IllegalArgumentException("matrix must be rectangular");
            }
            this.rows = rows;
            this.columns = columns;
            this.matrix = matrix;
        }

        public Integer(int[][] matrix) {
            this.rows = matrix.length;
            this.columns = matrix[0].length;
            for (int i = 0; i < rows; i++) {
                if (matrix[i].length != columns) {
                    throw new IllegalArgumentException("matrix must be rectangular");
                }
            }
            size = rows * columns;
            this.matrix = new int[size];
            for (int i = 0; i < rows; i++) {
                System.arraycopy(matrix[i], 0, this.matrix, i * columns, columns);
            }
        }

        @Override
        public Tensor.Integer asTensor() {
            return new Tensor.Integer(matrix, rows, columns);
        }
    }

    class Long implements Matrix {
        final long[] matrix;
        final int rows, columns, size;

        @Override
        public long[] getData() {
            return matrix;
        }

        @Override
        public int getRows() {
            return rows;
        }

        @Override
        public int getColumns() {
            return columns;
        }

        @Override
        public int size() {
            return size;
        }

        public long get(int row, int column) {
            return matrix[row * columns + column];
        }

        public void set(int row, int column, long value) {
            matrix[row * columns + column] = value;
        }

        public Long(int rows, int columns) {
            this.rows = rows;
            this.columns = columns;
            size = rows * columns;
            matrix = new long[size];
        }

        public Long(int rows, int columns, long[] matrix) {
            size = rows * columns;
            if (matrix.length != size) {
                throw new IllegalArgumentException("matrix must be rectangular");
            }
            this.rows = rows;
            this.columns = columns;
            this.matrix = matrix;
        }

        public Long(long[][] matrix) {
            this.rows = matrix.length;
            this.columns = matrix[0].length;
            for (int i = 0; i < rows; i++) {
                if (matrix[i].length != columns) {
                    throw new IllegalArgumentException("matrix must be rectangular");
                }
            }
            size = rows * columns;
            this.matrix = new long[size];
            for (int i = 0; i < rows; i++) {
                System.arraycopy(matrix[i], 0, this.matrix, i * columns, columns);
            }
        }

        @Override
        public Tensor.Long asTensor() {
            return new Tensor.Long(matrix, rows, columns);
        }
    }

    class Float implements Matrix {
        final float[] matrix;
        final int rows, columns, size;

        @Override
        public float[] getData() {
            return matrix;
        }

        @Override
        public int getRows() {
            return rows;
        }

        @Override
        public int getColumns() {
            return columns;
        }

        @Override
        public int size() {
            return size;
        }

        public float get(int row, int column) {
            return matrix[row * columns + column];
        }

        public void set(int row, int column, float value) {
            matrix[row * columns + column] = value;
        }

        public Float(int rows, int columns) {
            this.rows = rows;
            this.columns = columns;
            size = rows * columns;
            matrix = new float[size];
        }

        public Float(int rows, int columns, float[] matrix) {
            size = rows * columns;
            if (matrix.length != size) {
                throw new IllegalArgumentException("matrix must be rectangular");
            }
            this.rows = rows;
            this.columns = columns;
            this.matrix = matrix;
        }

        public Float(float[][] matrix) {
            this.rows = matrix.length;
            this.columns = matrix[0].length;
            for (int i = 0; i < rows; i++) {
                if (matrix[i].length != columns) {
                    throw new IllegalArgumentException("matrix must be rectangular");
                }
            }
            size = rows * columns;
            this.matrix = new float[size];
            for (int i = 0; i < rows; i++) {
                System.arraycopy(matrix[i], 0, this.matrix, i * columns, columns);
            }
        }

        @Override
        public Tensor.Float asTensor() {
            return new Tensor.Float(matrix, rows, columns);
        }
    }

    class Double implements Matrix {
        final double[] matrix;
        final int rows, columns, size;

        @Override
        public double[] getData() {
            return matrix;
        }

        @Override
        public int getRows() {
            return rows;
        }

        @Override
        public int getColumns() {
            return columns;
        }

        @Override
        public int size() {
            return size;
        }

        public double get(int row, int column) {
            return matrix[row * columns + column];
        }

        public void set(int row, int column, double value) {
            matrix[row * columns + column] = value;
        }

        public Double(int rows, int columns) {
            this.rows = rows;
            this.columns = columns;
            size = rows * columns;
            matrix = new double[size];
        }

        public Double(int rows, int columns, double[] matrix) {
            size = rows * columns;
            if (matrix.length != size) {
                throw new IllegalArgumentException("matrix must be rectangular");
            }
            this.rows = rows;
            this.columns = columns;
            this.matrix = matrix;
        }

        public Double(double[][] matrix) {
            this.rows = matrix.length;
            this.columns = matrix[0].length;
            for (int i = 0; i < rows; i++) {
                if (matrix[i].length != columns) {
                    throw new IllegalArgumentException("matrix must be rectangular");
                }
            }
            size = rows * columns;
            this.matrix = new double[size];
            for (int i = 0; i < rows; i++) {
                System.arraycopy(matrix[i], 0, this.matrix, i * columns, columns);
            }
        }

        @Override
        public Tensor.Double asTensor() {
            return new Tensor.Double(matrix, rows, columns);
        }
    }
}