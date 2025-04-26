package util.math.matrix;

import util.math.function.Function;

public interface MatrixFunction extends Function {
    default Matrix calculate(Matrix matrix) {
        Matrix result = Matrices.createMatrix(matrix.getRows(), matrix.getColumns());
        for (int i = 0; i < matrix.getRows(); i++) {
            for (int j = 0; j < matrix.getColumns(); j++) {
                result.set(i, j, calculate(matrix.get(i, j)));
            }
        }
        return result;
    }

    default Vector calculate(Vector vector) {
        Vector result = Vectors.createVector(vector.size());
        for (int i = 0; i < vector.size(); i++) {
            result.set(i, calculate(vector.get(i)));
        }
        return result;
    }
}
