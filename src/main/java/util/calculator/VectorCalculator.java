package util.calculator;

import util.math.tensors.Scalar;
import util.math.tensors.Vector;

import java.util.concurrent.Future;

public interface VectorCalculator<S extends Scalar, V extends Vector> {
    V add(V source1, V source2);

    void add(V source1, V source2, V result);

    Future<V> addAsync(V source1, V source2);

    Future<V> addAsync(V source1, V source2, V result);

    V subtract(V source1, V source2);

    void subtract(V source1, V source2, V result);

    Future<V> subtractAsync(V source1, V source2);

    Future<V> subtractAsync(V source1, V source2, V result);

    V dot(V source1, V source2);

    void dot(V source1, V source2, V result);

    Future<V> dotAsync(V source1, V source2);

    Future<V> dotAsync(V source1, V source2, V result);

    V multiply(V source, S scalar);

    void multiply(V source, S scalar, V result);

    Future<V> multiplyAsync(V source, S scalar);

    Future<V> multiplyAsync(V source, S scalar, V result);
}
