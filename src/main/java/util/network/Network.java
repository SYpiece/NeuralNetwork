package util.network;

import util.math.matrix.Matrix;
import util.math.matrix.Orientation;
import util.math.matrix.Vector;

import java.util.List;

public interface Network {
    /**
     * @return layers of the network
     */
    List<Layer> getLayers();

    default Matrix train(Matrix input, Matrix target, double learningRate) {
        return null;
    }

    default Vector compute(Vector input) {
        return null;
    }
}
