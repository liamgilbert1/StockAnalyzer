package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import model.portfolio.Holding;
import model.portfolio.IPortfolioWithHoldings;
import model.portfolio.Portfolio;
import model.stock.IStock;
import model.stock.Stock;

import static model.stock.StockDataPoint.CLOSE;
import static model.stock.StockDataPoint.DATE;

/**
 * Represents a model that can be used to calculate the gain or loss of a stock, the moving average
 * of a stock, and the dates where there was an x-day crossover. The model can also be used to
 * create a portfolio, add a holding to a portfolio, get the value of a portfolio, and get the
 * stocks in a portfolio.
 */
public class ModelImpl implements IModel {
  protected final List<IPortfolioWithHoldings> portfolios;

  /**
   * Constructs a new model with an empty list of portfolios.
   */
  public ModelImpl() {
    this.portfolios = new ArrayList<>();
  }

  /**
   * Get the stock with the given ticker.
   * @param ticker the ticker of the stock.
   * @return the stock with the given ticker.
   */
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

  /**
   * Create and store a portfolio with the given name.
   * @param name the name of the portfolio.
   */
  @Override
  public void createPortfolio(String name) {
    for (IPortfolioWithHoldings portfolio : this.portfolios) {
      if (portfolio.getName().equals(name)) {
        throw new IllegalArgumentException("Portfolio already exists");
      }
    }
    this.portfolios.add(new Portfolio(name));
  }

  /**
   * Add a holding of the given stock to the portfolio with the given name of the given quantity.
   * @param portfolioName the name of the portfolio.
   * @param ticker the ticker of the stock.
   * @param quantity the quantity of the stock.
   */
  @Override
  public void addPortfolioHolding(String portfolioName, String ticker, double quantity) {
    for (IPortfolioWithHoldings portfolio : this.portfolios) {
      if (portfolio.getName().equals(portfolioName)) {
        IPortfolioWithHoldings newPortfolio = portfolio.addHolding(new Holding(getStock(ticker),
                quantity));
        this.portfolios.remove(portfolio);
        this.portfolios.add(newPortfolio);
        return;
      }
    }
    throw new IllegalArgumentException("Portfolio does not exist");
  }

  /**
   * Get the value of the portfolio with the given name on the given date.
   * @param portfolioName the name of the portfolio.
   * @param date the date to get the value of the portfolio on.
   * @return the value of the portfolio on the given date.
   */
  @Override
  public double getPortfolioValue(String portfolioName, LocalDate date) {
    for (IPortfolioWithHoldings portfolio : this.portfolios) {
      if (portfolio.getName().equals(portfolioName)) {
        return portfolio.getValue(date);
      }
    }
    throw new IllegalArgumentException("Portfolio does not exist");
  }

  /**
   * Get the stocks in the portfolio with the given name.
   * @param portfolioName the name of the portfolio.
   * @return the stocks in the portfolio.
   */
  @Override
  public List<String> getStocksInPortfolio(String portfolioName) {
    for (IPortfolioWithHoldings portfolio : this.portfolios) {
      if (portfolio.getName().equals(portfolioName)) {
        return portfolio.getStocks();
      }
    }
    throw new IllegalArgumentException("Portfolio does not exist");
  }

  /**
   * Get the names of the portfolios.
   * @return the names of the portfolios.
   */
  @Override
  public List<String> getPortfolioNames() {
    List<String> portfolioNames = new ArrayList<>();
    for (IPortfolioWithHoldings portfolio : this.portfolios) {
      portfolioNames.add(portfolio.getName());
    }
    return portfolioNames;
  }
}
