import util.network.Layers;
import util.network.Network;
import util.network.NetworkFunctions;
import util.network.Networks;

public class App {
    public static void main(String[] args) {
        final Network network = Networks.createNetwork(
                Layers.createFullyConnectedLayer(2, 2, NetworkFunctions.Sigmoid),
                Layers.createFullyConnectedLayer(2, 1, NetworkFunctions.Sigmoid)
        );
        final double[][] inputs = {
                {0, 0},
                {0, 1},
                {1, 0},
                {1, 1}
        };
        final double[][] targets = {
                {0},
                {1},
                {1},
                {0}
        };
        final int epochs = 10000;
        for (int i = 0; i < epochs; i++) {
            double totalError = 0;
            for (int j = 0; j < inputs.length; j++) {
                double output = network.train(inputs[j], targets[j], .1)[0];
                double error = Math.pow(output - targets[j][0], 2) / 2;
                totalError += error;
            }
            if (i % 1000 == 0) {
                System.out.println("Epoch " + i + ": " + totalError);
            }
        }
    }
}
