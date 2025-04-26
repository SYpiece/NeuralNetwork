package util.calculator;

import util.math.function.Function;
import util.math.matrix.Matrix;
import util.math.matrix.Vector;

public interface MatrixCalculator extends Calculator {
    Matrix transpose(Matrix m);

    Matrix add(Matrix m1, Matrix m2);

    Matrix subtract(Matrix m1, Matrix m2);

//    Vector multiply(Vector v, double scalar);

    Matrix multiply(Matrix m, double scalar);

//    Vector m

//    Vector multiply(Matrix m, Vector v);

    Matrix multiply(Matrix m1, Matrix m2);

//    Vector dot(Vector v1, Vector v2);

    Matrix dot(Matrix m1, Matrix m2);

//    Vector function(Vector v, Function f);

    Matrix function(Matrix m, Function f);
}
