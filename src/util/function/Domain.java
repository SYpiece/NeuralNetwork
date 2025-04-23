package util.function;

import java.util.List;

public interface Domain {
    List<Set> getSets();

    default boolean isInDomain(double x) {
        for (Set set : getSets()) {
            if (set.isInSet(x)) {
                return true;
            }
        }
        return false;
    }
}
