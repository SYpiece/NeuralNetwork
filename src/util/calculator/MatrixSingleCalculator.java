package util.calculator;

import util.math.function.Function;
import util.math.matrix.Matrices;
import util.math.matrix.Matrix;

public class MatrixSingleCalculator implements MatrixCalculator {
    @Override
    public Matrix transpose(Matrix m) {
        final Matrix result = Matrices.createMatrix(m.getColumns(), m.getRows());
        for (int i = 0; i < result.getRows(); i++) {
            for (int j = 0; j < result.getColumns(); j++) {
                result.set(i, j, m.get(j, i));
            }
        }
        return result;
    }

    @Override
    public Matrix add(Matrix m1, Matrix m2) {
        if (m1.getRows() != m2.getRows() || m1.getColumns() != m2.getColumns()) {
            throw new IllegalArgumentException("Matrix.java size mismatch");
        }
        final Matrix result = Matrices.createMatrix(m1.getRows(), m1.getColumns());
        for (int i = 0; i < result.getRows(); i++) {
            for (int j = 0; j < result.getColumns(); j++) {
                result.set(i, j, m1.get(i, j) + m2.get(i, j));
            }
        }
        return result;
    }

    @Override
    public Matrix subtract(Matrix m1, Matrix m2) {
        if (m1.getRows() != m2.getRows() || m1.getColumns() != m2.getColumns()) {
            throw new IllegalArgumentException("Matrix.java size mismatch");
        }
        final Matrix result = Matrices.createMatrix(m1.getRows(), m1.getColumns());
        for (int i = 0; i < result.getRows(); i++) {
            for (int j = 0; j < result.getColumns(); j++) {
                result.set(i, j, m1.get(i, j) - m2.get(i, j));
            }
        }
        return result;
    }

    @Override
    public Matrix multiply(Matrix m, double scalar) {
        final Matrix result = Matrices.createMatrix(m.getRows(), m.getColumns());
        for (int i = 0; i < result.getRows(); i++) {
            for (int j = 0; j < result.getColumns(); j++) {
                result.set(i, j, m.get(i, j) * scalar);
            }
        }
        return result;
    }

    @Override
    public Matrix multiply(Matrix m1, Matrix m2) {
        if (m1.getColumns() != m2.getRows()) {
            throw new IllegalArgumentException("Matrix.java size mismatch");
        }
        final Matrix result = Matrices.createMatrix(m1.getRows(), m2.getColumns());
        for (int i = 0; i < result.getRows(); i++) {
            for (int j = 0; j < result.getColumns(); j++) {
                double sum = 0;
                for (int k = 0; k < m1.getColumns(); k++) {
                    sum += m1.get(i, k) * m2.get(k, j);
                }
                result.set(i, j, sum);
            }
        }
        return result;
    }

    @Override
    public Matrix dot(Matrix m1, Matrix m2) {
        if (m1.getRows() != m2.getRows() || m1.getColumns() != m2.getColumns()) {
            throw new IllegalArgumentException("Matrix.java size mismatch");
        }
        final Matrix result = Matrices.createMatrix(m1.getRows(), m1.getColumns());
        for (int i = 0; i < result.getRows(); i++) {
            for (int j = 0; j < result.getColumns(); j++) {
                result.set(i, j, m1.get(i, j) * m2.get(i, j));
            }
        }
        return result;
    }

    @Override
    public Matrix function(Matrix m, Function f) {
        final Matrix result = Matrices.createMatrix(m.getRows(), m.getColumns());
        for (int i = 0; i < result.getRows(); i++) {
            for (int j = 0; j < result.getColumns(); j++) {
                result.set(i, j, f.calculate(m.get(i, j)));
            }
        }
        return result;
    }
}
