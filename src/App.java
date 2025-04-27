import util.calculator.MatrixCalculators;
import util.math.matrix.*;
import util.network.Layers;
import util.network.Network;
import util.network.NetworkFunctions;
import util.network.Networks;

import java.util.Random;

public class App {
    public static void main(String[] args) {
//        test();
        exampleA();
    }

    private static void test() {
        Random random = new Random();
        for (int i = 0; i < 100; i++) {
            double[][] x = new double[1000000][3];
            for (double[] value : x) {
                value[0] = random.nextDouble();
                value[1] = random.nextDouble();
            }
            long startTime = System.nanoTime();
            for (double[] value : x) {
                value[2] = value[0] * value[1];
            }
            System.out.println("Single: " + (System.nanoTime() - startTime));
        }
    }

    /**
     * Example A Xor
     */
    private static void exampleA() {
        System.out.println("Example A Xor");
        MatrixCalculators.setDefaultCalculator(MatrixCalculators.getMultipleCalculator());
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
                double output = network.train(inputs[j].toMatrix(Orientation.TopToBottom), targets[j].toMatrix(Orientation.TopToBottom), .1).get(0, 0);
                double error = Math.pow(output - targets[j].get(0), 2);
                totalError += error;
            }
            if (i % 10 == 0) {
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

//    /**
//     * Example B And
//     */
//    private static void exampleB() {
//        System.out.println("Example B And");
//        final Network network = Networks.createNetwork(
//                Layers.createFullyConnectedLayer(2, 2, NetworkFunctions.Sigmoid),
//                Layers.createFullyConnectedLayer(2, 1, NetworkFunctions.Sigmoid)
//        );
//        final Vector[] inputs = {
//                Vectors.createVector(0, 0),
//                Vectors.createVector(0, 1),
//                Vectors.createVector(1, 0),
//                Vectors.createVector(1, 1),
//        };
//        final Vector[] targets = {
//                Vectors.createVector(0.0),
//                Vectors.createVector(0.0),
//                Vectors.createVector(0.0),
//                Vectors.createVector(1.0),
//        };
//        final int epochs = 100000;
//        for (int i = 0; i < epochs; i++) {
//            double totalError = 0;
//            for (int j = 0; j < inputs.length; j++) {
//                double output = network.train(inputs[j], targets[j], .1).get(0);
//                double error = Math.pow(output - targets[j].get(0), 2) / 2;
//                totalError += error;
//            }
//            if (i % 10000 == 0) {
//                System.out.println("Epoch " + i + ": " + totalError);
//            }
//        }
//        System.out.println("Test result:");
//        for (Vector input : inputs) {
//            double output = network.compute(input).get(0);
//            System.out.println(input.get(0) + " " + input.get(1) + " -> " + output + "(" + (output > 0.5 ? 1 : 0) + ")");
//        }
//    }
}
