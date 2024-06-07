package model;

import java.time.LocalDate;
import java.util.List;

import model.stock.IStock;

public interface IModel {

  /**
   * Populate the model with the data from the given readable from the API.
   * @param readable the readable with data from the API to populate the model
   */
  void populate(Readable readable);

  /**
   * Calculate the gain or loss of the stock from the given start date to the given end date.
   * @param startDate the start date of the stock
   * @param endDate the end date of the stock
   * @return the gain or loss of the stock
   */
  double calculateGainOrLoss(String ticker, LocalDate startDate, LocalDate endDate);

  /**
   * Calculate the moving average of the stock for the given number of days.
   * @param ticker the date of the stock
   * @param days the number of days to calculate the moving average
   * @return the moving average of the stock
   */
  double movingAverage(String ticker, LocalDate date, int days);

  /**
   * Determine if the stock has crossed over for the given number of days.
   * @param ticker the ticker of the stock
   * @param days the number of days to determine if the stock has crossed over
   * @return true if the stock has crossed over, false otherwise
   */
  List<LocalDate> crossOver(String ticker, LocalDate startDate, LocalDate endDate, int days);


  void createPortfolio(String name);

  void addPortfolioHolding(String portfolioName, String ticker, double quantity);

  double getPortfolioValue(String portfolioName, LocalDate date);

  List<String> getStocksInPortfolio(String portfolioName);

  IStock getStock(String ticker);
}
