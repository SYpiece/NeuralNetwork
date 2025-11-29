package util.calculator;

import util.math.function.Function;
import util.math.matrix.Matrices;
import util.math.matrix.Matrix;
import util.math.matrix.Vector;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public interface MatrixCalculator<M extends Matrix> extends Calculator {
    M transpose(M source);

    void transpose(M source, M result);

    Future<M> transposeAsync(M source);

    Future<M> transposeAsync(M source, M result);

    M add(M source1, M source2);

    void add(M source1, M source2, M result);

    Future<M> addAsync(M source1, M source2);

    Future<M> addAsync(M source1, M source2, M result);

    M subtract(M source1, M source2);

    void subtract(M source1, M source2, M result);

    Future<M> subtractAsync(M source1, M source2);

    Future<M> subtractAsync(M source1, M source2, M result);

    M multiply(M source, double scalar);

    void multiply(M source, double scalar, M result);

    Future<M> multiplyAsync(M source, double scalar);

    Future<M> multiplyAsync(M source, double scalar, M result);

    M multiply(M source1, M source2);

    void multiply(M source1, M source2, M result);

    Future<M> multiplyAsync(M source1, M source2);

    Future<M> multiplyAsync(M source1, M source2, M result);

    M dot(M source1, M source2);

    void dot(M source1, M source2, M result);

    Future<M> dotAsync(M source1, M source2);

    Future<M> dotAsync(M source1, M source2, M result);

    M function(M source, Function transformation);

    void function(M source, Function transformation, M result);

    Future<M> functionAsync(M source, Function transformation);

    Future<M> functionAsync(M source, Function transformation, M result);
}