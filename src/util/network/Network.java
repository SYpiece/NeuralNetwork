package util.network;

import util.matrix.Vector;
import util.matrix.Vectors;

import java.util.List;

public interface Network {
    /**
     * @return layers of the network
     */
    List<Layer> getLayers();

    /**
     * @return input size of the network
     */
    default int getInputSize() {
        return getLayers().get(0).getInputSize();
    }

    /**
     * @return output size of the network
     */
    default int getOutputSize() {
        return getLayers().get(getLayers().size() - 1).getOutputSize();
    }

    default Vector train(Vector input, Vector target, double learningRate) {
        final List<Layer> layers = getLayers();
        final Vector[] hidden = new Vector[layers.size() + 1];
        if (input.size() != getInputSize()) {
            throw new IllegalArgumentException("Input size is not correct");
        }
        if (target.size() != getOutputSize()) {
            throw new IllegalArgumentException("Target size is not correct");
        }

        hidden[0] = input;
        for (int i = 0; i < layers.size(); i++) {
            hidden[i + 1] = layers.get(i).compute(hidden[i]);
        }

        Vector error = hidden[layers.size()].subtract(target);
        for (int i = layers.size() - 1; i >= 0; i--) {
            final Layer layer = layers.get(i);
            error = layer.train(hidden[i], hidden[i + 1], error, learningRate);
        }

        return hidden[layers.size()];
    }

    default Vector compute(Vector input) {
        Vector hidden = input;
        for (Layer layer : getLayers()) {
            hidden = layer.compute(hidden);
        }
        return hidden;
    }
}
