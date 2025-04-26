package util.network;

import util.math.matrix.*;

import java.util.Random;

import static util.calculator.MatrixCalculators.*;

public class Layers {
    public static Layer createFullyConnectedLayer(int inputSize, int outputSize, NetworkFunction activationFunction) {
        return new Layer() {
            private Matrix weights = Matrices.createMatrix(inputSize, outputSize);
            private Matrix biases = Matrices.createMatrix(outputSize, 1);

            {
                Random random = new Random();
                for (int i = 0; i < getInputSize(); i++) {
                    for (int j = 0; j < getOutputSize(); j++) {
                        weights.set(i, j, random.nextDouble() - .5);
                    }
                }
                for (int i = 0; i < getOutputSize(); i++) {
                    biases.set(i, 0, random.nextDouble() - .5);
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
            public Matrix train(Matrix input, Matrix output, Matrix error, double learningRate) {
                if (input.size() != getInputSize()) {
                    throw new IllegalArgumentException("Input size is not correct");
                }
                if (output.size() != getOutputSize()) {
                    throw new IllegalArgumentException("Output size is not correct");
                }
                if (error.size() != getOutputSize()) {
                    throw new IllegalArgumentException("Error size is not correct");
                }

                final Matrix delta = dot(error, function(output, activationFunction.getDerivativeFunction()));
                weights = subtract(weights, multiply(multiply(input, delta.toTransposed()), learningRate));
                biases = subtract(biases, multiply(delta, learningRate));
                return multiply(weights, delta);
            }

            @Override
            public Matrix compute(Matrix input) {
                if (input.size() != getInputSize()) {
                    throw new IllegalArgumentException("Input size is not correct");
                }
                return function(add(multiply(weights.toTransposed(), input), biases), activationFunction);
            }
        };
    }

    private Layers() {}
}
