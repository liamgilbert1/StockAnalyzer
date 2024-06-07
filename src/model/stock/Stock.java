package model.stock;

import java.time.LocalDate;
import java.util.List;

import controller.CSVReader;
import controller.IStockReader;
import controller.StockDataPoint;

public class Stock implements IStock {
  private final String ticker;

  public Stock(String ticker) {
    this.ticker = ticker;
  }

  @Override
  public String getTicker() {
    return ticker;
  }

  protected IStockReader getReader() {
    return new CSVReader(ticker);
  }

  @Override
  public boolean checkContainsDates(LocalDate date, int days) {
    return getReader().checkContainsDates(date, days);
  }

  @Override
  public boolean checkContainsDateRange(LocalDate startDate, LocalDate endDate) {
    return getReader().checkContainsDateRange(startDate, endDate);
  }

  @Override
  public LocalDate getMostRecentDate() {
    return getReader().getMostRecentDate();
  }

  @Override
  public double getOpenPrice(LocalDate date) {
    return getStockData(date, StockDataPoint.OPEN);
  }

  @Override
  public double getClosePrice(LocalDate date) {
    return getStockData(date, StockDataPoint.CLOSE);
  }

  @Override
  public double getHighPrice(LocalDate date) {
    return getStockData(date, StockDataPoint.HIGH);
  }

  @Override
  public double getLowPrice(LocalDate date) {
    return getStockData(date, StockDataPoint.LOW);
  }

  @Override
  public double getVolume(LocalDate date) {
    return getStockData(date, StockDataPoint.VOLUME);
  }

  private double getStockData(LocalDate date, StockDataPoint dataPoint) {
    return getReader().getStockData(date, dataPoint);
  }

  @Override
  public List<Double> getDataAcrossDays(LocalDate date, int days, StockDataPoint dataPoint) {
    return getReader().getDataAcrossDays(date, days, dataPoint);
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null || getClass() != obj.getClass()) {
      return false;
    }
    Stock stock = (Stock) obj;
    return ticker.equals(stock.ticker);
  }

  @Override
  public int hashCode() {
    return ticker.hashCode();
  }
}
