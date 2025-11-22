package util.calculator;

import util.math.function.Function;
import util.math.matrix.Matrices;
import util.math.matrix.Matrix;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public abstract class AbstractSyncMatrixCalculator implements MatrixCalculator {
    @Override
    public Matrix transpose(Matrix source) {
        Matrix result = Matrices.createMatrix(source.getRows(), source.getColumns());
        transpose(source, result);
        return result;
    }

    @Override
    public Future<Matrix> transposeAsync(Matrix source) {
        return transposeAsync(source, Matrices.createMatrix(source.getRows(), source.getColumns()));
    }

    @Override
    public Future<Matrix> transposeAsync(Matrix source, Matrix result) {
    }

    @Override
    public Matrix add(Matrix source1, Matrix source2) {
        return null;
    }

    @Override
    public void add(Matrix source1, Matrix source2, Matrix result) {

    }

    @Override
    public Future<Matrix> addAsync(Matrix source1, Matrix source2) {
        return null;
    }

    @Override
    public Future<Matrix> addAsync(Matrix source1, Matrix source2, Matrix result) {
        return null;
    }

    @Override
    public Matrix subtract(Matrix source1, Matrix source2) {
        return null;
    }

    @Override
    public void subtract(Matrix source1, Matrix source2, Matrix result) {

    }

    @Override
    public Future<Matrix> subtractAsync(Matrix source1, Matrix source2) {
        return null;
    }

    @Override
    public Future<Matrix> subtractAsync(Matrix source1, Matrix source2, Matrix result) {
        return null;
    }

    @Override
    public Matrix multiply(Matrix source, double scalar) {
        return null;
    }

    @Override
    public void multiply(Matrix source, double scalar, Matrix result) {

    }

    @Override
    public Future<Matrix> multiplyAsync(Matrix source, double scalar) {
        return null;
    }

    @Override
    public Future<Matrix> multiplyAsync(Matrix source, double scalar, Matrix result) {
        return null;
    }

    @Override
    public Matrix multiply(Matrix source1, Matrix source2) {
        return null;
    }

    @Override
    public void multiply(Matrix source1, Matrix source2, Matrix result) {

    }

    @Override
    public Future<Matrix> multiplyAsync(Matrix source1, Matrix source2) {
        return null;
    }

    @Override
    public Future<Matrix> multiplyAsync(Matrix source1, Matrix source2, Matrix result) {
        return null;
    }

    @Override
    public Matrix dot(Matrix source1, Matrix source2) {
        return null;
    }

    @Override
    public void dot(Matrix source1, Matrix source2, Matrix result) {

    }

    @Override
    public Future<Matrix> dotAsync(Matrix source1, Matrix source2) {
        return null;
    }

    @Override
    public Future<Matrix> dotAsync(Matrix source1, Matrix source2, Matrix result) {
        return null;
    }

    @Override
    public Matrix function(Matrix source, Function transformation) {
        return null;
    }

    @Override
    public void function(Matrix source, Function transformation, Matrix result) {

    }

    @Override
    public Future<Matrix> functionAsync(Matrix source, Function transformation) {
        return null;
    }

    @Override
    public Future<Matrix> functionAsync(Matrix source, Function transformation, Matrix result) {
        return null;
    }
}
