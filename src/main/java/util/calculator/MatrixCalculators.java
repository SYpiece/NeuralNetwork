package util.calculator;

import util.math.function.Function;
import util.math.matrix.Matrix;

import java.util.Objects;

public class MatrixCalculators {
    private static MatrixCalculator Default;

    private static final MatrixCalculator Single = new MatrixSingleCalculator();

    private static final MatrixCalculator Multiple = new MatrixMultipleCalculator();

    static {
        Default = Single;
    }

    public static MatrixCalculator getDefaultCalculator() {
        return Default;
    }

    public static void setDefaultCalculator(MatrixCalculator calculator) {
        Default = Objects.requireNonNull(calculator);
    }

    public static MatrixCalculator getSingleCalculator() {
        return Single;
    }

    public static MatrixCalculator getMultipleCalculator() {
        return Multiple;
    }

    public static Matrix transpose(Matrix m) {
        return Default.transpose(m);
    }

    public static Matrix add(Matrix m1, Matrix m2) {
        return Default.add(m1, m2);
    }

    public static Matrix subtract(Matrix m1, Matrix m2) {
        return Default.subtract(m1, m2);
    }

    public static Matrix multiply(Matrix m, double scalar) {
        return Default.multiply(m, scalar);
    }

    public static Matrix multiply(Matrix m1, Matrix m2) {
        return Default.multiply(m1, m2);
    }

    public static Matrix dot(Matrix m1, Matrix m2) {
        return Default.dot(m1, m2);
    }

    public static Matrix function(Matrix m, Function f) {
        return Default.function(m, f);
    }

    protected MatrixCalculators() {}
}
