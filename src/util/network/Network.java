package util.network;

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

    default double[] train(double[] input, double[] target, double learningRate) {
        final double[][] hidden = new double[getLayers().size() + 1][];
        if (input.length != getInputSize()) {
            throw new IllegalArgumentException("Input size is not correct");
        }
        if (target.length != getOutputSize()) {
            throw new IllegalArgumentException("Target size is not correct");
        }

        hidden[0] = input;
        final List<Layer> layers = getLayers();
        for (int i = 0; i < layers.size(); i++) {
            hidden[i + 1] = layers.get(i).compute(hidden[i]);
        }

        double[] error = new double[target.length];
        {
            final Layer layer = layers.get(layers.size() - 1);
            for (int i = 0; i < target.length; i++) {
                error[i] = hidden[layers.size()][i] - target[i];
            }
            error = layer.train(hidden[layers.size() - 1], hidden[layers.size()], error, learningRate);
        }
        for (int i = layers.size() - 2; i >= 0; i--) {
            final Layer layer = layers.get(i);
            error = layer.train(hidden[i], hidden[i + 1], error, learningRate);
        }

        return hidden[layers.size()];
    }

    default double[] compute(double[] input) {
        double[] hidden = input;
        for (Layer layer : getLayers()) {
            hidden = layer.compute(hidden);
        }
        return hidden;
    }
}
