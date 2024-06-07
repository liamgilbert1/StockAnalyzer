package model.stock;

import java.time.LocalDate;
import java.util.List;

public interface IStock {
  String getTicker();

  boolean checkContainsDates(LocalDate date, int days);

  boolean checkContainsDateRange(LocalDate startDate, LocalDate endDate);

  LocalDate getMostRecentDate();

  double getOpenPrice(LocalDate date);

  double getClosePrice(LocalDate date);

  double getHighPrice(LocalDate date);

  double getLowPrice(LocalDate date);

  double getVolume(LocalDate date);

  List<String> getDataAcrossDays(LocalDate endDate, int days,
                                 StockDataPoint dataPoint);

  List<String> getDataAcrossDays(LocalDate startDate, LocalDate endDate, StockDataPoint dataPoint);
}
