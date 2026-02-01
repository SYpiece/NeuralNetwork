package util.math.tensors;

public class Scalars {
    public static FloatScalar createFloatScalar() {
        return new FloatScalarImpl();
    }

    public static FloatScalar createFloatScalar(float value) {
        return new FloatScalarImpl(value);
    }

    public static DoubleScalar createDoubleScalar() {
        return new DoubleScalarImpl();
    }

    public static DoubleScalar createDoubleScalar(double value) {
        return new DoubleScalarImpl(value);
    }

    private static class FloatScalarImpl implements FloatScalar, Cloneable {
        final float[] data;

        FloatScalarImpl() {
            this.data = new float[1];
        }

        FloatScalarImpl(float value) {
            this.data = new float[] {value};
        }

        @Override
        public float[] getData() {
            return data;
        }

        @Override
        public float get() {
            return data[0];
        }

        @Override
        public void set(float value) {
            this.data[0] = value;
        }

        @Override
        public FloatScalarImpl clone() {
            try {
                FloatScalarImpl clone = (FloatScalarImpl) super.clone();
                System.arraycopy(this.data, 0, clone.data, 0, this.data.length);
                return clone;
            } catch (CloneNotSupportedException e) {
                throw new AssertionError();
            }
        }
    }

    private static class DoubleScalarImpl implements DoubleScalar, Cloneable {
        final double[] data;

        DoubleScalarImpl() {
            this.data = new double[1];
        }

        DoubleScalarImpl(double value) {
            this.data = new double[] {value};
        }

        @Override
        public double[] getData() {
            return data;
        }

        @Override
        public double get() {
            return data[0];
        }

        @Override
        public void set(double value) {
            this.data[0] = value;
        }

        @Override
        public DoubleScalarImpl clone() {
            try {
                DoubleScalarImpl clone = (DoubleScalarImpl) super.clone();
                System.arraycopy(this.data, 0, clone.data, 0, this.data.length);
                return clone;
            } catch (CloneNotSupportedException e) {
                throw new AssertionError();
            }
        }
    }
}
