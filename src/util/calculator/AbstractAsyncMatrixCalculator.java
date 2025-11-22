package util.calculator;

import util.math.function.Function;
import util.math.matrix.Matrices;
import util.math.matrix.Matrix;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public abstract class AbstractAsyncMatrixCalculator implements MatrixCalculator {
    @Override
    public Matrix transpose(Matrix source) {
        Matrix result = Matrices.createMatrix(source.getRows(), source.getColumns());
        transpose(source, result);
        return result;
    }

    @Override
    public void transpose(Matrix source, Matrix result) {
        try {
            transposeAsync(source, result).get();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Future<Matrix> transposeAsync(Matrix source) {
        return transposeAsync(source, Matrices.createMatrix(source.getRows(), source.getColumns()));
    }

    @Override
    public Matrix add(Matrix source1, Matrix source2) {
        Matrix result = Matrices.createMatrix(source1.getRows(), source1.getColumns());
        try {
            addAsync(source1, source2, result).get();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    @Override
    public void add(Matrix source1, Matrix source2, Matrix result) {
        try {
            addAsync(source1, source2, result).get();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Future<Matrix> addAsync(Matrix source1, Matrix source2) {
        return addAsync(source1, source2, Matrices.createMatrix(source1.getRows(), source1.getColumns()));
    }

    @Override
    public Matrix subtract(Matrix source1, Matrix source2) {
        Matrix result = Matrices.createMatrix(source1.getRows(), source1.getColumns());
        try {
            subtractAsync(source1, source2, result).get();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    @Override
    public void subtract(Matrix source1, Matrix source2, Matrix result) {
        try {
            subtractAsync(source1, source2, result).get();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Future<Matrix> subtractAsync(Matrix source1, Matrix source2) {
        return subtractAsync(source1, source2, Matrices.createMatrix(source1.getRows(), source1.getColumns()));
    }

    @Override
    public Matrix multiply(Matrix source, double scalar) {
        Matrix result = Matrices.createMatrix(source.getRows(), source.getColumns());
        try {
            multiplyAsync(source, scalar, result).get();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    @Override
    public void multiply(Matrix source, double scalar, Matrix result) {
        try {
            multiplyAsync(source, scalar, result).get();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Future<Matrix> multiplyAsync(Matrix source, double scalar) {
        return multiplyAsync(source, scalar, Matrices.createMatrix(source.getRows(), source.getColumns()));
    }

    @Override
    public Matrix multiply(Matrix source1, Matrix source2) {
        Matrix result = Matrices.createMatrix(source1.getRows(), source2.getColumns());
        try {
            multiplyAsync(source1, source2, result).get();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    @Override
    public void multiply(Matrix source1, Matrix source2, Matrix result) {
        try {
            multiplyAsync(source1, source2, result).get();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Future<Matrix> multiplyAsync(Matrix source1, Matrix source2) {
        return multiplyAsync(source1, source2, Matrices.createMatrix(source1.getRows(), source2.getColumns()));
    }

    @Override
    public Matrix dot(Matrix source1, Matrix source2) {
        Matrix result = Matrices.createMatrix(source1.getRows(), source2.getColumns());
        try {
            dotAsync(source1, source2, result).get();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    @Override
    public void dot(Matrix source1, Matrix source2, Matrix result) {
        try {
            dotAsync(source1, source2, result).get();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Future<Matrix> dotAsync(Matrix source1, Matrix source2) {
        return dotAsync(source1, source2, Matrices.createMatrix(source1.getRows(), source2.getColumns()));
    }

    @Override
    public Matrix function(Matrix source, Function transformation) {
        Matrix result = Matrices.createMatrix(source.getRows(), source.getColumns());
        try {
            functionAsync(source, transformation, result).get();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    @Override
    public void function(Matrix source, Function transformation, Matrix result) {
        try {
            functionAsync(source, transformation, result).get();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Future<Matrix> functionAsync(Matrix source, Function transformation) {
        return functionAsync(source, transformation, Matrices.createMatrix(source.getRows(), source.getColumns()));
    }
}
