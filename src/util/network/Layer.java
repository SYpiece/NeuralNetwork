package util.network;

public interface Layer {
    NetworkFunction getActivationFunction();

    int getInputSize();

    int getOutputSize();

    double[] train(double[] output, double[] target, double learningRate);

    double[] compute(double[] input);

    double[] computeReversely(double[] output);
}
