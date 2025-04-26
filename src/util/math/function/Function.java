package util.math.function;

public interface Function {
    /**
     * @return the domain of the function
     */
    Domain getDomain();

    /**
     * @param x the argument of the function
     * @return the value of the function at x
     */
    double calculate(double x);
}
