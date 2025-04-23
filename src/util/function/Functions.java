package util.function;

public class Functions {
    public static Function createConstantFunction(double c) {
        return new Function() {
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

    public static Function createLinearFunction(double a, double b) {
        return new Function() {
            @Override
            public Domain getDomain() {
                return Domains.Real;
            }

            @Override
            public double calculate(double x) {
                return a * x + b;
            }
        };
    }

    public static Function createQuadraticFunction(double a, double b, double c) {
        return new Function() {
            @Override
            public Domain getDomain() {
                return Domains.Real;
            }

            @Override
            public double calculate(double x) {
                return a * x * x + b * x + c;
            }
        };
    }

    private Functions() {}
}
