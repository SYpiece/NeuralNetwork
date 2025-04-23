package util.network;

import java.util.Arrays;

public class Networks {
    public static Network createNetwork(Layer... layers) {
        return () -> Arrays.asList(layers);
    }
    private Networks() {}
}
