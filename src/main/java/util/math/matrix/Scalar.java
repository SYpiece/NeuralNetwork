package util.math.matrix;

public interface Scalar extends Vector, Matrix, Tensor {
    @Override
    default int size() {
        return 1;
    }

    @Override
    default int getRows() {
        return 1;
    }

    @Override
    default int getColumns() {
        return 1;
    }

    @Override
    default int[] getRankData() {
        return new int[0];
    }

    @Override
    default int getRankSize() {
        return 0;
    }

    @Override
    default int getRank(int rank) {
        throw new IllegalArgumentException("Invalid rank");
    }
}
