package util.network;

import util.math.matrix.Matrices;
import util.math.matrix.Matrix;
import util.math.matrix.Vector;

public class FullyConnectedLayer implements Layer {
    final int inputSize, outputSize;

    final Matrix weights;

    final Vector biases;

    @Override
    public int getInputSize() {
        return 0;
    }

    @Override
    public int getOutputSize() {
        return 0;
    }

    public Matrix getWeights() {
        return weights;
    }

    public FullyConnectedLayer(int inputSize, int outputSize) {
        this.inputSize = inputSize;
        this.outputSize = outputSize;
        weights = Matrices.createMatrix(inputSize, outputSize);
    }
}
