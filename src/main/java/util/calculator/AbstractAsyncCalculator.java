package util.calculator;

import util.math.function.Function;
import util.math.tensors.Data;
import util.math.tensors.Matrix;
import util.math.tensors.Scalar;
import util.math.tensors.Vector;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public abstract class AbstractAsyncCalculator<S extends Scalar, V extends Vector, M extends Matrix, F extends Function> implements Calculator<S, V, M, F> {
    protected abstract M createMatrix(int rows, int columns);

    protected abstract Future<M> doTranspose(M source, M result);

    protected abstract Future<M> doAdd(M source1, M source2, M result);

    protected abstract Future<M> doSubtract(M source1, M source2, M result);

    protected abstract Future<M> doMultiply(M source, S scalar, M result);

    protected abstract Future<M> doMultiply(M source, V scalar, M result);

    protected abstract Future<M> doMultiply(M source, M scalar, M result);

    @Override
    public M transpose(M source) {
        M result = createMatrix(source.getRows(), source.getColumns());
        try {
            doTranspose(source, result).get();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    @Override
    public void transpose(M source, M result) {
        checkDataDifferent(source, result);
        checkMatrixDimensionsSame(source, result);
        try {
            doTranspose(source, result).get();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Future<M> transposeAsync(M source) {
        return doTranspose(source, createMatrix(source.getRows(), source.getColumns()));
    }

    @Override
    public Future<M> transposeAsync(M source, M result) {
        checkDataDifferent(source, result);
        checkMatrixDimensionsSame(source, result);
        return doTranspose(source, result);
    }

    @Override
    public M add(M source1, M source2) {
        checkMatrixDimensionsSame(source1, source2);
        M result = createMatrix(source1.getRows(), source1.getColumns());
        try {
            doAdd(source1, source2, result).get();
        } catch (ExecutionException | InterruptedException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    @Override
    public void add(M source1, M source2, M result) {
        checkMatrixDimensionsSame(source1, source2);
        checkMatrixDimensionsSame(source1, result);
        try {
            doAdd(source1, source2, result).get();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Future<M> addAsync(M source1, M source2) {
        checkMatrixDimensionsSame(source1, source2);
        return doAdd(source1, source2, createMatrix(source1.getRows(), source1.getColumns()));
    }

    @Override
    public M subtract(M source1, M source2) {
        checkMatrixDimensionsSame(source1, source2);
        M result = createMatrix(source1.getRows(), source1.getColumns());
        try {
            doSubtract(source1, source2, result).get();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    @Override
    public void subtract(M source1, M source2, M result) {
        checkMatrixDimensionsSame(source1, source2);
        checkMatrixDimensionsSame(source1, result);
        try {
            doSubtract(source1, source2, result).get();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Future<M> subtractAsync(M source1, M source2) {
        checkMatrixDimensionsSame(source1, source2);
        return doSubtract(source1, source2, createMatrix(source1.getRows(), source1.getColumns()));
    }

    @Override
    public M multiply(M source, S scalar) {
        M result = createMatrix(source.getRows(), source.getColumns());
        try {
            doMultiply(source, scalar, result).get();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    @Override
    public void multiply(M source, S scalar, M result) {
        checkMatrixDimensionsSame(source, result);
        try {
            doMultiply(source, scalar, result).get();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Future<M> multiplyAsync(M source, S scalar) {
        return doMultiply(source, scalar, createMatrix(source.getRows(), source.getColumns()));
    }

    @Override
    public Future<M> multiplyAsync(M source, S scalar, M result) {
        checkMatrixDimensionsSame(source, result);
        return doMultiply(source, scalar, result);
    }

    @Override
    public M multiply(M source, V vector) {
        checkMatrixDimensionsMatchVector(source, vector);
        M result = createMatrix(source.getRows(), 1);
        try {
            doMultiply(source, vector, result).get();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    @Override
    public void multiply(M source, V vector, M result) {
        checkMatrixDimensionsMatchVector(source, vector);
        checkMatrixDimensionsSame(source, result);
        try {
            doMultiply(source, vector, result).get();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Future<M> multiplyAsync(M source, V vector) {
        checkMatrixDimensionsMatchVector(source, vector);
        return doMultiply(source, vector, createMatrix(source.getRows(), 1));
    }

    @Override
    public Future<M> multiplyAsync(M source, V vector, M result) {
        checkMatrixDimensionsMatchVector(source, vector);
        checkMatrixDimensionsSame(source, result);
        return doMultiply(source, vector, result);
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

    private void checkMatrixDimensionsSame(M matrix1, M matrix2) {
        if (matrix1.getRows() != matrix2.getRows() || matrix1.getColumns() != matrix2.getColumns()) {
            throw new IllegalArgumentException("Matrix dimensions must be equal");
        }
    }

    private void checkMatrixDimensionsMatchVector(M matrix, V vector) {
        if (matrix.getRows() != vector.size()) {
            throw new IllegalArgumentException("Matrix rows must be equal to vector size");
        }
    }

    private void checkDataDifferent(Data data1, Data data2) {
        if (data1.getData() == data2.getData()) {
            throw new IllegalArgumentException("Matrix must be different");
        }
    }
}
