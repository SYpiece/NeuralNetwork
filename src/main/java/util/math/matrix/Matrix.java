package util.math.matrix;

public interface Matrix {
    int getRows();

    int getColumns();

    class Float implements Matrix {
        final float[] matrix;
        final int rows, columns;

        @Override
        public int getRows() {
            return rows;
        }

        @Override
        public int getColumns() {
            return columns;
        }

        public Float(int rows, int columns) {
            this.rows = rows;
            this.columns = columns;
            matrix = new float[rows * columns];
        }

        public Float(int rows, int columns, float[] matrix) {
            if (matrix.length != rows * columns) {
                throw new IllegalArgumentException("matrix must be rectangular");
            }
            this.rows = rows;
            this.columns = columns;
            this.matrix = matrix;
        }

        public Float(float[][] matrix) {
            this.rows = matrix.length;
            this.columns = matrix[0].length;
            this.matrix = new float[rows * columns];
            for (int i = 0; i < rows; i++) {
                if (matrix[i].length != columns) {
                    throw new IllegalArgumentException("matrix must be rectangular");
                }
                System.arraycopy(matrix[i], 0, this.matrix, i * columns, columns);
            }
        }
    }
}