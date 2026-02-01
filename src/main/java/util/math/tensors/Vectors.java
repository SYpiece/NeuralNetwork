package util.math.tensors;

public class Vectors extends Scalars {
    public static FloatVector createFloatVector(int size) {
        return new FloatVectorImpl(size);
    }

    public static FloatVector createFloatVector(float[] vector) {
        return new FloatVectorImpl(vector);
    }

    public static DoubleVector createDoubleVector(int size) {
        return new DoubleVectorImpl(size);
    }

    public static DoubleVector createDoubleVector(double[] vector) {
        return new DoubleVectorImpl(vector);
    }

    private static class FloatVectorImpl implements FloatVector {
        final float[] vector;

        FloatVectorImpl(int size) {
            vector = new float[size];
        }

        FloatVectorImpl(float[] vector) {
            this.vector = vector;
        }

        @Override
        public int size() {
            return vector.length;
        }

        @Override
        public float[] getData() {
            return vector;
        }

        @Override
        public float get(int index) {
            return vector[index];
        }

        @Override
        public void set(int index, float value) {
            vector[index] = value;
        }
    }

    private static class DoubleVectorImpl implements DoubleVector {
        final double[] vector;

        DoubleVectorImpl(int size) {
            vector = new double[size];
        }

        DoubleVectorImpl(double[] vector) {
            this.vector = vector;
        }

        @Override
        public int size() {
            return vector.length;
        }

        @Override
        public double[] getData() {
            return vector;
        }

        @Override
        public double get(int index) {
            return vector[index];
        }

        @Override
        public void set(int index, double value) {
            vector[index] = value;
        }
    }

    protected Vectors() {}
}
