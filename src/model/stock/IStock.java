package model.stock;

import java.time.LocalDate;
import java.util.Map;

public interface IStock {
  double getPrice(LocalDate date);
  String getTicker();
  IStock addStockData(LocalDate date, IStockData data);

  Map<LocalDate, IStockData> getStockData();
}
