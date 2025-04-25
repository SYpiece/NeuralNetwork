package util.matrix;

class MatrixImpl implements Matrix, Cloneable {
    private double[][] matrix;

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

    MatrixImpl() {}

    MatrixImpl(double[][] matrix) {
        this.matrix = matrix;
    }

    MatrixImpl(int rows, int columns) {
        this.matrix = new double[rows][columns];
    }

    @Override
    public Matrix clone() {
        try {
            MatrixImpl clone = (MatrixImpl) super.clone();
            clone.matrix = new double[getRows()][getColumns()];
            for (int i = 0; i < getRows(); i++) {
                for (int j = 0; j < getColumns(); j++) {
                    clone.matrix[i][j] = get(i, j);
                }
            }
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(super.toString()).append("\n[");
        for (int i = 0; i < getRows(); i++) {
            for (int j = 0; j < getColumns(); j++) {
                builder.append(get(i, j));
                if (j < getColumns() - 1) {
                    builder.append("\t");
                }
            }
            if (i < getRows() - 1) {
                builder.append("\n ");
            } else {
                builder.append("]");
            }
        }
        return builder.toString();
    }
}
