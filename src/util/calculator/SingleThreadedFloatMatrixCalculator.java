package util.calculator;

import util.math.function.Function;
import util.math.matrix.FloatMatrix;
import util.math.matrix.Matrices;

public class SingleThreadedFloatMatrixCalculator extends AbstractSyncMatrixCalculator<FloatMatrix> {
    @Override
    public FloatMatrix createMatrix(int rows, int columns) {
        return Matrices.createFloatMatrix(rows, columns);
    }

    @Override
    public void transpose(FloatMatrix source, FloatMatrix result) {
        if (source.getRows() != result.getColumns() || source.getColumns() != result.getRows()) {
            throw new IllegalArgumentException("matrix dimensions do not match for transpose operation");
        }
        
        for (int i = 0; i < source.getRows(); i++) {
            for (int j = 0; j < source.getColumns(); j++) {
                result.set(j, i, source.get(i, j));
            }
        }
    }

    @Override
    public void add(FloatMatrix source1, FloatMatrix source2, FloatMatrix result) {
        // 检查矩阵维度是否匹配
        if (source1.getRows() != source2.getRows() || source1.getColumns() != source2.getColumns() ||
            source1.getRows() != result.getRows() || source1.getColumns() != result.getColumns()) {
            throw new IllegalArgumentException("matrix dimensions do not match for addition operation");
        }
        
        for (int i = 0; i < source1.getRows(); i++) {
            for (int j = 0; j < source1.getColumns(); j++) {
                result.set(i, j, source1.get(i, j) + source2.get(i, j));
            }
        }
    }

    @Override
    public void subtract(FloatMatrix source1, FloatMatrix source2, FloatMatrix result) {
        // 检查矩阵维度是否匹配
        if (source1.getRows() != source2.getRows() || source1.getColumns() != source2.getColumns() ||
            source1.getRows() != result.getRows() || source1.getColumns() != result.getColumns()) {
            throw new IllegalArgumentException("matrix dimensions do not match for subtraction operation");
        }
        
        for (int i = 0; i < source1.getRows(); i++) {
            for (int j = 0; j < source1.getColumns(); j++) {
                result.set(i, j, source1.get(i, j) - source2.get(i, j));
            }
        }
    }

    @Override
    public void multiply(FloatMatrix source, double scalar, FloatMatrix result) {
        // 检查矩阵维度是否匹配
        if (source.getRows() != result.getRows() || source.getColumns() != result.getColumns()) {
            throw new IllegalArgumentException("matrix dimensions do not match for scalar multiplication operation");
        }
        
        for (int i = 0; i < source.getRows(); i++) {
            for (int j = 0; j < source.getColumns(); j++) {
                result.set(i, j, (float) (source.get(i, j) * scalar));
            }
        }
    }

    @Override
    public void multiply(FloatMatrix source1, FloatMatrix source2, FloatMatrix result) {
        // 检查矩阵维度是否匹配
        if (source1.getColumns() != source2.getRows() || 
            source1.getRows() != result.getRows() || 
            source2.getColumns() != result.getColumns()) {
            throw new IllegalArgumentException("matrix dimensions do not match for multiplication operation");
        }
        
        for (int i = 0; i < source1.getRows(); i++) {
            for (int j = 0; j < source2.getColumns(); j++) {
                float sum = 0;
                for (int k = 0; k < source1.getColumns(); k++) {
                    sum += source1.get(i, k) * source2.get(k, j);
                }
                result.set(i, j, sum);
            }
        }
    }

    @Override
    public void dot(FloatMatrix source1, FloatMatrix source2, FloatMatrix result) {
        // 检查矩阵维度是否匹配
        if (source1.getRows() != source2.getRows() || source1.getColumns() != source2.getColumns() ||
            source1.getRows() != result.getRows() || source1.getColumns() != result.getColumns()) {
            throw new IllegalArgumentException("matrix dimensions do not match for dot product operation");
        }

        for (int i = 0; i < source1.getRows(); i++) {
            for (int j = 0; j < source2.getColumns(); j++) {
                result.set(i, j, source1.get(i, j) * source2.get(i, j));
            }
        }
    }

    @Override
    public void function(FloatMatrix source, Function transformation, FloatMatrix result) {
        // 检查矩阵维度是否匹配
        if (source.getRows() != result.getRows() || source.getColumns() != result.getColumns()) {
            throw new IllegalArgumentException("matrix dimensions do not match for function transformation operation");
        }
        
        for (int i = 0; i < source.getRows(); i++) {
            for (int j = 0; j < source.getColumns(); j++) {
                result.set(i, j, (float) transformation.calculate(source.get(i, j)));
            }
        }
    }
}