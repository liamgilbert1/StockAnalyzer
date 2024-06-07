package model.stock;

/**
 * This enum represents the data points of a stock.
 * The data points are date, open, high, low, close, and volume.
 * The usage of this enum is to get the index of the data point in the stock data.
 * For example, the index of the date is 0, the index of the open price is 1, etc.
 */
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
