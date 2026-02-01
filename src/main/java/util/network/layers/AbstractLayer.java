package util.network.layers;

public abstract class AbstractLayer implements Layer {
    protected String name;

    @Override
    public String getName() {
        return name;
    }

    @Override
    public AbstractLayer setName(String name) {
        this.name = name;
        return this;
    }

    public AbstractLayer() {
        this("Layer");
    }

    public AbstractLayer(String name) {
        this.name = name;
    }

    public abstract class AbstractTrainer implements Trainer {
        @Override
        public AbstractLayer getLayer() {
            return AbstractLayer.this;
        }
    }

    public abstract class AbstractComputer implements Computer {
        @Override
        public AbstractLayer getLayer() {
            return AbstractLayer.this;
        }
    }
}
