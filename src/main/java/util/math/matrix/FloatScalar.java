package util.math.matrix;

public class FloatScalar implements Scalar {
    final float[] value;

    public float[] getData() {
        return value;
    }

    public float get() {
        return value[0];
    }

    public void set(float value) {
        this.value[0] = value;
    }

    public FloatScalar() {
        this(0);
    }

    public FloatScalar(float value) {
        this.value = new float[] {value};
    }
}
