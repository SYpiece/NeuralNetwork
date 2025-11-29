package util.network.function;

import util.math.function.Domain;
import util.math.function.Domains;

public interface ActivationFunction extends NetworkFunction {
    int
            NONE = 0,
            SIGMOID = 1,
            TANH = 2,
            RELU = 3,
            LEAKY_RELU = 4,
            SOFTMAX = 5;

    int getActivationFunctionType();

    float calculateDerivative(float x);

    double calculateDerivative(double x);

    @Override
    default Domain getDomain() {
        return Domains.Real;
    }
}
