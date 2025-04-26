package util.math.function;

import java.util.Arrays;
import java.util.Collections;

public class Domains {
    public static final Domain Real = () -> Collections.singletonList(Sets.Real);

    public static Domain createPointDomain(double x)
    {
        return () -> Collections.singletonList(Sets.createPointSet(x));
    }

    public static Domain createPointsDomain(double... x)
    {
        return () -> Collections.singletonList(Sets.createPointsSet(x));
    }

    public static Domain createIntervalDomain(Interval interval)
    {
        return () -> Collections.singletonList(interval);
    }

    public static Domain createIntervalsDomain(Interval... intervals)
    {
        return () -> Arrays.asList(intervals);
    }

    public static Domain createSetDomain(Set set)
    {
        return () -> Collections.singletonList(set);
    }

    public static Domain createSetsDomain(Set... sets)
    {
        return () -> Arrays.asList(sets);
    }

    private Domains() {}
}
