package util.math.matrix;

public interface DoubleMatrix extends Matrix {
    double get(int row, int column);

    void set(int row, int column, double value);
}
