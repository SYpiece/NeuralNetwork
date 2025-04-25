package util.matrix;

public interface Matrix extends Cloneable {
    int getRows();

    int getColumns();

    default int size() {
        return getRows() * getColumns();
    }

    double get(int row, int column);

    void set(int row, int column, double value);

    default Matrix transpose() {
        return MatrixHelper.transpose(this);
    }

    default Matrix add(Matrix m) {
        if (getRows() != m.getRows() || getColumns() != m.getColumns()) {
            throw new IllegalArgumentException("Matrix dimensions must match.");
        }
        double[][] matrix = new double[getRows()][getColumns()];
        for (int i = 0; i < getRows(); i++) {
            for (int j = 0; j < getColumns(); j++) {
                matrix[i][j] = get(i, j) + m.get(i, j);
            }
        }
        return new MatrixImpl(matrix);
    }

    default Matrix subtract(Matrix m) {
        if (getRows() != m.getRows() || getColumns() != m.getColumns()) {
            throw new IllegalArgumentException("Matrix dimensions must match.");
        }
        return MatrixHelper.subtract(this, m);
    }

    default Matrix multiply(double scalar) {
        return MatrixHelper.multiply(this, scalar);
    }

    default Matrix multiply(Matrix m) {
        if (getColumns() != m.getRows()) {
            throw new IllegalArgumentException("Matrix dimensions must match.");
        }
        return MatrixHelper.multiply(this, m);
    }

    default Vector multiply(Vector v) {
        if (getColumns() != v.size()) {
            throw new IllegalArgumentException("Matrix dimensions must match.");
        }
        return Vectors.doPackMatrix(MatrixHelper.multiply(this, v));
   }

   default Matrix dot(Matrix m) {
       if (getRows() != m.getRows() || getColumns() != m.getColumns()) {
           throw new IllegalArgumentException("Matrix dimensions must match.");
       }
       double[][] matrix = new double[getRows()][getColumns()];
       for (int i = 0; i < getRows(); i++) {
           for (int j = 0; j < getColumns(); j++) {
               matrix[i][j] = get(i, j) * m.get(i, j);
           }
       }
       return new MatrixImpl(matrix);
   }

    Matrix clone();
}
