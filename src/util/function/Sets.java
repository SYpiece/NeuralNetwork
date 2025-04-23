package util.function;

public class Sets {
    public final static Set Real = value -> true;

    public static Set createPointSet(double x) {
        return value -> x == value;
    }

    public static Set createPointsSet(double... x) {
        return value -> {
            for (double point : x) {
                if (point == value) {
                    return true;
                }
            }
            return false;
        };
    }

    private Sets() {}
}
