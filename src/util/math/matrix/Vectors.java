package util.math.matrix;

public class Vectors extends Matrices {
    public static Vector createVector(int size) {
        return new VectorImpl(size);
    }

    public static Vector createVector(double... vector) {
        if (vector == null) {
            throw new IllegalArgumentException("Vector must not be null");
        }
        return new VectorImpl(vector);
    }

    private static class VectorImpl implements Vector {
        private final double[] vector;

        public VectorImpl(int size) {
            this.vector = new double[size];
        }

        public VectorImpl(double... vector) {
            this.vector = vector;
        }

        @Override
        public int size() {
            return vector.length;
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
