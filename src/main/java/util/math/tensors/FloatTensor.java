package util.math.tensors;

public interface FloatTensor extends Tensor {
    float[] getData();

    float get(int... indices);

    void set(int[] indices, float value);
}
