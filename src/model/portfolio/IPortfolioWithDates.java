package model.portfolio;
import java.time.LocalDate;
import java.util.List;

public interface IPortfolioWithDates {
  IPortfolioWithDates buyTransaction(ITransaction transaction);

  IPortfolioWithDates sellTransaction(ITransaction transaction);

  String getComposition(LocalDate date);

  String getValueDistribution(LocalDate date);

  double getValue();

  List<ITransaction> getTransactions();

  String getName();

  List<String> getStocks();

  boolean isDateBeforeFirstTransaction(LocalDate date);

}
