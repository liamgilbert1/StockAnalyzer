package model;

import java.time.LocalDate;
import java.util.Map;

/**
 * This interface represents a model for a stock market simulator. The model can be used to get the
 * data of a stock, get the data of a portfolio, and get the data of a strategy.
 */
public interface IModel2 extends IModel {
  /**
   * Purchase a specific number of shares of a specific stock on a specified date, and add them to
   * the portfolio.
   */
  void buyPortfolioHolding(String portfolioName, String ticker, int quantity, LocalDate date);

  /**
   * Sells a specific number of shares of a specific stock on a specified date, and remove them from
   * the portfolio.
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

  /**
   * Gets the value of the portfolio over time given a period of time.
   * @param portfolioName the name of the portfolio
   * @param startDate the start date
   * @param endDate the end date
   * @return the value of the portfolio over time as a bar chart
   */
  String getPortfolioPerformanceOverTime(String portfolioName, LocalDate startDate,
                                         LocalDate endDate);


  /**
   * Load a portfolio from a file placed in the "portfolios" directory. The file should be named as
   * the portfolio name.
   * @param portfolioName is the name of the portfolio to be loaded into the model.
   */
  void loadPortfolio(String portfolioName);

  /**
   * Re-balance the portfolio to the given stock weights on the given date.
   * @param portfolioName the name of the portfolio
   * @param date the date to re-balance the portfolio
   * @param stockWeights the weights of the stocks in the portfolio
   */
  void rebalancePortfolio(String portfolioName, LocalDate date,
                          Map<String, Integer> stockWeights);
}
