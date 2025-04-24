package util.network;

import util.function.DerivableFunction;
import util.function.Function;

import java.util.Random;

public class Layers {
    public static Layer createFullyConnectedLayer(int inputSize, int outputSize, DerivableFunction activationFunction) {
        return new Layer() {
            private final double[][] weights = new double[getInputSize()][getOutputSize()];
            private final double[] biases = new double[getOutputSize()];

            {
                Random random = new Random();
                for (int i = 0; i < getInputSize(); i++) {
                    for (int j = 0; j < getOutputSize(); j++) {
                        weights[i][j] = random.nextDouble() - .5;
                    }
                }
                for (int j = 0; j < getOutputSize(); j++) {
                    biases[j] = random.nextDouble() - .5;
                }
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
            public double[] train(double[] input, double[] output, double[] error, double learningRate) {
                if (input.length != getInputSize()) {
                    throw new IllegalArgumentException("Input size is not correct");
                }
                if (output.length != getOutputSize()) {
                    throw new IllegalArgumentException("Output size is not correct");
                }
                if (error.length != getOutputSize()) {
                    throw new IllegalArgumentException("Error size is not correct");
                }

                final double[] delta = new double[getOutputSize()];
                final Function derivativeFunction = activationFunction.getDerivativeFunction();
                for (int i = 0; i < getOutputSize(); i++) {
                    delta[i] = error[i] * derivativeFunction.calculate(output[i]);
                }

                for (int i = 0; i < getInputSize(); i++) {
                    for (int j = 0; j < getOutputSize(); j++) {
                        weights[i][j] -= learningRate * delta[j] * input[i];
                    }
                }
                for (int j = 0; j < getOutputSize(); j++) {
                    biases[j] -= learningRate * delta[j];
                }

                error = new double[getInputSize()];
                for (int i = 0; i < getInputSize(); i++) {
                    for (int j = 0; j < getOutputSize(); j++) {
                        error[i] += weights[i][j] * delta[j];
                    }
                }
                return error;
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
                        sum += weights[j][i] * input[j];
                    }
                    output[i] = activationFunction.calculate(sum + biases[i]);
                }
                return output;
            }
        };
    }

    private Layers() {}
}
