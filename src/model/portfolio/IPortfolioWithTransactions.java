package model.portfolio;
import java.time.LocalDate;
import java.util.List;

public interface IPortfolioWithTransactions extends IPortfolio {
  IPortfolioWithTransactions addTransaction(ITransaction transaction);

  String getComposition(LocalDate date);

  String getValueDistribution(LocalDate date);

  List<ITransaction> getTransactions();

  boolean isDateBeforeFirstTransaction(LocalDate date);

  IPortfolioWithTransactions loadPortfolio();

  String getPerformanceOverTime(LocalDate startDate, LocalDate endDate);

  List<LocalDate> getPerformanceDates(LocalDate startDate, LocalDate endDate);

  boolean isTransactionBefore(ITransaction transaction);
}
