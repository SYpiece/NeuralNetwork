package util.math.matrix;

public interface Matrix {
    int getRows();

    int getColumns();

    default int size() {
        return getRows() * getColumns();
    }
}