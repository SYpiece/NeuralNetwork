package util.network;

import util.math.function.DerivableFunction;
import util.math.matrix.*;

public interface NetworkFunction extends DerivableFunction, MatrixFunction {
    @Override
    MatrixFunction getDerivativeFunction();
}
