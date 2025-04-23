package util.network;

import util.function.Function;

import java.util.Random;

public class Layers {
    public static Layer createFullyConnectedLayer(int inputSize, int outputSize, NetworkFunction activationFunction) {
        return new Layer() {
            private final double[][] weights = new double[getOutputSize()][getInputSize()];
            private final double[] biases = new double[getOutputSize()];

            {
                Random random = new Random();
                for (int i = 0; i < getOutputSize(); i++) {
                    for (int j = 0; j < getInputSize(); j++) {
                        weights[i][j] = random.nextDouble() * 2 - 1;
                    }
                    biases[i] = random.nextDouble() * 2 - 1;
                }
            }

            @Override
            public NetworkFunction getActivationFunction() {
                return activationFunction;
            }

            @Override
            public int getInputSize() {
                return inputSize;
            }

            @Override
            public int getOutputSize() {
                return outputSize;
            }

            @Override
            public double[] train(double[] input, double[] target, double learningRate) {
                if (derivatives.length != getOutputSize()) {
                    throw new IllegalArgumentException("Derivatives size is not correct");
                }
            }

            @Override
            public double[] compute(double[] input) {
                if (input.length != getInputSize()) {
                    throw new IllegalArgumentException("Input size is not correct");
                }
                double[] output = new double[getOutputSize()];
                for (int i = 0; i < getOutputSize(); i++) {
                    double sum = 0;
                    for (int j = 0; j < getInputSize(); j++) {
                        sum += input[j] * weights[i][j];
                    }
                    output[i] = activationFunction.calculate(sum + biases[i]);
//                    output[i] = activationFunction.calculate(sum);
                }
                return output;
            }
        };
    }

    private Layers() {}
}
