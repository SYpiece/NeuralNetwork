package util.network.function;

public class NetworkFunctions {
    public final static ActivationFunction Sigmoid = new ActivationFunction() {
        @Override
        public int getFunctionType() {
            return NetworkFunction.ACTIVATION_SIGMOID;
        }

        @Override
        public float calculateDerivative(float x) {
            float f = 1 / (float) (1 + Math.exp(-x));
            return f * (1 - f);
        }

        @Override
        public double calculateDerivative(double x) {
            double f = 1 / (1 + Math.exp(-x));
            return f * (1 - f);
        }
        @Override
        public float calculate(float x) {
            return 1 / (float) (1 + Math.exp(-x));
        }

        @Override
        public double calculate(double x) {
            return 1 / (1 + Math.exp(-x));
        }

    };

    public final static ActivationFunction Tanh = new ActivationFunction() {
        @Override
        public int getFunctionType() {
            return NetworkFunction.ACTIVATION_TANH;
        }

        @Override
        public float calculateDerivative(float x) {
            float f = (float) Math.tanh(x);
            return 1 - f * f;
        }

        @Override
        public double calculateDerivative(double x) {
            double f = Math.tanh(x);
            return 1 - f * f;
        }

        @Override
        public double calculate(double x) {
            return Math.tanh(x);
        }

        @Override
        public float calculate(float x) {
            return (float) Math.tanh(x);
        }
    };

    public final static ActivationFunction ReLU = new ActivationFunction() {
        @Override
        public int getFunctionType() {
            return NetworkFunction.ACTIVATION_RELU;
        }

        @Override
        public float calculateDerivative(float x) {
            return x > 0 ? 1 : 0;
        }

        @Override
        public double calculateDerivative(double x) {
            return x > 0 ? 1 : 0;
        }

        @Override
        public double calculate(double x) {
            return Math.max(0, x);
        }

        @Override
        public float calculate(float x) {
            return Math.max(0, x);
        }
    };

    protected NetworkFunctions() {}
}
