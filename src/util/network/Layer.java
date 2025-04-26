package util.network;

import util.math.matrix.Matrix;
import util.math.matrix.Vector;

public interface Layer {
    /**
     * @return the number of inputs of the layer
     */
    int getInputSize();

    /**
     * @return the number of outputs of the layer
     */
    int getOutputSize();

    Matrix train(Matrix input, Matrix output, Matrix error, double learningRate);

    /**
     * @param input the input of the layer
     * @return the output of the layer
     */
    Matrix compute(Matrix input);
}
