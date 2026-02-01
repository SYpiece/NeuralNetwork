package util.math.matrix;

public interface Scalar {
    class Byte implements Scalar {
        byte value;

        public Byte() {
            this((byte) 0);
        }

        public Byte(byte value) {
            this.value = value;
        }

        public byte get() {
            return value;
        }

        public void set(byte value) {
            this.value = value;
        }
    }

    class Short implements Scalar {
        short value;

        public Short() {
            this((short) 0);
        }

        public Short(short value) {
            this.value = value;
        }

        public short get() {
            return value;
        }

        public void set(short value) {
            this.value = value;
        }
    }

    class Integer implements Scalar {
        int value;

        public Integer() {
            this(0);
        }

        public Integer(int value) {
            this.value = value;
        }

        public int get() {
            return value;
        }

        public void set(int value) {
            this.value = value;
        }
    }

    class Long implements Scalar {
        long value;

        public Long() {
            this(0);
        }

        public Long(long value) {
            this.value = value;
        }

        public long get() {
            return value;
        }

        public void set(long value) {
            this.value = value;
        }
    }

    class Float implements Scalar {
        float value;

        public Float() {
            this(0);
        }

        public Float(float value) {
            this.value = value;
        }

        public float get() {
            return value;
        }

        public void set(float value) {
            this.value = value;
        }
    }

    class Double implements Scalar {
        double value;

        public Double() {
            this(0);
        }

        public Double(double value) {
            this.value = value;
        }

        public double get() {
            return value;
        }

        public void set(double value) {
            this.value = value;
        }
    }
}
