package util.calculator;

import util.math.function.Function;
import util.math.matrix.Matrix;
import util.math.matrix.Vector;

public interface MatrixCalculator extends Calculator {
    Matrix transpose(Matrix m);

    Matrix add(Matrix m1, Matrix m2);

    Matrix subtract(Matrix m1, Matrix m2);

    Matrix multiply(Matrix m, double scalar);

    Matrix multiply(Matrix m1, Matrix m2);

    Matrix dot(Matrix m1, Matrix m2);

    Matrix function(Matrix m, Function f);
}
