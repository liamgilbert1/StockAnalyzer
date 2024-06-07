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

  List<Double> getDataAcrossDays(LocalDate date, int days, StockDataPoint dataPoint);
}
