package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import model.portfolio.IPortfolio;
import model.stock.IStock;
import model.stock.Stock;

import static controller.StockDataPoint.CLOSE;

public class ModelImpl implements IModel {

  private final List<IPortfolio> portfolios;

  public ModelImpl() {
    this.portfolios = new ArrayList<>();
  }

  @Override
  public void populate(Readable readable) {

  }

  protected IStock getStock(String ticker) {
    return new Stock(ticker);
  }



  /**
   * Calculate the gain or loss of the stock from the start date to the end date.
   * @param startDate the start date of the stock.
   * @param endDate the end date of the stock.
   * @return the gain or loss of the stock.
   */
  @Override
  public double calculateGainOrLoss(String ticker, LocalDate startDate, LocalDate endDate) {
    IStock stock = getStock(ticker);
    return stock.getClosePrice(endDate) - stock.getClosePrice(startDate);
  }

  /**
   * Calculate the moving average of the stock for the given number of days.
   * @param ticker the date of the stock
   * @param date the date of the stock
   * @param days the number of days to calculate the moving average
   * @return the moving average of the stock
   */
  @Override
  public double movingAverage(String ticker, LocalDate date, int days) {
    IStock stock = getStock(ticker);
    List<Double> prices = stock.getDataAcrossDays(date, days, CLOSE);
    double total = 0;
    for (double price : prices) {
      total += price;
    }
    return total / days;
  }

  /**
   * Determine if the stock has crossed over for the given number of days.
   * @param ticker the ticker of the stock
   * @param days the number of days to determine if the stock has crossed over
   * @return true if the stock has crossed over, false otherwise
   */
  @Override
  public boolean crossOver(String ticker, LocalDate date, int days) {
    IStock stock = getStock(ticker);
    return stock.getClosePrice(date) > movingAverage(ticker, date, days);
  }
}
