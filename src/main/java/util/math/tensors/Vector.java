package util.math.tensors;

import java.util.Arrays;

public interface Vector extends Data {
    static Vector.Byte clone(Vector.Byte vector) {
        return new Vector.Byte(Arrays.copyOf(vector.vector, vector.vector.length));
    }

    static Vector.Short clone(Vector.Short vector) {
        return new Vector.Short(Arrays.copyOf(vector.vector, vector.vector.length));
    }

    static Vector.Integer clone(Vector.Integer vector) {
        return new Vector.Integer(Arrays.copyOf(vector.vector, vector.vector.length));
    }

    static Vector.Long clone(Vector.Long vector) {
        return new Vector.Long(Arrays.copyOf(vector.vector, vector.vector.length));
    }

    static Vector.Float clone(Vector.Float vector) {
        return new Vector.Float(Arrays.copyOf(vector.vector, vector.vector.length));
    }

    static Vector.Double clone(Vector.Double vector) {
        return new Vector.Double(Arrays.copyOf(vector.vector, vector.vector.length));
    }

    int size();

    Tensor asTensor();

    class Byte implements Vector {
        final byte[] vector;

        @Override
        public byte[] getData() {
            return vector;
        }

        @Override
        public int size() {
            return vector.length;
        }

        public byte get(int index) {
            return vector[index];
        }

        public void set(int index, byte value) {
            vector[index] = value;
        }

        public Byte(int size) {
            vector = new byte[size];
        }

        public Byte(byte[] vector) {
            this.vector = vector;
        }

        @Override
        public Tensor.Byte asTensor() {
            return new Tensor.Byte(vector, vector.length);
        }
    }

    class Short implements Vector {
        final short[] vector;

        @Override
        public short[] getData() {
            return vector;
        }

        @Override
        public int size() {
            return vector.length;
        }

        public short get(int index) {
            return vector[index];
        }

        public void set(int index, short value) {
            vector[index] = value;
        }

        public Short(int size) {
            vector = new short[size];
        }

        public Short(short[] vector) {
            this.vector = vector;
        }

        @Override
        public Tensor.Short asTensor() {
            return new Tensor.Short(vector, vector.length);
        }
    }

    class Integer implements Vector {
        final int[] vector;

        @Override
        public int[] getData() {
            return vector;
        }

        @Override
        public int size() {
            return vector.length;
        }

        public int get(int index) {
            return vector[index];
        }

        public void set(int index, int value) {
            vector[index] = value;
        }

        public Integer(int size) {
            vector = new int[size];
        }

        public Integer(int[] vector) {
            this.vector = vector;
        }

        @Override
        public Tensor.Integer asTensor() {
            return new Tensor.Integer(vector, vector.length);
        }
    }

    class Long implements Vector {
        final long[] vector;

        @Override
        public long[] getData() {
            return vector;
        }

        @Override
        public int size() {
            return vector.length;
        }

        public long get(int index) {
            return vector[index];
        }

        public void set(int index, long value) {
            vector[index] = value;
        }

        public Long(int size) {
            vector = new long[size];
        }

        public Long(long[] vector) {
            this.vector = vector;
        }

        @Override
        public Tensor.Long asTensor() {
            return new Tensor.Long(vector, vector.length);
        }
    }

    class Float implements Vector {
        final float[] vector;

        @Override
        public float[] getData() {
            return vector;
        }

        @Override
        public int size() {
            return vector.length;
        }

        public float get(int index) {
            return vector[index];
        }

        public void set(int index, float value) {
            vector[index] = value;
        }

        public Float(int size) {
            vector = new float[size];
        }

        public Float(float[] vector) {
            this.vector = vector;
        }

        @Override
        public Tensor.Float asTensor() {
            return new Tensor.Float(vector, vector.length);
        }
    }

    class Double implements Vector {
        final double[] vector;

        @Override
        public double[] getData() {
            return vector;
        }

        @Override
        public int size() {
            return vector.length;
        }

        public double get(int index) {
            return vector[index];
        }

        public void set(int index, double value) {
            vector[index] = value;
        }

        public Double(int size) {
            vector = new double[size];
        }

        public Double(double[] vector) {
            this.vector = vector;
        }

        @Override
        public Tensor.Double asTensor() {
            return new Tensor.Double(vector, vector.length);
        }
    }
}
