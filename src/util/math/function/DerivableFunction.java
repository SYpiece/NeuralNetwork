package util.math.function;

public interface DerivableFunction extends Function {
    /**
     * @return the derivative function of this function
     */
    Function getDerivativeFunction();
}
