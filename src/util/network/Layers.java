package util.network;

import util.function.DerivableFunction;
import util.function.Function;
import util.matrix.Matrices;
import util.matrix.Matrix;
import util.matrix.Vector;
import util.matrix.Vectors;

import java.util.Random;

public class Layers {
    public static Layer createFullyConnectedLayer(int inputSize, int outputSize, NetworkFunction activationFunction) {
        return new Layer() {
            private Matrix weights = Matrices.createMatrix(inputSize, outputSize);
            private Vector biases = Vectors.createVector(outputSize);

            {
                Random random = new Random();
                for (int i = 0; i < getInputSize(); i++) {
                    for (int j = 0; j < getOutputSize(); j++) {
                        weights.set(i, j, random.nextDouble() - .5);
                    }
                }
                for (int j = 0; j < getOutputSize(); j++) {
                    biases.set(j, random.nextDouble() - .5);
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
            public Vector train(Vector input, Vector output, Vector error, double learningRate) {
                if (input.size() != getInputSize()) {
                    throw new IllegalArgumentException("Input size is not correct");
                }
                if (output.size() != getOutputSize()) {
                    throw new IllegalArgumentException("Output size is not correct");
                }
                if (error.size() != getOutputSize()) {
                    throw new IllegalArgumentException("Error size is not correct");
                }

                final Vector delta = error.dot(activationFunction.getDerivativeFunction().calculate(output));
                weights = weights.subtract(input.multiply(delta.transpose()).multiply(learningRate));
                biases = biases.subtract(delta.multiply(learningRate));
                return weights.multiply(delta);
            }

            @Override
            public Vector compute(Vector input) {
                if (input.size() != getInputSize()) {
                    throw new IllegalArgumentException("Input size is not correct");
                }
                return activationFunction.calculate(weights.transpose().multiply(input).add(biases));
            }
        };
    }

    private Layers() {}
}
