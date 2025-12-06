package util.network.function;

import util.math.function.Domain;
import util.math.function.Domains;

public interface ActivationFunction extends NetworkFunction {
    float calculateDerivative(float x);

    double calculateDerivative(double x);

    @Override
    default Domain getDomain() {
        return Domains.Real;
    }
}
