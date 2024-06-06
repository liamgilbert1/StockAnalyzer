package model.stock;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class Stock implements IStock {
  private final String ticker;
  private final Map<LocalDate, IStockData> stockData;

  public Stock(String ticker, Map<LocalDate, IStockData> stockData) {
    this.ticker = ticker;
    this.stockData = new HashMap<>(stockData);
  }

  public Stock(String ticker) {
    this.ticker = ticker;
    this.stockData = new HashMap<>();
  }

  @Override
  public double getPrice(LocalDate date) {
    if (!stockData.containsKey(date)) {
      throw new IllegalArgumentException("No stock data found for " + ticker + " on "
              + date);
    }
    return stockData.get(date).getClose();
  }

  @Override
  public String getTicker() {
    return ticker;
  }

  @Override
  public Stock addStockData(LocalDate date, IStockData data) {
    Map<LocalDate, IStockData> newStockData = new HashMap<>(stockData);
    newStockData.put(date, data);
    return new Stock(ticker, newStockData);
  }
}
