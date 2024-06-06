package model.stock;

import java.util.Objects;

public class StockData implements IStockData {
  private final double open;
  private final double high;
  private final double low;
  private final double close;
  private final int volume;

  public StockData(double open, double high, double low, double close, int volume) {
    this.open = open;
    this.high = high;
    this.low = low;
    this.close = close;
    this.volume = volume;
  }

  @Override
  public double getOpen() {
    return open;
  }

  @Override
  public double getHigh() {
    return high;
  }

  @Override
  public double getLow() {
    return low;
  }

  @Override
  public double getClose() {
    return close;
  }

  @Override
  public int getVolume() {
    return volume;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null || getClass() != obj.getClass()) {
      return false;
    }
    StockData stockData = (StockData) obj;
    return Double.compare(stockData.open, open) == 0
            && Double.compare(stockData.high, high) == 0
            && Double.compare(stockData.low, low) == 0
            && Double.compare(stockData.close, close) == 0
            && volume == stockData.volume;
  }

  @Override
  public int hashCode() {
    return Objects.hash(open, high, low, close, volume);
  }
}
