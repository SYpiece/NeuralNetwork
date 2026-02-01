package util.math.tensors;

import java.util.Arrays;

import static util.math.tensors.TensorHelper.*;

public interface Tensor extends Data {
    static Tensor.Byte clone(Tensor.Byte tensor) {
        return new Tensor.Byte(Arrays.copyOf(tensor.tensor, tensor.size), tensor.length);
    }

    static Tensor.Short clone(Tensor.Short tensor) {
        return new Tensor.Short(Arrays.copyOf(tensor.tensor, tensor.size), tensor.length);
    }

    static Tensor.Integer clone(Tensor.Integer tensor) {
        return new Tensor.Integer(Arrays.copyOf(tensor.tensor, tensor.size), tensor.length);
    }

    static Tensor.Long clone(Tensor.Long tensor) {
        return new Tensor.Long(Arrays.copyOf(tensor.tensor, tensor.size), tensor.length);
    }

    static Tensor.Float clone(Tensor.Float tensor) {
        return new Tensor.Float(Arrays.copyOf(tensor.tensor, tensor.size), tensor.length);
    }

    static Tensor.Double clone(Tensor.Double tensor) {
        return new Tensor.Double(Arrays.copyOf(tensor.tensor, tensor.size), tensor.length);
    }

    int[] getLengthData();

    int getRank();

    int getLength(int dimension);

    int size();

    default boolean isScalar() {
        return getRank() == 0;
    }

    default boolean isVector() {
        return getRank() == 1;
    }

    default boolean isMatrix() {
        return getRank() == 2;
    }

    default boolean isHighDimensionalTensor() {
        return getRank() >= 3;
    }

    Scalar asScalar();

    Vector asVector();

    Matrix asMatrix();

    class Byte implements Tensor {
        final byte[] tensor;
        final int[] length;
        final int size;

        @Override
        public byte[] getData() {
            return tensor;
        }

        @Override
        public int getRank() {
            return length.length;
        }

        @Override
        public int[] getLengthData() {
            return length;
        }

        @Override
        public int getLength(int dimension) {
            return length[dimension];
        }

        @Override
        public int size() {
            return size;
        }

        public byte get(int... indices) {
            return tensor[getIndex(indices, length)];
        }

        public void set(int[] indices, byte value) {
            tensor[getIndex(indices, length)] = value;
        }

        public Byte(int... length) {
            this.length = length;
            size = getTensorSize(length);
            tensor = new byte[size];
        }

        public Byte(byte[] tensor, int... length) {
            this.length = length;
            size = getTensorSize(length);
            if (tensor.length != size) {
                throw new IllegalArgumentException();
            }
            this.tensor = tensor;
        }

        @Override
        public Scalar.Byte asScalar() {
            if (getRank() != 0) {
                throw new IllegalArgumentException();
            }
            return new Scalar.Byte(tensor[0]);
        }

        @Override
        public Vector.Byte asVector() {
            if (getRank() != 1) {
                throw new IllegalArgumentException();
            }
            return new Vector.Byte(tensor);
        }

        @Override
        public Matrix.Byte asMatrix() {
            if (getRank() != 2) {
                throw new IllegalArgumentException();
            }
            return new Matrix.Byte(length[0], length[1], tensor);
        }
    }

    class Short implements Tensor {
        final short[] tensor;
        final int[] length;
        final int size;

        @Override
        public short[] getData() {
            return tensor;
        }

        @Override
        public int getRank() {
            return length.length;
        }

        @Override
        public int[] getLengthData() {
            return length;
        }

        @Override
        public int getLength(int dimension) {
            return length[dimension];
        }

        @Override
        public int size() {
            return size;
        }

        public short get(int... indices) {
            return tensor[getIndex(indices, length)];
        }

        public void set(int[] indices, short value) {
            tensor[getIndex(indices, length)] = value;
        }

        public Short(int... length) {
            this.length = length;
            size = getTensorSize(length);
            tensor = new short[size];
        }

        public Short(short[] tensor, int... length) {
            this.length = length;
            size = getTensorSize(length);
            if (tensor.length != size) {
                throw new IllegalArgumentException();
            }
            this.tensor = tensor;
        }

        @Override
        public Scalar.Short asScalar() {
            if (getRank() != 0) {
                throw new IllegalArgumentException();
            }
            return new Scalar.Short(tensor[0]);
        }

        @Override
        public Vector.Short asVector() {
            if (getRank() != 1) {
                throw new IllegalArgumentException();
            }
            return new Vector.Short(tensor);
        }

        @Override
        public Matrix.Short asMatrix() {
            if (getRank() != 2) {
                throw new IllegalArgumentException();
            }
            return new Matrix.Short(length[0], length[1], tensor);
        }
    }

    class Integer implements Tensor {
        final int[] tensor;
        final int[] length;
        final int size;

        @Override
        public int[] getData() {
            return tensor;
        }

        @Override
        public int getRank() {
            return length.length;
        }

        @Override
        public int[] getLengthData() {
            return length;
        }

        @Override
        public int getLength(int dimension) {
            return length[dimension];
        }

        @Override
        public int size() {
            return size;
        }

        public int get(int... indices) {
            return tensor[getIndex(indices, length)];
        }

        public void set(int[] indices, int value) {
            tensor[getIndex(indices, length)] = value;
        }

