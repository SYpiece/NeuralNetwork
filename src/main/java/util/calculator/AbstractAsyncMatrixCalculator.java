package util.calculator;

import util.math.function.Function;
import util.math.matrix.Matrices;
import util.math.matrix.Matrix;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public abstract class AbstractAsyncMatrixCalculator<M extends Matrix, F extends Function> implements MatrixCalculator<M, F> {
    protected abstract M createMatrix(int rows, int columns);

    @Override
    public M transpose(M source) {
        M result = createMatrix(source.getRows(), source.getColumns());
        transpose(source, result);
        return result;
    }

    @Override
    public void transpose(M source, M result) {
        try {
            transposeAsync(source, result).get();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Future<M> transposeAsync(M source) {
        return transposeAsync(source, createMatrix(source.getRows(), source.getColumns()));
    }

    @Override
    public M add(M source1, M source2) {
        M result = createMatrix(source1.getRows(), source1.getColumns());
        try {
            addAsync(source1, source2, result).get();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    @Override
    public void add(M source1, M source2, M result) {
        try {
            addAsync(source1, source2, result).get();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Future<M> addAsync(M source1, M source2) {
        return addAsync(source1, source2, createMatrix(source1.getRows(), source1.getColumns()));
    }

    @Override
    public M subtract(M source1, M source2) {
        M result = createMatrix(source1.getRows(), source1.getColumns());
        try {
            subtractAsync(source1, source2, result).get();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    @Override
    public void subtract(M source1, M source2, M result) {
        try {
            subtractAsync(source1, source2, result).get();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Future<M> subtractAsync(M source1, M source2) {
        return subtractAsync(source1, source2, createMatrix(source1.getRows(), source1.getColumns()));
    }

    @Override
    public M multiply(M source, double scalar) {
        M result = createMatrix(source.getRows(), source.getColumns());
        try {
            multiplyAsync(source, scalar, result).get();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    @Override
    public void multiply(M source, double scalar, M result) {
        try {
            multiplyAsync(source, scalar, result).get();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Future<M> multiplyAsync(M source, double scalar) {
        return multiplyAsync(source, scalar, createMatrix(source.getRows(), source.getColumns()));
    }

    @Override
    public M multiply(M source1, M source2) {
        M result = createMatrix(source1.getRows(), source2.getColumns());
        try {
            multiplyAsync(source1, source2, result).get();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    @Override
    public void multiply(M source1, M source2, M result) {
        try {
            multiplyAsync(source1, source2, result).get();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Future<M> multiplyAsync(M source1, M source2) {
        return multiplyAsync(source1, source2, createMatrix(source1.getRows(), source2.getColumns()));
    }

    @Override
    public M dot(M source1, M source2) {
        M result = createMatrix(source1.getRows(), source2.getColumns());
        try {
            dotAsync(source1, source2, result).get();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    @Override
    public void dot(M source1, M source2, M result) {
        try {
            dotAsync(source1, source2, result).get();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Future<M> dotAsync(M source1, M source2) {
        return dotAsync(source1, source2, createMatrix(source1.getRows(), source2.getColumns()));
    }

    @Override
    public M function(M source, F transformation) {
        M result = createMatrix(source.getRows(), source.getColumns());
        try {
            functionAsync(source, transformation, result).get();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    @Override
    public void function(M source, F transformation, M result) {
        try {
            functionAsync(source, transformation, result).get();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Future<M> functionAsync(M source, F transformation) {
        return functionAsync(source, transformation, createMatrix(source.getRows(), source.getColumns()));
    }
}
