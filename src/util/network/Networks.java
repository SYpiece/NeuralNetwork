package util.network;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Networks {
    public static Network createNetwork(Layer... layers) {
        for (int i = 1; i < layers.length; i++) {
            if (layers[i - 1].getOutputSize() != layers[i].getInputSize()) {
                throw new IllegalArgumentException("The output size of the previous layer must be equal to the input size of the next layer.");
            }
        }
        return new Network() {
            private final List<Layer> l = Arrays.asList(layers);

            @Override
            public List<Layer> getLayers() {
                return l;
            }
        };
    }
    private Networks() {}
}
