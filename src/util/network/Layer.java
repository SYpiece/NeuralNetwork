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

    Trainer getTrainer();
}
