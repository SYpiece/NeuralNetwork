package util.math.matrix;

public interface FloatMatrix extends Matrix {
    float[] getData();

    float getData(int index);

    void setData(int index, float value);

    float get(int row, int column);

    void set(int row, int column, float value);
}
