package model.stock;

public enum StockDataPoint {
  DATE {
    @Override
    public int getIndex() {
      return 0;
    }
  },
  OPEN {
    @Override
    public int getIndex() {
      return 1;
    }
  }, HIGH {
    @Override
    public int getIndex() {
      return 2;
    }
  }, LOW {
    @Override
    public int getIndex() {
      return 3;
    }
  }, CLOSE {
    @Override
    public int getIndex() {
      return 4;
    }
  }, VOLUME {
    @Override
    public int getIndex() {
      return 5;
    }
  };

  public abstract int getIndex();
}
