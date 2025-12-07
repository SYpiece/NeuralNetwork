package util.math.matrix;

public interface FloatScalar extends Scalar, FloatVector, FloatMatrix, FloatTensor {
    float[] getData();

    float get();

    void set(float value);

    @Override
    default float get(int index) {
        if (index != 0) {
            throw new IllegalArgumentException("Invalid index");
        }
        return get();
    }

    @Override
    default void set(int index, float value) {
        if (index != 0) {
            throw new IllegalArgumentException("Invalid index");
        }
        set(value);
    }

    @Override
    default float get(int row, int column) {
        if (row != 0 || column != 0) {
            throw new IllegalArgumentException("Invalid index");
        }
        return get();
    }

    @Override
    default void set(int row, int column, float value) {
        if (row != 0 || column != 0) {
            throw new IllegalArgumentException("Invalid index");
        }
        set(value);
    }

    @Override
    default float get(int... indices) {
        if (indices.length != 0) {
            throw new IllegalArgumentException("Invalid number of indices");
        }
        return get();
    }

    @Override
    default void set(int[] indices, float value) {
        if (indices.length != 0) {
            throw new IllegalArgumentException("Invalid number of indices");
        }
        set(value);
    }
}
