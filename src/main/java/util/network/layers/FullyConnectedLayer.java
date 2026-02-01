package util.network.layers;

import util.math.tensors.Matrix;
import util.math.tensors.Tensor;
import util.math.tensors.Vector;
import util.network.function.ActivationFunction;

public class FullyConnectedLayer extends AbstractLayer {
    Matrix weights;
    Vector biases;
    ActivationFunction activationFunction;

    public Matrix getWeights() {
        return weights;
    }

    public FullyConnectedLayer setWeights(Matrix weights) {
        this.weights = weights;
        return this;
    }

    public Vector getBiases() {
        return biases;
    }

    public FullyConnectedLayer setBiases(Vector biases) {
        this.biases = biases;
        return this;
    }

    public ActivationFunction getActivationFunction() {
        return activationFunction;
    }

    public FullyConnectedLayer setActivationFunction(ActivationFunction activationFunction) {
        this.activationFunction = activationFunction;
        return this;
    }

    public FullyConnectedLayer() {}

    @Override
    public Trainer createTrainer() {
        return new Trainer(weights, biases, activationFunction);
    }

    @Override
    public Computer createComputer() {
        return new Computer(weights, biases, activationFunction);
    }

    public class Trainer extends AbstractLayer.AbstractTrainer {
        Matrix weights;
        Vector biases;
        ActivationFunction activationFunction;

        Trainer(Matrix weights, Vector biases, ActivationFunction activationFunction) {
            this.weights = weights;
            this.biases = biases;
            this.activationFunction = activationFunction;
        }

        @Override
        public FullyConnectedLayer getLayer() {
            return FullyConnectedLayer.this;
        }

        @Override
        public void apply() {
            getLayer().setWeights(weights).setBiases(biases).setActivationFunction(activationFunction);
        }
    }

    public class Computer extends AbstractLayer.AbstractComputer {
        Matrix weights;
        Vector biases;
        ActivationFunction activationFunction;

        Computer(Matrix weights, Vector biases, ActivationFunction activationFunction) {
            this.weights = weights;
            this.biases = biases;
            this.activationFunction = activationFunction;
        }

        @Override
        public Tensor compute(Tensor input) {
            return null;
        }
    }
}
