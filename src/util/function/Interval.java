package util.function;

public interface Interval extends Set {
    IntervalType getLeftType();

    double getLeftPoint();

    IntervalType getRightType();

    double getRightPoint();

    default boolean isInInterval(double x) {
        return ((getLeftType() == IntervalType.Closed && x >= getLeftPoint())
                || (getLeftType() == IntervalType.Open && x > getLeftPoint()))
                && ((getRightType() == IntervalType.Closed && x <= getRightPoint())
                || (getRightType() == IntervalType.Open && x < getRightPoint()));
    }

    @Override
    default boolean isInSet(double x) {
        return isInInterval(x);
    }

    enum IntervalType {
        Closed,
        Open,
    }
}
