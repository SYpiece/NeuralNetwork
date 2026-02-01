package util.network.layers;

import util.math.tensors.Tensor;

public interface Layer {
    String getName();

    Layer setName(String name);

    Trainer createTrainer();

    Computer createComputer();

    interface Trainer {
        Layer getLayer();

        void apply();
    }

    interface Computer {
        Layer getLayer();

        Tensor compute(Tensor input);
    }
}
