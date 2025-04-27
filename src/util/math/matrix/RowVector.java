package util.math.matrix;

public interface RowVector extends Vector, Matrix {
    @Override
    int size();

    @Override
    default int getRows() {
        return 1;
    }

    @Override
    default int getColumns() {
        return size();
    }

    @Override
    default double get(int row, int column) {
        if (row != 0) {
            throw new IllegalArgumentException("Row index out of bounds");
        }
        return get(column);
    }

    @Override
    default void set(int row, int column, double value) {
        if (row != 0) {
            throw new IllegalArgumentException("Row index out of bounds");
        }
        set(column, value);
    }

    @Override
    default Matrix toMatrix(Orientation orientation) {
        if (orientation == Orientation.LeftToRight) {
            return this;
        } else {
            return Vector.super.toMatrix(orientation);
        }
    }
}
