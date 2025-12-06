package util.math.matrix;

public interface FloatVector extends Vector, FloatMatrix {
    float get(int index);

    void set(int index, float value);

    @Override
    default float get(int rows, int columns) {
        if (columns != 0) {
            throw new IllegalArgumentException("Column index out of bounds");
        }
        return get(rows);
    }

    @Override
    default void set(int rows, int columns, float value) {
        if (columns != 0) {
            throw new IllegalArgumentException("Column index out of bounds");
        }
        set(rows, value);
    }
}
