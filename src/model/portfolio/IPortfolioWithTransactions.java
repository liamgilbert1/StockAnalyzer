package model.portfolio;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

/**
 * Represents a portfolio of holdings with transactions.
 * A portfolio is a collection of holdings, and can be used to calculate the value of the holdings
 * in the portfolio.
 * A portfolio with transactions can also add transactions to the portfolio.
 */
public interface IPortfolioWithTransactions extends IPortfolio {
  /**
   * Adds the given transaction to the portfolio.
   *
   * @param transaction the transaction to add.
   * @return the portfolio with the given transaction added.
   */
  IPortfolioWithTransactions addTransaction(ITransaction transaction);

  /**
   * Gets the composition of the portfolio on the given date (List of stocks and their quantities).
   *
   * @param date the date to get the composition of the portfolio on.
   * @return the composition of the portfolio on the given date.
   */
  String getComposition(LocalDate date);

  /**
   * Gets the value distribution of the portfolio on the given date (List of stocks and their
   * values).
   *
   * @param date the date to get the value distribution of the portfolio on.
   * @return the value distribution of the portfolio on the given date.
   */
  String getValueDistribution(LocalDate date);

  /**
   * Gets the list of transactions in the portfolio.
   *
   * @return the transactions in the portfolio.
   */
  List<ITransaction> getTransactions();

  /**
   * Determines if the given date is before the first transaction date in the portfolio.
   *
   * @param date the date to check.
   * @return true if the given date is before the first transaction date in the portfolio, false
   *         otherwise.
   */
  boolean isDateBeforeFirstTransaction(LocalDate date);

  /**
   * Gets the performance of the portfolio over time given a period of time.
   * @param startDate the start date of the period.
   * @param endDate the end date of the period.
   * @return the performance of the portfolio over time.
   */
  String getPerformanceOverTime(LocalDate startDate, LocalDate endDate);

  /**
   * Gets the performance dates of the portfolio given a period of time.
   * @param startDate the start date of the period.
   * @param endDate the end date of the period.
   * @return the performance dates of the portfolio.
   */
  List<LocalDate> getPerformanceDates(LocalDate startDate, LocalDate endDate);

  /**
   * Rebalances the portfolio on the given date with the given stock weights.
   *
   * @param date the date to rebalance the portfolio on.
   * @param stockWeights the stock weights to rebalance the portfolio with.
   */
  void rebalance(LocalDate date, Map<String, Integer> stockWeights);

  /**
   * Gets the latest transaction date in the portfolio.
   *
   * @return the latest transaction date in the portfolio.
   */
  LocalDate getLatestTransactionDate();
}
