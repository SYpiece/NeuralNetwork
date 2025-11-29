package util.calculator;

import util.math.function.Function;
import util.math.matrix.Matrix;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public abstract class AbstractSyncMatrixCalculator<M extends Matrix> implements MatrixCalculator<M> {
    protected abstract M createMatrix(int rows, int columns);

    @Override
    public M transpose(M source) {
        M result = createMatrix(source.getRows(), source.getColumns());
        transpose(source, result);
        return result;
    }

    @Override
    public Future<M> transposeAsync(M source) {
        return transposeAsync(source, createMatrix(source.getRows(), source.getColumns()));
    }

    @Override
    public Future<M> transposeAsync(M source, M result) {
        transpose(source, result);
        return new FutureMatrix<>(result);
    }

    @Override
    public M add(M source1, M source2) {
        M result = createMatrix(source1.getRows(), source1.getColumns());
        add(source1, source2, result);
        return result;
    }

    @Override
    public Future<M> addAsync(M source1, M source2) {
        return addAsync(source1, source2, createMatrix(source1.getRows(), source1.getColumns()));
    }

    @Override
    public Future<M> addAsync(M source1, M source2, M result) {
        add(source1, source2, result);
        return new FutureMatrix<>(result);
    }

    @Override
    public M subtract(M source1, M source2) {
        M result = createMatrix(source1.getRows(), source1.getColumns());
        subtract(source1, source2, result);
        return result;
    }

    @Override
    public Future<M> subtractAsync(M source1, M source2) {
        return subtractAsync(source1, source2, createMatrix(source1.getRows(), source1.getColumns()));
    }

    @Override
    public Future<M> subtractAsync(M source1, M source2, M result) {
        subtract(source1, source2, result);
        return new FutureMatrix<>(result);
    }

    @Override
    public M multiply(M source, double scalar) {
        M result = createMatrix(source.getRows(), source.getColumns());
        multiply(source, scalar, result);
        return result;
    }

    @Override
    public Future<M> multiplyAsync(M source, double scalar) {
        return multiplyAsync(source, scalar, createMatrix(source.getRows(), source.getColumns()));
    }

    @Override
    public Future<M> multiplyAsync(M source, double scalar, M result) {
        multiply(source, scalar, result);
        return new FutureMatrix<>(result);
    }

    @Override
    public M multiply(M source1, M source2) {
        M result = createMatrix(source1.getRows(), source2.getColumns());
        multiply(source1, source2, result);
        return result;
    }

    @Override
    public Future<M> multiplyAsync(M source1, M source2) {
        return multiplyAsync(source1, source2, createMatrix(source1.getRows(), source2.getColumns()));
    }

    @Override
    public Future<M> multiplyAsync(M source1, M source2, M result) {
        multiply(source1, source2, result);
        return new FutureMatrix<>(result);
    }

    @Override
    public M dot(M source1, M source2) {
        M result = createMatrix(source1.getRows(), source2.getColumns());
        dot(source1, source2, result);
        return result;
    }

    @Override
    public Future<M> dotAsync(M source1, M source2) {
        return dotAsync(source1, source2, createMatrix(source1.getRows(), source2.getColumns()));
    }

    @Override
    public Future<M> dotAsync(M source1, M source2, M result) {
        dot(source1, source2, result);
        return new FutureMatrix<>(result);
    }

    @Override
    public M function(M source, Function transformation) {
        M result = createMatrix(source.getRows(), source.getColumns());
        function(source, transformation, result);
        return result;
    }

    @Override
    public Future<M> functionAsync(M source, Function transformation) {
        return functionAsync(source, transformation, createMatrix(source.getRows(), source.getColumns()));
    }

    @Override
    public Future<M> functionAsync(M source, Function transformation, M result) {
        function(source, transformation, result);
        return new FutureMatrix<>(result);
    }

    protected static class FutureMatrix<M extends Matrix> implements Future<M> {
        final M result;

        FutureMatrix(M result) {
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
        public M get() throws InterruptedException, ExecutionException {
            return result;
        }

        @Override
        public M get(long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
            return result;
        }
    }
}
