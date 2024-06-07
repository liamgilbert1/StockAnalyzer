package model;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

import model.stock.IStock;

/**
 * Represents a mock model implementation. This class is used for testing purposes only.
 */
public class MockModelImpl implements IModel {
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
}
