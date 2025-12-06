package util.math.function;

public class Intervals {
    public final static Interval Real = new Interval() {
        @Override
        public IntervalType getLeftType() {
            return IntervalType.Open;
        }

        @Override
        public double getLeftPoint() {
            return Double.NEGATIVE_INFINITY;
        }

        @Override
        public IntervalType getRightType() {
            return IntervalType.Open;
        }

        @Override
        public double getRightPoint() {
            return Double.POSITIVE_INFINITY;
        }

        @Override
        public boolean isInInterval(double x) {
            return true;
        }
    };

    public static Interval createPointInterval(double x) {
        return new Interval() {
            @Override
            public IntervalType getLeftType() {
                return IntervalType.Closed;
            }

            @Override
            public double getLeftPoint() {
                return x;
            }

            @Override
            public IntervalType getRightType() {
                return IntervalType.Closed;
            }

            @Override
            public double getRightPoint() {
                return x;
            }

            @Override
            public boolean isInInterval(double value) {
                return x == value;
            }
        };
    }

    public static Interval createInterval(double left, Interval.IntervalType leftType, double right, Interval.IntervalType rightType) {
        if (left > right || (left == right && (leftType == Interval.IntervalType.Open || rightType == Interval.IntervalType.Open))) {
            throw new IllegalArgumentException("Invalid interval");
        }
        return new Interval() {
            @Override
            public IntervalType getLeftType() {
                return leftType;
            }

            @Override
            public double getLeftPoint() {
                return left;
            }

            @Override
            public IntervalType getRightType() {
                return rightType;
            }

            @Override
            public double getRightPoint() {
                return right;
            }
        };
    }

    private Intervals() {}
}
