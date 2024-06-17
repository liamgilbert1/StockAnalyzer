package model.stock;

import java.time.LocalDate;
import java.util.List;

/**
 * This interface represents a stock in the stock market simulator.
 * A stock has a ticker, open price, close price, high price, low price, and volume.
 * A stock can be used to get the data across a given number of days.
 */
public interface IStock {
  /**
   * Gets the ticker of the stock.
   * @return the ticker of the stock
   */
  String getTicker();

  /**
   * Checks if the stock contains the given date.
   * @param date the date to check
   * @return true if the stock contains the date, false otherwise
   */
  boolean checkContainsDates(LocalDate date, int days);

  /**
   * Checks if the stock contains the given date range.
   * @param startDate the start date to check
   * @param endDate the end date to check
   * @return true if the stock contains the date range, false otherwise
   */
  boolean checkContainsDateRange(LocalDate startDate, LocalDate endDate);

  /**
   * Gets the most recent date of the stock.
   * @return the most recent date of the stock
   */
  LocalDate getMostRecentDate();


  /**
   * Gets the open price of the stock.
   * @param date the date of the open price
   * @return the open price of the stock
   */
  double getOpenPrice(LocalDate date);

  /**
   * Gets the close price of the stock.
   * @param date the date of the close price
   * @return the close price of the stock
   */
  double getClosePrice(LocalDate date);

  /**
   * Gets the high price of the stock.
   * @param date the date of the high price
   * @return the high price of the stock
   */
  double getHighPrice(LocalDate date);

  /**
   * Gets the low price of the stock.
   * @param date the date of the low price
   * @return the low price of the stock
   */
  double getLowPrice(LocalDate date);

  /**
   * Gets the volume of the stock.
   * @param date the date of the volume
   * @return the volume of the stock
   */
  double getVolume(LocalDate date);

  /**
   * Gets the data across the given number of days.
   * @param endDate the end date of the data
   * @param days the number of days to get data across
   * @param dataPoint the data point to get
   * @return the data across the given number of days
   */
  List<String> getDataAcrossDays(LocalDate endDate, int days,
                                 StockDataPoint dataPoint);

  /**
   * Gets the data across the given number of days.
   * @param startDate the start date of the data
   * @param endDate the end date of the data
   * @param dataPoint the data point to get
   * @return the data across the given number of days
   */
  List<String> getDataAcrossDays(LocalDate startDate, LocalDate endDate, StockDataPoint dataPoint);

  /**
   * Gets a copy of this stock to be used.
   * @return a copy of this stock.
   */
  IStock getCopy();
}
