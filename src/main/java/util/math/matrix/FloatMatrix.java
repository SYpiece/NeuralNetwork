package util.math.matrix;

public interface FloatMatrix extends Matrix {
    float[] getData();

    float get(int row, int column);

    void set(int row, int column, float value);
}
