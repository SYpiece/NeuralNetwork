package util.network;

import util.math.matrix.Matrix;
import util.math.matrix.Orientation;
import util.math.matrix.Vector;

import java.util.List;

import static util.calculator.MatrixCalculators.*;

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

    default Matrix train(Matrix input, Matrix target, double learningRate) {
        final List<Layer> layers = getLayers();
        final Matrix[] hidden = new Matrix[layers.size() + 1];
        if (input.size() != getInputSize()) {
            throw new IllegalArgumentException("Input size is not correct");
        }
        if (target.size() != getOutputSize()) {
            throw new IllegalArgumentException("Target size is not correct");
        }

//        long start = System.nanoTime();

        hidden[0] = input;
        for (int i = 0; i < layers.size(); i++) {
            hidden[i + 1] = layers.get(i).compute(hidden[i]);
        }


//        System.out.println("Computation Use Time: " + (System.nanoTime() - start));
//        start = System.nanoTime();

        Matrix error = subtract(hidden[layers.size()], target);
//        Vector error = hidden[layers.size()].subtract(target);
        for (int i = layers.size() - 1; i >= 0; i--) {
            final Layer layer = layers.get(i);
            error = layer.train(hidden[i], hidden[i + 1], error, learningRate);
        }

//        System.out.println("Training Use Time: " + (System.nanoTime() - start));
        return hidden[layers.size()];
    }

    default Vector compute(Vector input) {
        Vector hidden = input;
        for (Layer layer : getLayers()) {
            hidden = layer.compute(hidden.toMatrix(Orientation.TopToBottom)).toVector(Orientation.TopToBottom);
        }
        return hidden;
    }
}
