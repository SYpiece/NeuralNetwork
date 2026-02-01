package util.network;

import util.math.tensors.Matrix;
import util.math.tensors.Vector;
import util.network.layers.Layer;

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
