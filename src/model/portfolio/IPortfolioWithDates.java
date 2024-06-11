package model.portfolio;
import java.time.LocalDate;
import java.util.List;

public interface IPortfolioWithDates {
  IPortfolioWithDates addTransaction(ITransaction transaction);


  String getComposition(LocalDate date);

  String getValueDistribution(LocalDate date);

  double getValue();

  List<ITransaction> getTransactions();

  String getName();

  List<String> getStocks();

  boolean isDateBeforeFirstTransaction(LocalDate date);


}
