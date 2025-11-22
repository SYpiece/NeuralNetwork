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
        transpose(source, result);
        return new FutureMatrix(result);
    }

    @Override
    public Matrix add(Matrix source1, Matrix source2) {
        Matrix result = Matrices.createMatrix(source1.getRows(), source1.getColumns());
        add(source1, source2, result);
        return result;
    }

    @Override
    public Future<Matrix> addAsync(Matrix source1, Matrix source2) {
        return addAsync(source1, source2, Matrices.createMatrix(source1.getRows(), source1.getColumns()));
    }

    @Override
    public Future<Matrix> addAsync(Matrix source1, Matrix source2, Matrix result) {
        add(source1, source2, result);
        return new FutureMatrix(result);
    }

    @Override
    public Matrix subtract(Matrix source1, Matrix source2) {
        Matrix result = Matrices.createMatrix(source1.getRows(), source1.getColumns());
        subtract(source1, source2, result);
        return result;
    }

    @Override
    public Future<Matrix> subtractAsync(Matrix source1, Matrix source2) {
        return subtractAsync(source1, source2, Matrices.createMatrix(source1.getRows(), source1.getColumns()));
    }

    @Override
    public Future<Matrix> subtractAsync(Matrix source1, Matrix source2, Matrix result) {
        subtract(source1, source2, result);
        return new FutureMatrix(result);
    }

    @Override
    public Matrix multiply(Matrix source, double scalar) {
        Matrix result = Matrices.createMatrix(source.getRows(), source.getColumns());
        multiply(source, scalar, result);
        return result;
    }

    @Override
    public Future<Matrix> multiplyAsync(Matrix source, double scalar) {
        return multiplyAsync(source, scalar, Matrices.createMatrix(source.getRows(), source.getColumns()));
    }

    @Override
    public Future<Matrix> multiplyAsync(Matrix source, double scalar, Matrix result) {
        multiply(source, scalar, result);
        return new FutureMatrix(result);
    }

    @Override
    public Matrix multiply(Matrix source1, Matrix source2) {
        Matrix result = Matrices.createMatrix(source1.getRows(), source2.getColumns());
        multiply(source1, source2, result);
        return result;
    }

    @Override
    public Future<Matrix> multiplyAsync(Matrix source1, Matrix source2) {
        return multiplyAsync(source1, source2, Matrices.createMatrix(source1.getRows(), source2.getColumns()));
    }

    @Override
    public Future<Matrix> multiplyAsync(Matrix source1, Matrix source2, Matrix result) {
        multiply(source1, source2, result);
        return new FutureMatrix(result);
    }

    @Override
    public Matrix dot(Matrix source1, Matrix source2) {
        Matrix result = Matrices.createMatrix(source1.getRows(), source2.getColumns());
        dot(source1, source2, result);
        return result;
    }

    @Override
    public Future<Matrix> dotAsync(Matrix source1, Matrix source2) {
        return dotAsync(source1, source2, Matrices.createMatrix(source1.getRows(), source2.getColumns()));
    }

    @Override
    public Future<Matrix> dotAsync(Matrix source1, Matrix source2, Matrix result) {
        dot(source1, source2, result);
        return new FutureMatrix(result);
    }

    @Override
    public Matrix function(Matrix source, Function transformation) {
        Matrix result = Matrices.createMatrix(source.getRows(), source.getColumns());
        function(source, transformation, result);
        return result;
    }

    @Override
    public Future<Matrix> functionAsync(Matrix source, Function transformation) {
        return functionAsync(source, transformation, Matrices.createMatrix(source.getRows(), source.getColumns()));
    }

    @Override
    public Future<Matrix> functionAsync(Matrix source, Function transformation, Matrix result) {
        function(source, transformation, result);
        return new FutureMatrix(result);
    }

    protected static class FutureMatrix implements Future<Matrix> {
        final Matrix result;

        FutureMatrix(Matrix result) {
            this.result = result;
        }

        @Override
        public boolean cancel(boolean mayInterruptIfRunning) {
            return false;
        }

        @Override
        public boolean isCancelled() {
            return false;
        }

        @Override
        public boolean isDone() {
            return true;
        }

        @Override
        public Matrix get() throws InterruptedException, ExecutionException {
            return result;
        }

        @Override
        public Matrix get(long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
            return result;
        }
    }
}
