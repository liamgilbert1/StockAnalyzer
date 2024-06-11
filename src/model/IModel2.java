package model;

import java.time.LocalDate;

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
   * Determine the value of a portfolio on a specific date (to be exact, the end of that day).
   * The value for a portfolio before the date of its first purchase would be 0, since each stock
   * in the portfolio now was purchased at a specific point in time.
   */
  double getPortfolioValue2(String portfolioName, LocalDate date);

  /**
   * The distribution of value of a portfolio on a specific date (to be exact, the end of that day).
   * The distribution of value should include (a) the stock itself (b) the value of each individual
   * stock in the portfolio. The sum of the values in (b) should equal to the value of that
   * portfolio on that date.
   */
  String getPortfolioValueDistribution(String portfolioName, LocalDate date);



}
