package util.network;

import util.function.DerivableFunction;

public interface Layer {
    /**
     * @return the number of inputs of the layer
     */
    int getInputSize();

    /**
     * @return the number of outputs of the layer
     */
    int getOutputSize();

    double[] train(double[] input, double[] output, double[] error, double learningRate);

    /**
     * @param input the input of the layer
     * @return the output of the layer
     */
    double[] compute(double[] input);
}
