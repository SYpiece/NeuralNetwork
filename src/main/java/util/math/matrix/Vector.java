package util.math.matrix;

public interface Vector extends Matrix {
    int size();

    @Override
    default int getRows() {
        return size();
    }

    @Override
    default int getColumns() {
        return 1;
    }
}
