package model.stock;

import java.util.Map;

import model.IDate;

public class Stock implements IStock {
  private final String ticker;
  private final Map<String, StockData> stockData;

  public Stock(String ticker, Map<String, StockData> stockData) {
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
