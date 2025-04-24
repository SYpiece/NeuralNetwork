package util.function;

import java.util.List;

public interface Domain {
    /**
     * @return the sets that define the domain
     */
    List<Set> getSets();

    /**
     * @param x the point to check
     * @return true if the point is in the domain
     */
    default boolean isInDomain(double x) {
        for (Set set : getSets()) {
            if (set.isInSet(x)) {
                return true;
            }
        }
        return false;
    }
}
