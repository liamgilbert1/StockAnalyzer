package controller.readers;

import java.time.LocalDate;
import java.util.List;

import model.stock.StockDataPoint;

public interface IStockReader extends IReader {
  /**
   * This method checks if the file contains the given date and the number of days before that.
   * @param date the date to check.
   * @param days the number of days to check.
   * @return true if the file contains the date and the number of days before that, false otherwise.
   */
  boolean checkContainsDates(LocalDate date, int days);

  boolean checkContainsDateRange(LocalDate startDate, LocalDate endDate);

  double getStockData(LocalDate date, StockDataPoint dataPoint);

  List<String> getDataAcrossDays(LocalDate date, int days, StockDataPoint dataPoint);

  List<String> getDataAcrossDays(LocalDate startDate, LocalDate endDate, StockDataPoint dataPoint);

  LocalDate getMostRecentDate();
}
