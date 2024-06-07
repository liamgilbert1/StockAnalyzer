package model;

import java.time.LocalDate;
import java.util.List;

import model.stock.IStock;

/**
 * Represents a model that can be used to calculate the gain or loss of a stock,
 * the moving average of a stock, and if a stock has crossed over for a given number of days.
 * A model can also be used to create a portfolio, add a holding to a portfolio, get the value of a
 * portfolio, and get the stocks in a portfolio.
 */
public interface IModel {

  /**
   * Calculate the gain or loss of the stock for the given date range.
   * @param ticker the ticker of the stock.
   * @param startDate the start date of the stock.
   * @param endDate the end date of the stock.
   * @return the gain or loss of the stock.
   */
  double calculateGainOrLoss(String ticker, LocalDate startDate, LocalDate endDate);

  /**
   * Calculate the moving average of the stock for the given number of days.
   * @param ticker the ticker of the stock.
   * @param date the date of the stock.
   * @param days the number of days to calculate the moving average.
   * @return the moving average of the stock.
   */
  double movingAverage(String ticker, LocalDate date, int days);

  /**
   * Determines the dates the stock has crossed over the x-day moving average over
   * the given date range.
   * @param ticker the date of the stock.
   * @param startDate the start date of the stock.
   * @param endDate the end date of the stock.
   * @param days the number of days to determine if the stock has crossed over.
   * @return the dates the stock has crossed over.
   */
  List<LocalDate> crossOver(String ticker, LocalDate startDate, LocalDate endDate, int days);

  /**
   * Create and store a portfolio with the given name.
   * @param name the name of the portfolio.
   */
  void createPortfolio(String name);

  /**
   * Add a holding of the given stock to the portfolio with the given name of the given quantity.
   * @param portfolioName the name of the portfolio.
   * @param ticker the ticker of the stock.
   * @param quantity the quantity of the stock.
   */
  void addPortfolioHolding(String portfolioName, String ticker, double quantity);

  /**
   * Get the value of the portfolio with the given name on the given date.
   * @param portfolioName the name of the portfolio.
   * @param date the date to get the value of the portfolio on.
   * @return the value of the portfolio on the given date.
   */
  double getPortfolioValue(String portfolioName, LocalDate date);

  /**
   * Get the stocks in the portfolio with the given name.
   * @param portfolioName the name of the portfolio.
   * @return the stocks in the portfolio.
   */
  List<String> getStocksInPortfolio(String portfolioName);

  /**
   * Get the stock with the given ticker.
   * @param ticker the ticker of the stock.
   * @return the stock with the given ticker.
   */
  IStock getStock(String ticker);

  /**
   * Get the names of the portfolios.
   * @return the names of the portfolios.
   */
  List<String> getPortfolioNames();
}
