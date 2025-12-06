package util.math.function;

public interface Set {
    /**
     * @param x the point to check
     * @return true if x is in the set, false otherwise
     */
    boolean isInSet(double x);

    /**
     * @param set the set to union with
     * @return the union of this set and the given set
     */
    default Set union(Set set) {
        return value -> isInSet(value) || set.isInSet(value);
    }

    /**
     * @param set the set to intersect with
     * @return the intersection of this set and the given set
     */
    default Set intersect(Set set) {
        return value -> isInSet(value) && set.isInSet(value);
    }

    /**
     * @param set the set to subtract from this set
     * @return the set difference of this set and the given set
     */
    default Set except(Set set) {
        return value -> isInSet(value) && !set.isInSet(value);
    }

    /**
     * @return the complement of this set
     */
    default Set complement() {
        return value -> !isInSet(value);
    }
}
