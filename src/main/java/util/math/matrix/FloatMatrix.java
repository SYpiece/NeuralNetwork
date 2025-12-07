package util.math.matrix;

public interface FloatMatrix extends Matrix, FloatTensor {
    float[] getData();

    float get(int row, int column);

    void set(int row, int column, float value);

    @Override
    default float get(int... indices) {
        if (indices.length != 2) {
            throw new IllegalArgumentException("Invalid number of indices");
        }
        return get(indices[0], indices[1]);
    }

    @Override
    default void set(float value, int... indices) {
        if (indices.length != 2) {
            throw new IllegalArgumentException("Invalid number of indices");
        }
        set(indices[0], indices[1], value);
    }
}
