package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import controller.IO.readers.IPortfolioWithTransactionsReader;
import controller.IO.readers.TxtPortfolioReader;
import model.portfolio.BuyTransaction;
import model.portfolio.IPortfolioWithHoldings;
import model.portfolio.IPortfolioWithTransactions;

import model.portfolio.Portfolio;
import model.portfolio.PortfolioWithTransactions;
import model.portfolio.SellTransaction;


public class ModelImpl2 extends ModelImpl implements IModel2 {
  private final List<IPortfolioWithTransactions> portfoliosWithDates;

  public ModelImpl2() {
    super();
    this.portfoliosWithDates = new ArrayList<>();
  }

  @Override
  public void createPortfolio(String name) {
    for (IPortfolioWithTransactions portfolio : this.portfoliosWithDates) {
      if (portfolio.getName().equals(name)) {
        throw new IllegalArgumentException("Portfolio already exists");
      }
    }
    this.portfoliosWithDates.add(new PortfolioWithTransactions(name));
  }

  @Override
  public void buyPortfolioHolding(String portfolioName, String ticker, int quantity,
                                  LocalDate date) {
    for (IPortfolioWithTransactions portfolio : this.portfoliosWithDates) {
      if (portfolio.getName().equals(portfolioName)) {
        IPortfolioWithTransactions newPortfolio = portfolio.addTransaction(
                new BuyTransaction(getStock(ticker), quantity, date));
        this.portfoliosWithDates.remove(portfolio);
        this.portfoliosWithDates.add(newPortfolio);
        return;
      }
    }
    throw new IllegalArgumentException("Portfolio does not exist");
  }

  @Override
  public void sellPortfolioHolding(String portfolioName, String ticker, double quantity,
                                   LocalDate date) {
    for (IPortfolioWithTransactions portfolio : this.portfoliosWithDates) {
      if (portfolio.getName().equals(portfolioName)) {

        IPortfolioWithTransactions newPortfolio =
                portfolio.addTransaction(new SellTransaction(getStock(ticker), quantity, date));
        this.portfoliosWithDates.remove(portfolio);
        this.portfoliosWithDates.add(newPortfolio);
        return;
      }
    }
  }

  /**
   * Determines the composition of a portfolio at a specific date. Note that the composition may
   * change over time. The composition must include (a) the list of stocks and (b) the number of
   * shares of each stock
   */
  @Override
  public String getPortfolioComposition(String portfolioName, LocalDate date) {
    for (IPortfolioWithTransactions portfolio : this.portfoliosWithDates) {
      if (portfolio.getName().equals(portfolioName)) {
        return portfolio.getComposition(date);
      }
    }
    throw new IllegalArgumentException("Portfolio does not exist");
  }

  /**
   * Determine the value of a portfolio on a specific date (to be exact, the end of that day).
   * The value for a portfolio before the date of its first purchase would be 0, since each stock
   * in the portfolio now was purchased at a specific point in time.
   */
  @Override
  public double getPortfolioValue2(String portfolioName, LocalDate date) {
    for (IPortfolioWithTransactions portfolio : this.portfoliosWithDates) {
      if (portfolio.getName().equals(portfolioName)) {
        if (portfolio.isDateBeforeFirstTransaction(date)) {
          return 0;
        }
        return portfolio.getValue(date);
      }
    }
    throw new IllegalArgumentException("Portfolio does not exist");
  }

  /**
   * The distribution of value of a portfolio on a specific date (to be exact, the end of that day).
   * The distribution of value should include (a) the stock itself (b) the value of each individual
   * stock in the portfolio. The sum of the values in (b) should equal to the value of that
   * portfolio on that date.
   */
  @Override
  public String getPortfolioValueDistribution(String portfolioName, LocalDate date) {
    for (IPortfolioWithTransactions portfolio : this.portfoliosWithDates) {
      if (portfolio.getName().equals(portfolioName)) {
        return portfolio.getValueDistribution(date);
      }
    }
    throw new IllegalArgumentException("Portfolio does not exist");
  }

  @Override
  public List<String> getStocksInPortfolio(String portfolioName) {
    for (IPortfolioWithTransactions portfolio : this.portfoliosWithDates) {
      if (portfolio.getName().equals(portfolioName)) {
        return portfolio.getStocks();
      }
    }
    throw new IllegalArgumentException("Portfolio does not exist");
  }

  /**
   * Get the portfolio reader for the given portfolio name. This implementation of IModel2 uses
   * a TxtPortfolioReader to read portfolios. This can be overridden in a subclass to use a
   * different portfolio reader.
   * @param portfolioName the name of the portfolio to get the reader for.
   * @return the portfolio reader for the given portfolio name.
   */
  protected IPortfolioWithTransactionsReader getPortfolioReader(String portfolioName) {
    return new TxtPortfolioReader(portfolioName);
  }

  @Override
  public String getPortfolioPerformanceOverTime(String portfolioName, LocalDate startDate,
                                         LocalDate endDate) {
    for (IPortfolioWithTransactions portfolio : this.portfoliosWithDates) {
      if (portfolio.getName().equals(portfolioName)) {
        return portfolio.getPerformanceOverTime(startDate, endDate);
      }
    }
    throw new IllegalArgumentException("Portfolio does not exist");
  }

  @Override
  public List<LocalDate> getPortfolioPerformanceDates(String portfolioName, LocalDate startDate,
                                             LocalDate endDate) {
    for (IPortfolioWithTransactions portfolio : this.portfoliosWithDates) {
      if (portfolio.getName().equals(portfolioName)) {
        return portfolio.getPerformanceDates(startDate, endDate);
      }
    }
    throw new IllegalArgumentException("Portfolio does not exist");
  }
}
