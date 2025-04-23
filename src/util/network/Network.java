package util.network;

import util.function.Function;

import java.util.List;

public interface Network {
    List<Layer> getLayers();

    default int getInputSize() {
        return getLayers().get(0).getInputSize();
    }

    default int getOutputSize() {
        return getLayers().get(getLayers().size() - 1).getOutputSize();
    }

    default void train(double[] input, double[] target, double learningRate) {
        double[][] hidden = new double[getLayers().size() + 1][];
        if (input.length != getInputSize()) {
            throw new IllegalArgumentException("Input size is not correct");
        }

        hidden[0] = input;
        for (int i = 0; i < getLayers().size(); i++) {
            hidden[i + 1] = getLayers().get(i).compute(hidden[i]);
        }

        double[][] error = new double[getLayers().size()][];
        double[][] delta = new double[getLayers().size()][];
        for (int i = getLayers().size() - 1; i >= 0; i--) {
            final Layer layer = getLayers().get(i);
            final Function derivativeFunction = layer.getActivationFunction().getDerivativeFunction();
            error[i] = new double[layer.getOutputSize()];
            for (int j = 0; j < layer.getOutputSize(); j++) {
                error[i][j] =
                delta[i][j] = error[i][j] * derivativeFunction.calculate(hidden[i + 1][j]);
            }
        }
    }

    default double[] calculate(double[] input) {
        double[] hidden = input;
        for (Layer layer : getLayers()) {
            hidden = layer.compute(hidden);
        }
        return hidden;
    }

}
