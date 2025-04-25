package util.matrix;

public class Vectors extends Matrices {
    public static Vector createVector(int size) {
        return new VectorImpl(size);
    }

    public static Vector createVector(double... vector) {
        if (vector == null) {
            throw new IllegalArgumentException("Vector must not be null");
        }
        return new VectorImpl(vector);
    }

    public static Vector packMatrix(Matrix matrix) {
        if (matrix == null) {
            throw new IllegalArgumentException("Matrix must not be null");
        }
        if (matrix.getColumns() != 1) {
            throw new IllegalArgumentException("Matrix must have only one column");
        }
        return doPackMatrix(matrix);
    }

    static Vector doPackMatrix(Matrix matrix) {
        return new VectorImpl() {
            @Override
            public int size() {
                return matrix.getRows();
            }

            @Override
            public double get(int index) {
                return matrix.get(index, 0);
            }

            @Override
            public void set(int index, double value) {
                matrix.set(index, 0, value);
            }
        };
    }

    protected Vectors() {}
}
