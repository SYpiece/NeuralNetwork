package util.math.matrix;

public class Tensors extends Matrices {
    public static FloatTensor createFloatTensor(int... ranks) {
        return new FloatTensorImpl(ranks);
    }

    public static FloatTensor createFloatTensor(int[] ranks, float[] data) {
        return new FloatTensorImpl(ranks, data);
    }

    public static DoubleTensor createDoubleTensor(int... ranks) {
        return new DoubleTensorImpl(ranks);
    }

    public static DoubleTensor createDoubleTensor(int[] ranks, double[] data) {
        return new DoubleTensorImpl(ranks, data);
    }

    private static class FloatTensorImpl implements FloatTensor {
        final int[] dimensions;
        final float[] data;

        FloatTensorImpl(int[] ranks) {
            this.dimensions = ranks;
            int size = 1;
            for (int dimension : ranks) {
                if (dimension < 0) {
                    throw new IllegalArgumentException();
                }
                size *= dimension;
            }
            data = new float[size];
        }

        FloatTensorImpl(int[] ranks, float[] data) {
            int size = 1;
            for (int dimension : ranks) {
                if (dimension < 0) {
                    throw new IllegalArgumentException();
                }
                size *= dimension;
            }
            if (data.length != size) {
                throw new IllegalArgumentException();
            }
            this.dimensions = ranks;
            this.data = data;
        }

        @Override
        public int[] getRankData() {
            return dimensions;
        }

        @Override
        public int getRankSize() {
            return dimensions.length;
        }

        @Override
        public int getRank(int rank) {
            return dimensions[rank];
        }

        @Override
        public float[] getData() {
            return data;
        }

        @Override
        public float get(int... indices) {
            return data[getIndex(indices)];
        }

        @Override
        public void set(int[] indices, float value) {
            data[getIndex(indices)] = value;
        }

        int getIndex(int... indices) {
            if (indices.length != dimensions.length) {
                throw new IllegalArgumentException("Invalid number of indices");
            }
            int index = 0;
            for (int i = indices.length - 1, j = 1; i >= 0; j *= dimensions[i--]) {
                index += indices[i] * j;
            }
            return index;
        }
    }

    private static class DoubleTensorImpl implements DoubleTensor {
        final int[] dimensions;
        final double[] data;

        DoubleTensorImpl(int[] ranks) {
            this.dimensions = ranks;
            int size = 1;
            for (int dimension : ranks) {
                if (dimension < 0) {
                    throw new IllegalArgumentException();
                }
                size *= dimension;
            }
            data = new double[size];
        }

        DoubleTensorImpl(int[] ranks, double[] data) {
            int size = 1;
            for (int dimension : ranks) {
                if (dimension < 0) {
                    throw new IllegalArgumentException();
                }
                size *= dimension;
            }
            if (data.length != size) {
                throw new IllegalArgumentException();
            }
            this.dimensions = ranks;
            this.data = data;
        }

        @Override
        public int[] getRankData() {
            return dimensions;
        }

        @Override
        public int getRankSize() {
            return dimensions.length;
        }

        @Override
        public int getRank(int rank) {
            return dimensions[rank];
        }

        @Override
        public double[] getData() {
            return data;
        }

        @Override
        public double get(int... indices) {
            return data[getIndex(indices)];
        }

        @Override
        public void set(int[] indices, double value) {
            data[getIndex(indices)] = value;
        }

        int getIndex(int... indices) {
            if (indices.length != dimensions.length) {
                throw new IllegalArgumentException("Invalid number of indices");
            }
            int index = 0;
            for (int i = indices.length - 1, j = 1; i >= 0; j *= dimensions[i--]) {
                index += indices[i] * j;
            }
            return index;
        }
    }

    protected Tensors() {}
}
