package util.network;

import util.function.DerivableFunction;
import util.matrix.Vector;

public interface Layer {
    /**
     * @return the number of inputs of the layer
     */
    int getInputSize();

    /**
     * @return the number of outputs of the layer
     */
    int getOutputSize();

    Vector train(Vector input, Vector output, Vector error, double learningRate);

    /**
     * @param input the input of the layer
     * @return the output of the layer
     */
    Vector compute(Vector input);
}
