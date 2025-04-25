package util.network;

import util.function.DerivableFunction;
import util.matrix.*;

public interface NetworkFunction extends DerivableFunction, MatrixFunction {
    @Override
    MatrixFunction getDerivativeFunction();
}
