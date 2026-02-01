package util.calculator;

import com.aparapi.Kernel;
import com.aparapi.Range;
import util.math.function.FloatFunction;
import util.math.tensors.FloatMatrix;
import util.math.tensors.Matrices;

public class AparapiFloatMatrixCalculator extends AbstractSyncMatrixCalculator<FloatMatrix, FloatFunction> {
    @Override
    protected FloatMatrix createMatrix(int rows, int columns) {
        return Matrices.createFloatMatrix(rows, columns);
    }

    TransposingKernel transposingKernel;
    final Object transposingKernelLock = new Object();

    @Override
    public void transpose(FloatMatrix source, FloatMatrix result) {
        if (source.getRows() != result.getColumns() || source.getColumns() != result.getRows()) {
            throw new IllegalArgumentException("source and result must have the same size");
        }
        synchronized (transposingKernelLock) {
            if (transposingKernel == null) {
                transposingKernel = new TransposingKernel();
            }
            transposingKernel.rows = source.getRows();
            transposingKernel.columns = source.getColumns();
            transposingKernel.source = source.getData();
            transposingKernel.result = result.getData();
            transposingKernel.execute(Range.create2D(source.getColumns(), source.getRows()));
        }
    }

    AddingKernel addingKernel;
    final Object addingKernelLock = new Object();

    @Override
    public void add(FloatMatrix source1, FloatMatrix source2, FloatMatrix result) {
        if (source1.getRows() != source2.getRows() || source1.getColumns() != source2.getColumns() ||
            source1.getRows() != result.getRows() || source1.getColumns() != result.getColumns()) {
            throw new IllegalArgumentException("source and result must have the same size");
        }
        synchronized (addingKernelLock) {
            if (addingKernel == null) {
                addingKernel = new AddingKernel();
            }
            addingKernel.rows = source1.getRows();
            addingKernel.columns = source1.getColumns();
            addingKernel.source1 = source1.getData();
            addingKernel.source2 = source2.getData();
            addingKernel.result = result.getData();
            addingKernel.execute(Range.create2D(source1.getColumns(), source1.getRows()));
        }
    }

    SubtractingKernel subtractingKernel;
    final Object subtractingKernelLock = new Object();

    @Override
    public void subtract(FloatMatrix source1, FloatMatrix source2, FloatMatrix result) {
        if (source1.getRows() != source2.getRows() || source1.getColumns() != source2.getColumns() ||
            source1.getRows() != result.getRows() || source1.getColumns() != result.getColumns()) {
            throw new IllegalArgumentException("source and result must have the same size");
        }
        synchronized (subtractingKernelLock) {
            if (subtractingKernel == null) {
                subtractingKernel = new SubtractingKernel();
            }
            subtractingKernel.rows = source1.getRows();
            subtractingKernel.columns = source1.getColumns();
            subtractingKernel.source1 = source1.getData();
            subtractingKernel.source2 = source2.getData();
            subtractingKernel.result = result.getData();
            subtractingKernel.execute(Range.create2D(source1.getColumns(), source1.getRows()));
        }
    }

    ScalarMultiplyingKernel scalarMultiplyingKernel;
    final Object scalarMultiplyingKernelLock = new Object();

    @Override
    public void multiply(FloatMatrix source, double scalar, FloatMatrix result) {
        if (source.getRows() != result.getRows() || source.getColumns() != result.getColumns()) {
            throw new IllegalArgumentException("source and result must have the same size");
        }
        synchronized (scalarMultiplyingKernelLock) {
            if (scalarMultiplyingKernel == null) {
                scalarMultiplyingKernel = new ScalarMultiplyingKernel();
            }
            scalarMultiplyingKernel.rows = source.getRows();
            scalarMultiplyingKernel.columns = source.getColumns();
            scalarMultiplyingKernel.scalar = (float) scalar;
            scalarMultiplyingKernel.source = source.getData();
            scalarMultiplyingKernel.result = result.getData();
            scalarMultiplyingKernel.execute(Range.create2D(source.getColumns(), source.getRows()));
        }
    }

    MultiplyingKernel multiplyingKernel;
    final Object multiplyingKernelLock = new Object();

