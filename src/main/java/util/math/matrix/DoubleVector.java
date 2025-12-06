package util.math.matrix;

public interface DoubleVector extends Vector, DoubleMatrix {
    double get(int index);

    void set(int index, double value);

    @Override
    default double get(int rows, int columns) {
        if (columns != 0) {
            throw new IllegalArgumentException("Column index out of bounds");
        }
        return get(rows);
    }

    @Override
    default void set(int rows, int columns, double value) {
        if (columns != 0) {
            throw new IllegalArgumentException("Column index out of bounds");
        }
        set(rows, value);
    }
}
