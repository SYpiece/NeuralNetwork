package util.math.matrix;

public interface Matrix extends Tensor {
    int getRows();

    int getColumns();

    @Override
    default int size() {
        return getRows() * getColumns();
    }

    @Override
    default int[] getRankData() {
        return new int[] {getRows(), getColumns()};
    }

    @Override
    default int getRankSize() {
        return 2;
    }

    @Override
    default int getRank(int rank) {
        if (rank == 0) {
            return getRows();
        }
        if (rank == 1) {
            return getColumns();
        }
        throw new IllegalArgumentException("Invalid rank");
    }


}