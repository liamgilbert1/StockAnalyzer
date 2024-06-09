package model.stock;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

import controller.readers.CSVReader;
import controller.readers.IStockReader;

import static model.stock.StockDataPoint.CLOSE;
import static model.stock.StockDataPoint.HIGH;
import static model.stock.StockDataPoint.LOW;
import static model.stock.StockDataPoint.OPEN;
import static model.stock.StockDataPoint.VOLUME;

/**
 * This class represents a stock.
 * A stock has a ticker and can be used to get stock data.
 */
public class Stock implements IStock {
  private final String ticker;

  /**
   * Constructs a stock object with the given ticker.
   * @param ticker the ticker of the stock
   */
  public Stock(String ticker) {
    this.ticker = ticker;
  }

  /**
   * Gets the ticker of the stock.
   * @return the ticker of the stock
   */
  @Override
  public String getTicker() {
    return ticker;
  }

  /**
   * Gets the reader for the stock.
   * @return the reader for the stock
   */
  protected IStockReader getReader() {
    return new CSVReader(ticker);
  }

  /**
   * Checks if the stock contains the given date.
   * @param date the date to check
   * @param days the number of days to check
   * @return true if the stock contains the date, false otherwise
   */
  @Override
  public boolean checkContainsDates(LocalDate date, int days) {
    return getReader().checkContainsDates(date, days);
  }

  /**
   * Checks if the stock contains the given date range.
   * @param startDate the start date to check
   * @param endDate the end date to check
   * @return true if the stock contains the date range, false otherwise
   */
  @Override
  public boolean checkContainsDateRange(LocalDate startDate, LocalDate endDate) {
    return getReader().checkContainsDateRange(startDate, endDate);
  }

  /**
   * Gets the most recent date of the stock.
   * @return the most recent date of the stock
   */
  @Override
  public LocalDate getMostRecentDate() {
    return getReader().getMostRecentDate();
  }

  /**
   * Gets the open price of the stock.
   * @param date the date of the open price
   * @return the open price of the stock
   */
  @Override
  public double getOpenPrice(LocalDate date) {
    return getStockData(date, OPEN);
  }

  /**
   * Gets the close price of the stock.
   * @param date the date of the close price
   * @return the close price of the stock
   */
  @Override
  public double getClosePrice(LocalDate date) {
    return getStockData(date, CLOSE);
  }

  /**
   * Gets the high price of the stock.
   * @param date the date of the high price
   * @return the high price of the stock
   */
  @Override
  public double getHighPrice(LocalDate date) {
    return getStockData(date, HIGH);
  }

  /**
   * Gets the low price of the stock.
   * @param date the date of the low price
   * @return the low price of the stock
   */
  @Override
  public double getLowPrice(LocalDate date) {
    return getStockData(date, LOW);
  }

  /**
   * Gets the volume of the stock.
   * @param date the date of the volume
   * @return the volume of the stock
   */
  @Override
  public double getVolume(LocalDate date) {
    return getStockData(date, VOLUME);
  }

  /**
   * Gets the stock data for the given date and data point.
   * @param date the date of the stock data
   * @param dataPoint the data point to get
   * @return the stock data for the given date and data point
   */
  private double getStockData(LocalDate date, StockDataPoint dataPoint) {
    return getReader().getStockData(date, dataPoint);
  }

  /**
   * Gets the data across the given number of days.
   * @param endDate the end date of the data
   * @param days the number of days to get data across
   * @param dataPoint the data point to get
   * @return the data across the given number of days
   */
  @Override
  public List<String> getDataAcrossDays(LocalDate endDate, int days,
                                        StockDataPoint dataPoint) {
    return getReader().getDataAcrossDays(endDate, days, dataPoint);
  }

  /**
   * Gets the data across the given number of days.
   * @param startDate the start date of the data
   * @param endDate the end date of the data
   * @param dataPoint the data point to get
   * @return the data across the given number of days
   */
  @Override
  public List<String> getDataAcrossDays(LocalDate startDate, LocalDate endDate,
                                        StockDataPoint dataPoint) {
    return getReader().getDataAcrossDays(startDate, endDate, dataPoint);
  }

  /**
   * Gets the data across the given number of days.
   * @param obj the object to compare
   * @return true if the objects are equal, false otherwise
   */
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

  /**
   * Gets the hash code of the stock.
   * @return the hash code of the stock
   */
  @Override
  public int hashCode() {
    return Objects.hash(ticker);
  }
}