    @Override
    public void multiply(FloatMatrix source1, FloatMatrix source2, FloatMatrix result) {
        if (source1.getColumns() != source2.getRows() || source1.getRows() != result.getRows() ||
            source1.getColumns() != result.getColumns()) {
            throw new IllegalArgumentException("source and result must have the same size");
        }
        synchronized (multiplyingKernelLock) {
            if (multiplyingKernel == null) {
                multiplyingKernel = new MultiplyingKernel();
            }
            multiplyingKernel.rows = source1.getRows();
            multiplyingKernel.columns = source1.getColumns();
            multiplyingKernel.source1 = source1.getData();
            multiplyingKernel.source2 = source2.getData();
            multiplyingKernel.result = result.getData();
            multiplyingKernel.execute(Range.create2D(source1.getColumns(), source1.getRows()));
        }
    }

    MultiplyingKernel dottingKernel;
    final Object dottingKernelLock = new Object();

    @Override
    public void dot(FloatMatrix source1, FloatMatrix source2, FloatMatrix result) {
        if (source1.getRows() != source2.getRows() || source1.getColumns() != source2.getColumns() ||
            source1.getRows() != result.getRows() || source1.getColumns() != result.getColumns()) {
            throw new IllegalArgumentException("source and result must have the same size");
        }
        synchronized (dottingKernelLock) {
            if (dottingKernel == null) {
                dottingKernel = new MultiplyingKernel();
            }
            dottingKernel.rows = source1.getRows();
            dottingKernel.columns = source1.getColumns();
            dottingKernel.source1 = source1.getData();
            dottingKernel.source2 = source2.getData();
            dottingKernel.result = result.getData();
            dottingKernel.execute(Range.create2D(source1.getColumns(), source1.getRows()));
        }
    }

    FunctionKernel functionKernel;
    final Object functionKernelLock = new Object();

    @Override
    public void function(FloatMatrix source, FloatFunction transformation, FloatMatrix result) {
        if (source.getRows() != result.getRows() || source.getColumns() != result.getColumns()) {
            throw new IllegalArgumentException("source and result must have the same size");
        }
        synchronized (functionKernelLock) {
            if (functionKernel == null) {
                functionKernel = new FunctionKernel();
            }
            functionKernel.rows = source.getRows();
            functionKernel.columns = source.getColumns();
            functionKernel.transformation = transformation;
            functionKernel.source = source.getData();
            functionKernel.result = result.getData();
            functionKernel.execute(Range.create2D(source.getColumns(), source.getRows()));
        }
    }

    private static class TransposingKernel extends Kernel {
        int rows, columns;
        float[] source, result;
        @Override
        public void run() {
            final int column = getGlobalId(0), row = getGlobalId(1);
            result[column * rows + row] = source[row * columns + column];
        }
    }

    private static class AddingKernel extends Kernel {
        int rows, columns;
        float[] source1, source2, result;
        @Override
        public void run() {
            final int column = getGlobalId(0), row = getGlobalId(1);
            result[column * rows + row] = source1[column * rows + row] + source2[column * rows + row];
        }
    }

    private static class SubtractingKernel extends Kernel {
        int rows, columns;
        float[] source1, source2, result;
        @Override
        public void run() {
            final int column = getGlobalId(0), row = getGlobalId(1);
            result[column * rows + row] = source1[column * rows + row] - source2[column * rows + row];
        }
    }

    private static class ScalarMultiplyingKernel extends Kernel {
        int rows, columns;
        float scalar;
        float[] source, result;
        @Override
        public void run() {
            final int column = getGlobalId(0), row = getGlobalId(1);
            result[column * rows + row] = source[column * rows + row] * scalar;
        }
    }

    private static class MultiplyingKernel extends Kernel {
        int rows, columns;
        float[] source1, source2, result;
        @Override
        public void run() {
            final int column = getGlobalId(0), row = getGlobalId(1);
            float sum = 0;
            for (int i = 0; i < columns; i++) {
                sum += source1[column * rows + i] * source2[i * rows + row];
            }
            result[column * rows + row] = sum;
        }
    }

    private static class DottingKernel extends Kernel {
        int rows, columns;
        float[] source1, source2, result;
        @Override
        public void run() {
            final int column = getGlobalId(0), row = getGlobalId(1);
            result[column * rows + row] = source1[column * rows + row] * source2[column * rows + row];
        }
    }

    private static class FunctionKernel extends Kernel {
        int rows, columns;
        float[] source, result;
        FloatFunction transformation;
        @Override
        public void run() {
            final int column = getGlobalId(0), row = getGlobalId(1);
            result[column * rows + row] = transformation.calculate(source[column * rows + row]);
        }
    }
}
