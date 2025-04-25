package util.matrix;

public interface Vector extends Matrix, Cloneable {
    @Override
    int size();

    @Override
    default int getRows() {
        return size();
    }

    @Override
    default int getColumns() {
        return 1;
    }

    double get(int index);

    void set(int index, double value);

    @Override
    default double get(int row, int column) {
        if (column != 0) {
            throw new IllegalArgumentException("Column index must be 0");
        }
        return get(row);
    }

    @Override
    default void set(int row, int column, double value) {
        if (column != 0) {
            throw new IllegalArgumentException("Column index must be 0");
        }
        set(row, value);
    }

    default Vector add(Vector v) {
        if (size() != v.size()) {
            throw new IllegalArgumentException("Vectors must have the same size");
        }
        double[] vector = new double[size()];
        for (int i = 0; i < size(); i++) {
            vector[i] = get(i) + v.get(i);
        }
        return new VectorImpl(vector);
    }

    default Vector subtract(Vector v) {
        if (size() != v.size()) {
            throw new IllegalArgumentException("Vectors must have the same size");
        }
        return Vectors.doPackMatrix(MatrixHelper.subtract(this, v));
    }

    @Override
    default Vector multiply(double scalar) {
        return Vectors.doPackMatrix(MatrixHelper.multiply(this, scalar));
    }

    default Vector dot(Vector v) {
        if (size() != v.size()) {
            throw new IllegalArgumentException("Vectors must have the same size");
        }
        double[] vector = new double[size()];
        for (int i = 0; i < size(); i++) {
            vector[i] = get(i) * v.get(i);
        }
        return new VectorImpl(vector);
    }

    Vector clone();
}
