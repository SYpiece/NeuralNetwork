package util.math.matrix;

public interface Matrix {
    int getRows();

    int getColumns();

    default int size() {
        return getRows() * getColumns();
    }

    double get(int row, int column);

    void set(int row, int column, double value);

    default Vector toVector(Orientation orientation) {
        switch (orientation) {
            case LeftToRight: {
                if (getRows() != 1) {
                    throw new IllegalArgumentException("Invalid orientation");
                }
                return new Vector() {
                    @Override
                    public int size() {
                        return Matrix.this.getColumns();
                    }

                    @Override
                    public double get(int index) {
                        return Matrix.this.get(0, index);
                    }

                    @Override
                    public void set(int index, double value) {
                        Matrix.this.set(0, index, value);
                    }
                };
            }
            case RightToLeft: {
                if (getRows() != 1) {
                    throw new IllegalArgumentException("Invalid orientation");
                }
                return new Vector() {
                    @Override
                    public int size() {
                        return Matrix.this.getColumns();
                    }

                    @Override
                    public double get(int index) {
                        return Matrix.this.get(0, Matrix.this.getColumns() - index - 1);
                    }

                    @Override
                    public void set(int index, double value) {
                        Matrix.this.set(0, Matrix.this.getColumns() - index - 1, value);
                    }
                };
            }
            case TopToBottom: {
                if (getColumns() != 1) {
                    throw new IllegalArgumentException("Invalid orientation");
                }
                return new Vector() {
                    @Override
                    public int size() {
                        return Matrix.this.getRows();
                    }

                    @Override
                    public double get(int index) {
                        return Matrix.this.get(index, 0);
                    }

                    @Override
                    public void set(int index, double value) {
                        Matrix.this.set(index, 0, value);
                    }
                };
            }
            case BottomToTop: {
                if (getColumns() != 1) {
                    throw new IllegalArgumentException("Invalid orientation");
                }
                return new Vector() {
                    @Override
                    public int size() {
                        return Matrix.this.getRows();
                    }

                    @Override
                    public double get(int index) {
                        return Matrix.this.get(Matrix.this.getColumns() - index - 1, 0);
                    }

                    @Override
                    public void set(int index, double value) {
                        Matrix.this.set(Matrix.this.getColumns() - index - 1, 0, value);
                    }
                };
            }
            default: throw new IllegalArgumentException("Invalid orientation");
        }
    }

    default Matrix toTransposed() {
        return new Matrix() {
            @Override
            public int getRows() {
                return Matrix.this.getColumns();
            }

            @Override
            public int getColumns() {
                return Matrix.this.getRows();
            }

            @Override
            public double get(int row, int column) {
                return Matrix.this.get(column, row);
            }

            @Override
            public void set(int row, int column, double value) {
                Matrix.this.set(column, row, value);
            }
        };
    }
}
