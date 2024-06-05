package model.stock;

public interface IStockData {
  double getOpen();

  double getHigh();

  double getLow();

  double getClose();

  int getVolume();
}
