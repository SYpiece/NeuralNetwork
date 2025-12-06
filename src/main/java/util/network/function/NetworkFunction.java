package util.network.function;

import util.math.function.DoubleFunction;
import util.math.function.FloatFunction;

public interface NetworkFunction extends FloatFunction, DoubleFunction {
    int
            NONE = 0,
            ACTIVATION_SIGMOID = 1,
            ACTIVATION_TANH = 2,
            ACTIVATION_RELU = 3,
            ACTIVATION_LEAKY_RELU = 4,
            ACTIVATION_SOFTMAX = 5;

    int getFunctionType();
}
