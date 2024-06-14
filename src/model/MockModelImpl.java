package model;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import model.stock.IStock;

/**
 * Represents a mock model implementation. This class is used for testing purposes only.
 * (Changed MockModelImpl to implement IModel2 instead of IModel so we can test the new methods in
 * IModel2. Since MockModelImpl is only used for testing purposes, we believe this alteration won't
 * violate the SOLID principles.)
 */
public class MockModelImpl implements IModel2 {
  private final StringBuilder log;

  /**
   * Constructs a mock model implementation with the given log.
   * @param log the log to use.
   */
  public MockModelImpl(StringBuilder log) {
    this.log = Objects.requireNonNull(log);
  }

  /**
   * Gets the log of the mock model implementation for GainOrLoss.
   * @param ticker the ticker of the stock.
   * @param startDate the start date of the stock.
   * @param endDate the end date of the stock.
   * @return 0;
   */
  @Override
  public double calculateGainOrLoss(String ticker, LocalDate startDate, LocalDate endDate) {
    log.append("GainOrLoss ").append(ticker).append(" ").append(startDate.toString()).append(" ")
            .append(endDate.toString());
    return 0;
  }

  /**
   * Gets the log of the mock model implementation for the moving average.
   * @param ticker the ticker of the stock.
   * @param date the date of the stock.
   * @param days the number of days to calculate the moving average.
   * @return 0;
   */
  @Override
  public double movingAverage(String ticker, LocalDate date, int days) {
    log.append("MovingAverage ").append(ticker).append(" ").append(date.toString()).append(" ")
            .append(days);
    return 0;
  }

  /**
   * Gets the log of the mock model implementation for the crossover.
   * @param ticker the ticker of the stock.
   * @param startDate the start date of the stock.
   * @param endDate the end date of the stock.
   * @param days the number of days to determine if the stock has crossed over.
   * @return null;
   */
  @Override
  public List<LocalDate> crossOver(String ticker, LocalDate startDate, LocalDate endDate,
                                   int days) {
    log.append("Crossover ").append(ticker).append(" ").append(startDate.toString()).append(" ")
            .append(endDate.toString()).append(" ").append(days);
    return null;
  }

  /**
   * Gets the log of the mock model implementation for the create portfolio.
   * @param name the name of the portfolio.
   */
  @Override
  public void createPortfolio(String name) {
    log.append("CreatePortfolio ").append(name);
  }

  /**
   * Gets the log of the mock model implementation for the add portfolio holding.
   * @param portfolioName the name of the portfolio.
   * @param ticker the ticker of the stock.
   * @param quantity the quantity of the stock.
   */
  @Override
  public void addPortfolioHolding(String portfolioName, String ticker, double quantity) {
    log.append("AddPortfolioHolding ").append(portfolioName).append(" ").append(ticker).append(" ")
            .append(quantity);
  }

  /**
   * Gets the log of the mock model implementation for the get portfolio value.
   * @param portfolioName the name of the portfolio.
   * @param date the date to get the value of the portfolio on.
   * @return 0;
   */
  @Override
  public double getPortfolioValue(String portfolioName, LocalDate date) {
    log.append("GetPortfolioValue ").append(portfolioName).append(" ").append(date.toString());
    return 0;
  }

  /**
   * Gets the log of the mock model implementation for the get stocks in the portfolio.
   * @param portfolioName the name of the portfolio.
   * @return null;
   */
  @Override
  public List<String> getStocksInPortfolio(String portfolioName) {
    return null;
  }

  /**
   * Gets the log of the mock model implementation for the get stock.
   * @param ticker the ticker of the stock.
   * @return null;
   */
  @Override
  public IStock getStock(String ticker) {
    return null;
  }

  /**
   * Gets the log of the mock model implementation for the get portfolio names.
   * @return null;
   */
  @Override
  public List<String> getPortfolioNames() {
    return null;
  }

  /**
   * Gets the log of the mock model implementation for the buy portfolio holding.
   * @param portfolioName the name of the portfolio.
   * @param ticker the ticker of the stock.
   * @param quantity the quantity of the stock.
   * @param date the date of the stock.
   */
  @Override
  public void buyPortfolioHolding(String portfolioName, String ticker, int quantity,
                                  LocalDate date) {
    log.append("BuyPortfolioHolding ").append(portfolioName).append(" ").append(ticker).append(" ")
            .append(quantity).append(" ").append(date.toString());
  }

  /**
   * Gets the log of the mock model implementation for the sell portfolio holding.
   * @param portfolioName the name of the portfolio.
   * @param ticker the ticker of the stock.
   * @param quantity the quantity of the stock.
   * @param date the date of the stock.
   */
  @Override
  public void sellPortfolioHolding(String portfolioName, String ticker, double quantity,
                                   LocalDate date) {
    log.append("SellPortfolioHolding ").append(portfolioName).append(" ").append(ticker).append(" ")
            .append(quantity).append(" ").append(date.toString());
  }

  /**
   * Gets the log of the mock model implementation for the get portfolio composition.
   * @param portfolioName the name of the portfolio.
   * @param date the date of the stock.
   * @return null;
   */
  @Override
  public String getPortfolioComposition(String portfolioName, LocalDate date) {
    log.append("GetPortfolioComposition ").append(portfolioName).append(" ")
            .append(date.toString());
    return "";
  }

  /**
   * Gets the log of the mock model implementation for the get portfolio value distribution.
   * @param portfolioName the name of the portfolio.
   * @param date the date of the stock.
   * @return null;
   */
  @Override
  public String getPortfolioValueDistribution(String portfolioName, LocalDate date) {
    log.append("GetPortfolioDistribution ").append(portfolioName).append(" ")
            .append(date.toString());
    return "";
  }

  /**
   * Gets the log of the mock model implementation for the load portfolio.
   * @param portfolioName is the name of the portfolio to be loaded into the model.
   */
  @Override
  public void loadPortfolio(String portfolioName) {
    log.append("LoadPortfolio ").append(portfolioName);
  }

  /**
   * Gets the log of the mock model implementation for the rebalance portfolio.
   * @param portfolioName the name of the portfolio.
   * @param date the date to re-balance the portfolio.
   * @param stockWeights the weights of the stocks in the portfolio.
   */
  @Override
  public void rebalancePortfolio(String portfolioName, LocalDate date,
                                 Map<String, Integer> stockWeights) {
    log.append("RebalancePortfolio ").append(portfolioName).append(" ").append(date.toString());
  }

  /**
   * Gets the log of the mock model implementation for the get performance over time.
   * @param portfolioName the name of the portfolio.
   * @param startDate the start date.
   * @param endDate the end date.
   * @return null;
   */
  @Override
  public String getPortfolioPerformanceOverTime(String portfolioName, LocalDate startDate,
                                                LocalDate endDate) {
    log.append("GetPerformanceOverTime ").append(portfolioName).append(" ")
            .append(startDate.toString()).append(" ").append(endDate.toString());
    return "";
  }


}