        public Integer(int... length) {
            this.length = length;
            size = getTensorSize(length);
            tensor = new int[size];
        }

        public Integer(int[] tensor, int... length) {
            this.length = length;
            size = getTensorSize(length);
            if (tensor.length != size) {
                throw new IllegalArgumentException();
            }
            this.tensor = tensor;
        }

        @Override
        public Scalar.Integer asScalar() {
            if (getRank() != 0) {
                throw new IllegalArgumentException();
            }
            return new Scalar.Integer(tensor[0]);
        }

        @Override
        public Vector.Integer asVector() {
            if (getRank() != 1) {
                throw new IllegalArgumentException();
            }
            return new Vector.Integer(tensor);
        }

        @Override
        public Matrix.Integer asMatrix() {
            if (getRank() != 2) {
                throw new IllegalArgumentException();
            }
            return new Matrix.Integer(length[0], length[1], tensor);
        }
    }

    class Long implements Tensor {
        final long[] tensor;
        final int[] length;
        final int size;

        @Override
        public long[] getData() {
            return tensor;
        }

        @Override
        public int getRank() {
            return length.length;
        }

        @Override
        public int[] getLengthData() {
            return length;
        }

        @Override
        public int getLength(int dimension) {
            return length[dimension];
        }

        @Override
        public int size() {
            return size;
        }

        public long get(int... indices) {
            return tensor[getIndex(indices, length)];
        }

        public void set(int[] indices, long value) {
            tensor[getIndex(indices, length)] = value;
        }

        public Long(int... length) {
            this.length = length;
            size = getTensorSize(length);
            tensor = new long[size];
        }

        public Long(long[] tensor, int... length) {
            this.length = length;
            size = getTensorSize(length);
            if (tensor.length != size) {
                throw new IllegalArgumentException();
            }
            this.tensor = tensor;
        }

        @Override
        public Scalar.Long asScalar() {
            if (getRank() != 0) {
                throw new IllegalArgumentException();
            }
            return new Scalar.Long(tensor[0]);
        }

        @Override
        public Vector.Long asVector() {
            if (getRank() != 1) {
                throw new IllegalArgumentException();
            }
            return new Vector.Long(tensor);
        }

        @Override
        public Matrix.Long asMatrix() {
            if (getRank() != 2) {
                throw new IllegalArgumentException();
            }
            return new Matrix.Long(length[0], length[1], tensor);
        }
    }

    class Float implements Tensor {
        final float[] tensor;
        final int[] length;
        final int size;

        @Override
        public float[] getData() {
            return tensor;
        }

        @Override
        public int getRank() {
            return length.length;
        }

        @Override
        public int[] getLengthData() {
            return length;
        }

        @Override
        public int getLength(int dimension) {
            return length[dimension];
        }

        @Override
        public int size() {
            return size;
        }

        public float get(int... indices) {
            return tensor[getIndex(indices, length)];
        }

        public void set(int[] indices, float value) {
            tensor[getIndex(indices, length)] = value;
        }

        public Float(int... length) {
            this.length = length;
            size = getTensorSize(length);
            tensor = new float[size];
        }

        public Float(float[] tensor, int... length) {
            this.length = length;
            size = getTensorSize(length);
            if (tensor.length != size) {
                throw new IllegalArgumentException();
            }
            this.tensor = tensor;
        }

        @Override
        public Scalar.Float asScalar() {
            if (getRank() != 0) {
                throw new IllegalArgumentException();
            }
            return new Scalar.Float(tensor[0]);
        }

        @Override
        public Vector.Float asVector() {
            if (getRank() != 1) {
                throw new IllegalArgumentException();
            }
            return new Vector.Float(tensor);
        }

        @Override
        public Matrix.Float asMatrix() {
            if (getRank() != 2) {
                throw new IllegalArgumentException();
            }
            return new Matrix.Float(length[0], length[1], tensor);
        }
    }

    class Double implements Tensor {
        final double[] tensor;
        final int[] length;
        final int size;

        @Override
        public double[] getData() {
            return tensor;
        }

        @Override
        public int getRank() {
            return length.length;
        }

        @Override
        public int[] getLengthData() {
            return length;
        }

        @Override
        public int getLength(int dimension) {
            return length[dimension];
        }

        @Override
        public int size() {
            return size;
        }

        public double get(int... indices) {
            return tensor[getIndex(indices, length)];
        }

        public void set(int[] indices, double value) {
            tensor[getIndex(indices, length)] = value;
        }

        public Double(int... length) {
            this.length = length;
            size = getTensorSize(length);
            tensor = new double[size];
        }

        public Double(double[] tensor, int... length) {
            this.length = length;
            size = getTensorSize(length);
            if (tensor.length != size) {
                throw new IllegalArgumentException();
            }
            this.tensor = tensor;
        }

        @Override
        public Scalar.Double asScalar() {
            if (getRank() != 0) {
                throw new IllegalArgumentException();
            }
            return new Scalar.Double(tensor[0]);
        }

        @Override
        public Vector.Double asVector() {
            if (getRank() != 1) {
                throw new IllegalArgumentException();
            }
            return new Vector.Double(tensor);
        }

        @Override
        public Matrix.Double asMatrix() {
            if (getRank() != 2) {
                throw new IllegalArgumentException();
            }
            return new Matrix.Double(length[0], length[1], tensor);
        }
    }
}

