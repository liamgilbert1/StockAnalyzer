package model;

import java.time.LocalDate;
import java.util.List;

public interface IModel2 extends IModel {
  //TODO
  // add new methods

  /**
   * Purchase a specific number of shares of a specific stock on a specified date, and add them to
   * the portfolio
   */
  void buyPortfolioHolding(String portfolioName, String ticker, int quantity, LocalDate date);

  /**
   * Sells a specific number of shares of a specific stock on a specified date, and remove them from
   * the portfolio
   */
  void sellPortfolioHolding(String portfolioName, String ticker, double quantity, LocalDate date);

  /**
   * Determines the composition of a portfolio at a specific date. Note that the composition may
   * change over time. The composition must include (a) the list of stocks and (b) the number of
   * shares of each stock
   */
  String getPortfolioComposition(String portfolioName, LocalDate date);



  /**
   * The distribution of value of a portfolio on a specific date (to be exact, the end of that day).
   * The distribution of value should include (a) the stock itself (b) the value of each individual
   * stock in the portfolio. The sum of the values in (b) should equal to the value of that
   * portfolio on that date.
   */
  String getPortfolioValueDistribution(String portfolioName, LocalDate date);

  String getPortfolioPerformanceOverTime(String portfolioName, LocalDate startDate,
                                         LocalDate endDate);

  List<LocalDate> getPortfolioPerformanceDates(String portfolioName, LocalDate startDate, LocalDate endDate);

  /**
   * Load a portfolio from a file placed in the "portfolios" directory. The file should be named as
   * the portfolio name.
   * @param portfolioName is the name of the portfolio to be loaded into the model.
   */
  void loadPortfolio(String portfolioName);
}
