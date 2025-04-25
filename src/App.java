import util.matrix.*;
import util.network.Layers;
import util.network.Network;
import util.network.NetworkFunctions;
import util.network.Networks;

public class App {
    public static void main(String[] args) {
        exampleA();
    }

    private static void test() {
        Matrix
                a = Matrices.createMatrix(new double[][] {
                        {1, 2},
                        {3, 4}
                }),
                b = Matrices.createMatrix(new double[][] {
                        {1, 2},
                        {3, 4}
                }),
                c = a.add(b);
        System.out.println(a);
        System.out.println(b);
        System.out.println(c);
    }

    /**
     * Example A Xor
     */
    private static void exampleA() {
        System.out.println("Example A Xor");
        Matrices.setComputingMode(ComputingMode.Single);
        final Network network = Networks.createNetwork(
                Layers.createFullyConnectedLayer(2, 1000, NetworkFunctions.Sigmoid),
                Layers.createFullyConnectedLayer(1000, 1000, NetworkFunctions.Sigmoid),
                Layers.createFullyConnectedLayer(1000, 1, NetworkFunctions.Sigmoid)
        );
        final Vector[] inputs = {
                Vectors.createVector(0, 0),
                Vectors.createVector(0, 1),
                Vectors.createVector(1, 0),
                Vectors.createVector(1, 1),
        };
        final Vector[] targets = {
                Vectors.createVector(0.0),
                Vectors.createVector(1.0),
                Vectors.createVector(1.0),
                Vectors.createVector(0.0),
        };
        final int epochs = 100000;
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < epochs; i++) {
            double totalError = 0;
            for (int j = 0; j < inputs.length; j++) {
                double output = network.train(inputs[j], targets[j], .1).get(0);
                double error = Math.pow(output - targets[j].get(0), 2);
                totalError += error;
            }
            if (i % 1000 == 0) {
                System.out.println("Epoch " + i + ": " + totalError);
                System.out.println("Use Time: " + (System.currentTimeMillis() - startTime));
                startTime = System.currentTimeMillis();
            }
        }
        System.out.println("Test result:");
        for (Vector input : inputs) {
            double output = network.compute(input).get(0);
            System.out.println(input.get(0) + " " + input.get(1) + " -> " + output + "(" + (output > 0.5 ? 1 : 0) + ")");
        }
    }

    /**
     * Example B And
     */
    private static void exampleB() {
        System.out.println("Example B And");
        final Network network = Networks.createNetwork(
                Layers.createFullyConnectedLayer(2, 2, NetworkFunctions.Sigmoid),
                Layers.createFullyConnectedLayer(2, 1, NetworkFunctions.Sigmoid)
        );
        final Vector[] inputs = {
                Vectors.createVector(0, 0),
                Vectors.createVector(0, 1),
                Vectors.createVector(1, 0),
                Vectors.createVector(1, 1),
        };
        final Vector[] targets = {
                Vectors.createVector(0.0),
                Vectors.createVector(0.0),
                Vectors.createVector(0.0),
                Vectors.createVector(1.0),
        };
        final int epochs = 100000;
        for (int i = 0; i < epochs; i++) {
            double totalError = 0;
            for (int j = 0; j < inputs.length; j++) {
                double output = network.train(inputs[j], targets[j], .1).get(0);
                double error = Math.pow(output - targets[j].get(0), 2) / 2;
                totalError += error;
            }
            if (i % 10000 == 0) {
                System.out.println("Epoch " + i + ": " + totalError);
            }
        }
        System.out.println("Test result:");
        for (Vector input : inputs) {
            double output = network.compute(input).get(0);
            System.out.println(input.get(0) + " " + input.get(1) + " -> " + output + "(" + (output > 0.5 ? 1 : 0) + ")");
        }
    }
}
