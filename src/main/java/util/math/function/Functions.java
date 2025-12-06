package util.math.function;

public class Functions {
    public static DoubleFunction createConstantFunction(double c) {
        return new DoubleFunction() {
            @Override
            public Domain getDomain() {
                return Domains.Real;
            }

            @Override
            public double calculate(double x) {
                return c;
            }
        };
    }

//    public static DerivableFunction createLinearFunction(double a, double b) {
//        return new DerivableFunction() {
//            private final Function derivativeFunction = new Function() {
//                @Override
//                public Domain getDomain() {
//                    return Domains.Real;
//                }
//
//                @Override
//                public double calculate(double x) {
//                    return a;
//                }
//            };
//
//            @Override
//            public Domain getDomain() {
//                return Domains.Real;
//            }
//
//            @Override
//            public double calculate(double x) {
//                return a * x + b;
//            }
//
//            @Override
//            public Function getDerivativeFunction() {
//                return derivativeFunction;
//            }
//        };
//    }
//
//    public static DerivableFunction createQuadraticFunction(double a, double b, double c) {
//        return new DerivableFunction() {
//            private final Function derivativeFunction = new Function() {
//                @Override
//                public Domain getDomain() {
//                    return Domains.Real;
//                }
//
//                @Override
//                public double calculate(double x) {
//                    return 2 * a * x + b;
//                }
//            };
//
//            @Override
//            public Domain getDomain() {
//                return Domains.Real;
//            }
//
//            @Override
//            public double calculate(double x) {
//                return a * x * x + b * x + c;
//            }
//
//            @Override
//            public Function getDerivativeFunction() {
//                return derivativeFunction;
//            }
//        };
//    }

    private Functions() {}
}
