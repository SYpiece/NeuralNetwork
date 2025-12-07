package util.math.matrix;

public interface FloatVector extends Vector, FloatMatrix, FloatTensor {
    float[] getData();

    float get(int index);

    void set(int index, float value);

    @Override
    default float get(int row, int column) {
        if (column != 0) {
            throw new IllegalArgumentException("Column index out of bounds");
        }
        return get(row);
    }

    @Override
    default void set(int row, int column, float value) {
        if (column != 0) {
            throw new IllegalArgumentException("Column index out of bounds");
        }
        set(row, value);
    }

    @Override
    default float get(int...  indices) {
        if (indices.length != 1) {
            throw new IllegalArgumentException("Column index out of bounds");
        }
        return get(indices[0]);
    }

    @Override
    default void set(int[] indices, float value) {
        if (indices.length != 1) {
            throw new IllegalArgumentException("Column index out of bounds");
        }
        set(indices[0], value);
    }
}
