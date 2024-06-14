package model.portfolio;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface IPortfolioWithTransactions extends IPortfolio {
  IPortfolioWithTransactions addTransaction(ITransaction transaction);

  String getComposition(LocalDate date);

  String getValueDistribution(LocalDate date);

  List<ITransaction> getTransactions();

  boolean isDateBeforeFirstTransaction(LocalDate date);

  String getPerformanceOverTime(LocalDate startDate, LocalDate endDate);

  List<LocalDate> getPerformanceDates(LocalDate startDate, LocalDate endDate);

  void rebalance(LocalDate date, Map<String, Integer> stockWeights);

  LocalDate getLatestTransactionDate();
}
