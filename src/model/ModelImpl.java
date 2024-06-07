package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import model.portfolio.Holding;
import model.portfolio.IPortfolio;
import model.portfolio.Portfolio;
import model.stock.IStock;
import model.stock.Stock;

import static model.stock.StockDataPoint.CLOSE;
import static model.stock.StockDataPoint.DATE;

public class ModelImpl implements IModel {

  private final List<IPortfolio> portfolios;

  public ModelImpl() {
    this.portfolios = new ArrayList<>();
  }

  @Override
  public IStock getStock(String ticker) {
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
    List<String> prices = stock.getDataAcrossDays(date, days, CLOSE);
    double total = 0;
    for (String price : prices) {
      double stockPrice = Double.parseDouble(price);
      total += stockPrice;
    }
    return total / days;
  }

  /**
   * Determine the dates, if any, where there was an x-day crossover within the given date range.
   * @param ticker the ticker of the stock
   * @param startDate the start date of the stock
   * @param endDate the end date of the stock
   * @param days the number of days to determine if the stock has crossed over
   * @return the dates where there was an x-day crossover
   */
  @Override
  public List<LocalDate> crossOver(String ticker, LocalDate startDate, LocalDate endDate,
                                   int days) {
    IStock stock = getStock(ticker);
    List<String> stringDates = stock.getDataAcrossDays(startDate, endDate, DATE);

    List<LocalDate> dates = new ArrayList<>();
    List<Double> closingPrices = new ArrayList<>();
    List<Double> movingAverages = new ArrayList<>();

    for (String stringDate : stringDates) {
      LocalDate stockDate = LocalDate.parse(stringDate);
      dates.add(stockDate);
      closingPrices.add(stock.getClosePrice(stockDate));
      movingAverages.add(movingAverage(ticker, stockDate, days));
    }

    List<LocalDate> crossovers = new ArrayList<>();

    for (int i = 0; i < dates.size(); i++) {
      if (closingPrices.get(i) > movingAverages.get(i)) {
        crossovers.add(dates.get(i));
      }
    }

    return crossovers;
  }

  @Override
  public void createPortfolio(String name) {
    this.portfolios.add(new Portfolio(name));
  }

  @Override
  public void addPortfolioHolding(String portfolioName, String ticker, double quantity) {
    for (IPortfolio portfolio : this.portfolios) {
      if (portfolio.getName().equals(portfolioName)) {
        portfolio.addHolding(new Holding(getStock(ticker), quantity));
        return;
      }
    }
    throw new IllegalArgumentException("Portfolio does not exist");
  }

  @Override
  public double getPortfolioValue(String portfolioName, LocalDate date) {
    for (IPortfolio portfolio : this.portfolios) {
      if (portfolio.getName().equals(portfolioName)) {
        return portfolio.getValue(date);
      }
    }
    throw new IllegalArgumentException("Portfolio does not exist");
  }

  @Override
  public List<String> getStocksInPortfolio(String portfolioName) {
    for (IPortfolio portfolio : this.portfolios) {
      if (portfolio.getName().equals(portfolioName)) {
        return portfolio.getStocks();
      }
    }
    throw new IllegalArgumentException("Portfolio does not exist");
  }
}
