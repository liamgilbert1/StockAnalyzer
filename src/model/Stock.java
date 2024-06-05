package model;

import java.util.Map;

public class Stock implements IStock {
  private final String ticker;
  private final Map<String, StockDay> stockData;

  public Stock(String ticker, Map<String, StockDay> stockData) {
    this.ticker = ticker;
    this.stockData = stockData;
  }
  @Override
  public double getPrice(IDate date) {
    return stockData.get(date.dateToString()).getClose();
  }

  @Override
  public String getTicker() {
    return ticker;
  }
}
