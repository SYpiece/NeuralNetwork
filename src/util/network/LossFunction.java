package util.network;

public interface LossFunction {
    int getTrainingDataSize();

    double loss(double[][] input);
}
