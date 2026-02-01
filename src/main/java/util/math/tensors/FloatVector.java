package util.math.tensors;

public class FloatVector extends Vector, FloatMatrix, FloatTensor {
    public float[] getData();

    public float get(int index);

    public void set(int index, float value);

    @Override
    public float get(int row, int column) {
        if (column != 0) {
            throw new IllegalArgumentException("Column index out of bounds");
        }
        return get(row);
    }

    @Override
    public void set(int row, int column, float value) {
        if (column != 0) {
            throw new IllegalArgumentException("Column index out of bounds");
        }
        set(row, value);
    }

    @Override
    public float get(int...  indices) {
        if (indices.length != 1) {
            throw new IllegalArgumentException("Column index out of bounds");
        }
        return get(indices[0]);
    }

    @Override
    public void set(int[] indices, float value) {
        if (indices.length != 1) {
            throw new IllegalArgumentException("Column index out of bounds");
        }
        set(indices[0], value);
    }
}
