package util.calculator;

import util.math.function.Function;
import util.math.tensors.Matrix;
import util.math.tensors.Scalar;
import util.math.tensors.Vector;

import java.util.concurrent.Future;

public interface Calculator<S extends Scalar, V extends Vector, M extends Matrix, F extends Function> {
    V add(V source1, V source2);

    void add(V source1, V source2, V result);

    Future<V> addAsync(V source1, V source2);

    Future<V> addAsync(V source1, V source2, V result);

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

    M multiply(M source, S scalar);

    void multiply(M source, S scalar, M result);

    Future<M> multiplyAsync(M source, S scalar);

    Future<M> multiplyAsync(M source, S scalar, M result);

    V multiply(M source, V vector);

    void multiply(M source, V vector, V result);

    Future<V> multiplyAsync(M source, V vector);

    Future<V> multiplyAsync(M source, V vector, V result);

    M multiply(M source1, M source2);

    void multiply(M source1, M source2, M result);

    Future<M> multiplyAsync(M source1, M source2);

    Future<M> multiplyAsync(M source1, M source2, M result);

    M dot(M source1, M source2);

    void dot(M source1, M source2, M result);

    Future<M> dotAsync(M source1, M source2);

    Future<M> dotAsync(M source1, M source2, M result);

    M function(M source, F transformation);

    void function(M source, F transformation, M result);

    Future<M> functionAsync(M source, F transformation);

    Future<M> functionAsync(M source, F transformation, M result);
}