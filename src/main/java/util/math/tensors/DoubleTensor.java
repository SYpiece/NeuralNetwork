package util.math.tensors;

public interface DoubleTensor extends Tensor {
    double[] getData();

    double get(int... indices);

    void set(int[] indices, double value);
}
