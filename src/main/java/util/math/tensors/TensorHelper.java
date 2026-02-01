package util.math.tensors;

class TensorHelper {
    static int getTensorSize(int[] length) {
        int size = 1;
        for (int j : length) {
            size *= j;
        }
        return size;
    }

    static int getIndex(int[] indices, int[] length) {
        int index = 0;
        for (int i = indices.length - 1, j = 1; i >= 0; j *= length[i--]) {
            index += indices[i] * j;
        }
        return index;
    }

    private TensorHelper() {
    }
}
