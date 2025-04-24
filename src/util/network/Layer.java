package util.network;

import util.function.DerivableFunction;

public interface Layer {
    int getInputSize();

    int getOutputSize();

    double[] train(double[] input, double[] output, double[] error, double learningRate);

    double[] compute(double[] input);
}
