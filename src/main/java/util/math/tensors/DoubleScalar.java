package util.math.tensors;

public interface DoubleScalar extends Scalar, DoubleVector, DoubleMatrix, DoubleTensor {
    double[] getData();

    double get();

    void set(double value);

    @Override
    default double get(int index) {
        if (index != 0) {
            throw new IllegalArgumentException("Invalid index");
        }
        return get();
    }

    @Override
    default void set(int index, double value) {
        if (index != 0) {
            throw new IllegalArgumentException("Invalid index");
        }
        set(value);
    }

    @Override
    default double get(int row, int column) {
        if (row != 0 || column != 0) {
            throw new IllegalArgumentException("Invalid index");
        }
        return get();
    }

    @Override
    default void set(int row, int column, double value) {
        if (row != 0 || column != 0) {
            throw new IllegalArgumentException("Invalid index");
        }
        set(value);
    }

    @Override
    default double get(int... indices) {
        if (indices.length != 0) {
            throw new IllegalArgumentException("Invalid number of indices");
        }
        return get();
    }

    @Override
    default void set(int[] indices, double value) {
        if (indices.length != 0) {
            throw new IllegalArgumentException("Invalid number of indices");
        }
        set(value);
    }
}
