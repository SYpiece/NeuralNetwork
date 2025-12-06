package util.math.function;

public interface Interval extends Set {
    /**
     * @return the type of the left point
     */
    IntervalType getLeftType();

    /**
     * @return the left point of the interval
     */
    double getLeftPoint();

    /**
     * @return the type of the right point
     */
    IntervalType getRightType();

    /**
     * @return the right point of the interval
     */
    double getRightPoint();

    /**
     * @param x the point to check
     * @return true if the point is in the interval, false otherwise
     */
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

    /**
     * The type of the interval's endpoints
     */
    enum IntervalType {
        Closed,
        Open,
    }
}
