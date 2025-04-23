package util.network;

import util.function.*;

public class NetworkFunctions {
    public final static NetworkFunction HyperbolicTangent = new NetworkFunction() {
        private final Function derivativeFunction = new Function() {
            @Override
            public Domain getDomain() {
                return Domains.Real;
            }

            @Override
            public double calculate(double x) {
                final double value = Math.tanh(x);
                return 1 - value * value;
            }
        };

        @Override
        public Domain getDomain() {
            return Domains.Real;
        }

        @Override
        public double calculate(double x) {
            return Math.tanh(x);
        }

        @Override
        public Function getDerivativeFunction() {
            return derivativeFunction;
        }
    };

    public final static NetworkFunction RectifiedLinearUnit = new NetworkFunction() {
        private final Function derivativeFunction = new Function() {
            @Override
            public Domain getDomain() {
                return Domains.Real;
            }

            @Override
            public double calculate(double x) {
                return x > 0 ? 1 : 0;
            }
        };

        @Override
        public Domain getDomain() {
            return Domains.Real;
        }

        @Override
        public double calculate(double x) {
            return Math.max(x, 0);
        }

        @Override
        public Function getDerivativeFunction() {
            return derivativeFunction;
        }
    };

    public final static NetworkFunction Sigmoid = new NetworkFunction() {
        private final Function derivativeFunction = new Function() {
            @Override
            public Domain getDomain() {
                return Domains.Real;
            }

            @Override
            public double calculate(double x) {
                return x * (1 - x);
            }
        };

        @Override
        public Domain getDomain() {
            return Domains.Real;
        }

        @Override
        public double calculate(double x) {
            return 1 / (1 + Math.exp(-x));
        }

        @Override
        public Function getDerivativeFunction() {
            return derivativeFunction;
        }
    };

    private NetworkFunctions() {}
}
