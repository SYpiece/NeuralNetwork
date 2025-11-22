package util.calculator;

import util.math.function.Function;
import util.math.matrix.Matrices;
import util.math.matrix.Matrix;
import util.math.matrix.Vector;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public interface MatrixCalculator extends Calculator {
    Matrix transpose(Matrix source);

    void transpose(Matrix source, Matrix result);

    Future<Matrix> transposeAsync(Matrix source);

    Future<Matrix> transposeAsync(Matrix source, Matrix result);

    Matrix add(Matrix source1, Matrix source2);

    void add(Matrix source1, Matrix source2, Matrix result);

    Future<Matrix> addAsync(Matrix source1, Matrix source2);

    Future<Matrix> addAsync(Matrix source1, Matrix source2, Matrix result);

    Matrix subtract(Matrix source1, Matrix source2);

    void subtract(Matrix source1, Matrix source2, Matrix result);

    Future<Matrix> subtractAsync(Matrix source1, Matrix source2);

    Future<Matrix> subtractAsync(Matrix source1, Matrix source2, Matrix result);

    Matrix multiply(Matrix source, double scalar);

    void multiply(Matrix source, double scalar, Matrix result);

    Future<Matrix> multiplyAsync(Matrix source, double scalar);

    Future<Matrix> multiplyAsync(Matrix source, double scalar, Matrix result);

    Matrix multiply(Matrix source1, Matrix source2);

    void multiply(Matrix source1, Matrix source2, Matrix result);

    Future<Matrix> multiplyAsync(Matrix source1, Matrix source2);

    Future<Matrix> multiplyAsync(Matrix source1, Matrix source2, Matrix result);

    Matrix dot(Matrix source1, Matrix source2);

    void dot(Matrix source1, Matrix source2, Matrix result);

    Future<Matrix> dotAsync(Matrix source1, Matrix source2);

    Future<Matrix> dotAsync(Matrix source1, Matrix source2, Matrix result);

    Matrix function(Matrix source, Function transformation);

    void function(Matrix source, Function transformation, Matrix result);

    Future<Matrix> functionAsync(Matrix source, Function transformation);

    Future<Matrix> functionAsync(Matrix source, Function transformation, Matrix result);
}
