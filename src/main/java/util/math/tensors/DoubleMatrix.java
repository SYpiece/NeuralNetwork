package util.math.tensors;

public interface DoubleMatrix extends Matrix {
    double[] getData();

    double get(int row, int column);

    void set(int row, int column, double value);
}
