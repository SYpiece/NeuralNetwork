package util.math.matrix;

public interface Vector {
    int size();

    double get(int index);

    void set(int index, double value);

    default Matrix toMatrix(Orientation orientation) {
        switch (orientation) {
            case LeftToRight: {
                return new Matrix() {
                    @Override
                    public int getRows() {
                        return 1;
                    }

                    @Override
                    public int getColumns() {
                        return Vector.this.size();
                    }

                    @Override
                    public double get(int row, int column) {
                        if (row != 0) {
                            throw new IllegalArgumentException("Invalid row");
                        }
                        return Vector.this.get(column);
                    }

                    @Override
                    public void set(int row, int column, double value) {
                        if (row != 0) {
                            throw new IllegalArgumentException("Invalid row");
                        }
                        Vector.this.set(column, value);
                    }
                };
            }
            case RightToLeft: {
                return new Matrix() {
                    @Override
                    public int getRows() {
                        return 1;
                    }

                    @Override
                    public int getColumns() {
                        return Vector.this.size();
                    }

                    @Override
                    public double get(int row, int column) {
                        if (row != 0) {
                            throw new IllegalArgumentException("Invalid row");
                        }
                        return Vector.this.get(Vector.this.size() - column - 1);
                    }

                    @Override
                    public void set(int row, int column, double value) {
                        if (row != 0) {
                            throw new IllegalArgumentException("Invalid row");
                        }
                        Vector.this.set(Vector.this.size() - column - 1, value);
                    }
                };
            }
            case TopToBottom: {
                return new Matrix() {
                    @Override
                    public int getRows() {
                        return Vector.this.size();
                    }

                    @Override
                    public int getColumns() {
                        return 1;
                    }

                    @Override
                    public double get(int row, int column) {
                        if (column != 0) {
                            throw new IllegalArgumentException("Invalid column");
                        }
                        return Vector.this.get(row);
                    }

                    @Override
                    public void set(int row, int column, double value) {
                        if (column != 0) {
                            throw new IllegalArgumentException("Invalid column");
                        }
                        Vector.this.set(row, value);
                    }
                };
            }
            case BottomToTop: {
                return new Matrix() {
                    @Override
                    public int getRows() {
                        return Vector.this.size();
                    }

                    @Override
                    public int getColumns() {
                        return 1;
                    }

                    @Override
                    public double get(int row, int column) {
                        if (column != 0) {
                            throw new IllegalArgumentException("Invalid column");
                        }
                        return Vector.this.get(Vector.this.size() - row - 1);
                    }

                    @Override
                    public void set(int row, int column, double value) {
                        if (column != 0) {
                            throw new IllegalArgumentException("Invalid column");
                        }
                        Vector.this.set(Vector.this.size() - row - 1, value);
                    }
                };
            }
            default: throw new IllegalArgumentException("Invalid orientation");
        }
    }
}
