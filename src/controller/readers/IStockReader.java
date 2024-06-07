package controller.readers;

import java.time.LocalDate;
import java.util.List;

import model.stock.StockDataPoint;

/**
 * Represents a reader that can read stock data from a source, and return it as a Readable object.
 * This is useful for reading stock data from a file, or from a web service.
 */
public interface IStockReader extends IReader {

  /**
   * Checks if the stock data contains the given date and the number of days before it.
   * @param date the date to check for.
   * @param days the number of days before the given date to check for.
   * @return true if the stock data contains the given date and the number of days before it, false
   *         otherwise.
   */
  boolean checkContainsDates(LocalDate date, int days);

  /**
   * Checks if the stock data contains the given date range.
   * @param startDate the start date of the range.
   * @param endDate the end date of the range.
   * @return true if the stock data contains the given date range, false otherwise.
   */
  boolean checkContainsDateRange(LocalDate startDate, LocalDate endDate);

  /**
   * Gets the stock data for the given date and data point.
   * @param date the date to get the stock data for.
   * @param dataPoint the data point to get the stock data for.
   * @return the stock data for the given date and data point.
   */
  double getStockData(LocalDate date, StockDataPoint dataPoint);

  /**
   * Gets the stock data for the given date and data point. This will include all dates
   * within the given number of days before the given date.
   * @param date the date to get the stock data for.
   * @param dataPoint the data point to get the stock data for.
   * @return the stock data for the given date and data point.
   */
  List<String> getDataAcrossDays(LocalDate date, int days, StockDataPoint dataPoint);

  /**
   * Gets the stock data for the given date and data point. This will include all dates
   * WITHIN the given range.
   * @param startDate the start date of the range.
   * @param endDate the end date of the range.
   * @param dataPoint the data point to get the stock data for.
   * @return the stock data for the given date and data point.
   */
  List<String> getDataAcrossDays(LocalDate startDate, LocalDate endDate, StockDataPoint dataPoint);

  /**
   * Gets the most recent date in the stock data.
   * @return the most recent date in the stock data.
   */
  LocalDate getMostRecentDate();
}
