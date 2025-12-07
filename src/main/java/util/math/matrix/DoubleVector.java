package util.math.matrix;

public interface DoubleVector extends Vector {
    double[] getData();

    double get(int index);

    void set(int index, double value);
}
