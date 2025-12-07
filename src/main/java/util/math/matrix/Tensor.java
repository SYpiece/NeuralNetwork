package util.math.matrix;

public interface Tensor {
    int[] getRankData();

    int getRankSize();

    int getRank(int rank);

    default int size() {
        int size = 1;
        for (int i = 0; i < getRankSize(); i++) {
            size *= getRank(i);
        }
        return size;
    }

    default boolean isScalar() {
        return getRankSize() == 0;
    }

    default boolean isVector() {
        return getRankSize() == 1;
    }

    default boolean isMatrix() {
        return getRankSize() == 2;
    }

    default boolean isTensor() {
        return true;
    }
}
