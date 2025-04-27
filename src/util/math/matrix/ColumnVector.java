package util.math.matrix;

public interface ColumnVector extends Vector, Matrix {
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

    @Override
    default double get(int row, int column) {
        if (column != 0) {
            throw new IllegalArgumentException("Column index out of bounds");
        }
        return get(row);
    }

    @Override
    default void set(int row, int column, double value) {
        if (column != 0) {
            throw new IllegalArgumentException("Column index out of bounds");
        }
        set(row, value);
    }

    @Override
    default Matrix toMatrix(Orientation orientation) {
        if (orientation == Orientation.TopToBottom) {
            return this;
        } else {
            return Vector.super.toMatrix(orientation);
        }
    }
}
