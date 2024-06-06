package model.stock;

import java.time.LocalDate;
import java.util.Map;

public class Stock implements IStock {
  private final String ticker;
  private final Map<LocalDate, IStockData> stockData;

  public Stock(String ticker, Map<LocalDate, IStockData> stockData) {
    this.ticker = ticker;
    this.stockData = stockData;
  }
  @Override
  public double getPrice(LocalDate date) {
    return stockData.get(date).getClose();
  }

  @Override
  public String getTicker() {
    return ticker;
  }
}
