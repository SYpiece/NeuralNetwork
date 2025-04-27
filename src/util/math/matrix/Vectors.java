package util.math.matrix;

public class Vectors extends Matrices {
    public static Vector createVector(int size) {
        return new VectorImpl(size);
    }

    public static ColumnVector createColumnVector(int size) {
        return new ColumnVectorImpl(size);
    }

    public static RowVector createRowVector(int size) {
        return new RowVectorImpl(size);
    }

    public static Vector createVector(double... vector) {
        if (vector == null) {
            throw new IllegalArgumentException("Vector must not be null");
        }
        return new VectorImpl(vector);
    }

    public static ColumnVector createColumnVector(double... vector) {
        if (vector == null) {
            throw new IllegalArgumentException("Vector must not be null");
        }
        return new ColumnVectorImpl(vector);
    }

    public static RowVector createRowVector(double... vector) {
        if (vector == null) {
            throw new IllegalArgumentException("Vector must not be null");
        }
        return new RowVectorImpl(vector);
    }

    private static class VectorImpl implements Vector {
        protected final double[] vector;

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

    private static class ColumnVectorImpl extends VectorImpl implements ColumnVector {
        public ColumnVectorImpl(int size) {
            super(size);
        }

        public ColumnVectorImpl(double... vector) {
            super(vector);
        }

        @Override
        public Matrix toMatrix(Orientation orientation) {
            return ColumnVector.super.toMatrix(orientation);
        }
    }

    private static class RowVectorImpl extends VectorImpl implements RowVector {
        public RowVectorImpl(int size) {
            super(size);
        }

        public RowVectorImpl(double... vector) {
            super(vector);
        }

        @Override
        public Matrix toMatrix(Orientation orientation) {
            return RowVector.super.toMatrix(orientation);
        }
    }

    protected Vectors() {}
}
