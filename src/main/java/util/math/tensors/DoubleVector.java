package util.math.tensors;

public interface DoubleVector extends Vector {
    double[] getData();

    double get(int index);

    void set(int index, double value);
}
