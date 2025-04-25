package util.matrix;

public class VectorImpl implements Vector, Cloneable {
    private double[] vector;

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

    VectorImpl() {}

    VectorImpl(double[] vector) {
        this.vector = vector;
    }

    VectorImpl(int size) {
        this.vector = new double[size];
    }

    @Override
    public VectorImpl clone() {
        try {
            VectorImpl clone = (VectorImpl) super.clone();
            for (int i = 0; i < vector.length; i++) {
                clone.vector[i] = get(i);
            }
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(super.toString()).append("[");
        for (int i = 0; i < vector.length; i++) {
            builder.append(get(i));
            if (i < vector.length - 1) {
                builder.append("\t");
            }
        }
        builder.append("]");
        return builder.toString();
    }
}
