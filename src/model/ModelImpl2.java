package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import controller.io.readers.IPortfolioWithTransactionsReader;
import controller.io.readers.TxtPortfolioReader;
import controller.io.writers.IPortfolioWriter;
import controller.io.writers.PortfolioTxtWriter;
import model.portfolio.BuyTransaction;
import model.portfolio.IPortfolioWithTransactions;

import model.portfolio.PortfolioWithTransactions;
import model.portfolio.SellTransaction;

/**
 * Represents a model that can be used to create a portfolio, add a holding to a portfolio, get the
 * value of a portfolio, and get the stocks in a portfolio.
 */
public class ModelImpl2 extends ModelImpl implements IModel2 {
  private final List<IPortfolioWithTransactions> portfolios;

  /**
   * Constructs a new model with an empty list of portfolios.
   */
  public ModelImpl2() {
    super();
    this.portfolios = new ArrayList<>();
  }

  /**
   * Add a holding of the given stock to the portfolio with the given name of the given quantity.
   * @param portfolioName the name of the portfolio.
   * @param ticker the ticker of the stock.
   * @param quantity the quantity of the stock.
   */
  @Override
  public void addPortfolioHolding(String portfolioName, String ticker, double quantity) {
    throw new UnsupportedOperationException("This method is not supported in this model. Use the "
            + "buyPortfolioHolding method instead.");
  }

  /**
   * Create and store a portfolio with the given name.
   * @param name the name of the portfolio.
   */
  @Override
  public void createPortfolio(String name) {
    for (IPortfolioWithTransactions portfolio : this.portfolios) {
      if (portfolio.getName().equals(name)) {
        throw new IllegalArgumentException("Portfolio already exists");
      }
    }
    IPortfolioWithTransactions newPortfolio = new PortfolioWithTransactions(name);
    this.portfolios.add(newPortfolio);
    updatePortfolioFile(name);
  }

  /**
   * Update the portfolio file with the given name.
   * @param portfolioName the name of the portfolio to update.
   */
  protected void updatePortfolioFile(String portfolioName) {
    for (IPortfolioWithTransactions portfolio : this.portfolios) {
      if (portfolio.getName().equals(portfolioName)) {
        getPortfolioWriter().write(portfolio);
        return;
      }
    }
    throw new IllegalArgumentException("Portfolio does not exist");
  }

  /**
   * Purchase a specific number of shares of a specific stock on a specified date, and add them to
   * the portfolio
   */
  @Override
  public void buyPortfolioHolding(String portfolioName, String ticker, int quantity,
                                  LocalDate date) {
    for (IPortfolioWithTransactions portfolio : this.portfolios) {
      if (portfolio.getName().equals(portfolioName)) {
        if (!(portfolio.getTransactions().isEmpty())
                && portfolio.getLatestTransactionDate().isAfter(date)) {
          throw new IllegalArgumentException("Date is before latest transaction");
        }
        IPortfolioWithTransactions newPortfolio = portfolio.addTransaction(
                new BuyTransaction(getStock(ticker), quantity, date));
        this.portfolios.remove(portfolio);
        this.portfolios.add(newPortfolio);
        updatePortfolioFile(portfolioName);
        return;
      }
    }
    throw new IllegalArgumentException("Portfolio does not exist");
  }

  /**
   * Sells a specific number of shares of a specific stock on a specified date, and remove them from
   * the portfolio
   */
  @Override
  public void sellPortfolioHolding(String portfolioName, String ticker, double quantity,
                                   LocalDate date) {

    for (IPortfolioWithTransactions portfolio : this.portfolios) {
      if (portfolio.getName().equals(portfolioName)) {
        if (!(portfolio.getTransactions().isEmpty())
                && portfolio.getLatestTransactionDate().isAfter(date)) {
          throw new IllegalArgumentException("Date is before latest transaction");
        }
        IPortfolioWithTransactions newPortfolio =
                portfolio.addTransaction(new SellTransaction(getStock(ticker), quantity, date));
        this.portfolios.remove(portfolio);
        this.portfolios.add(newPortfolio);
        updatePortfolioFile(portfolioName);
        return;
      }
    }
  }

  /**
   * Get the value of the portfolio with the given name on the given date.
   * @param portfolioName the name of the portfolio.
   * @param date the date to get the value of the portfolio on.
   * @return the value of the portfolio on the given date.
   */
  @Override
  public double getPortfolioValue(String portfolioName, LocalDate date) {
    for (IPortfolioWithTransactions portfolio : this.portfolios) {
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
   * Determines the composition of a portfolio at a specific date. Note that the composition may
   * change over time. The composition must include (a) the list of stocks and (b) the number of
   * shares of each stock
   */
  @Override
  public String getPortfolioComposition(String portfolioName, LocalDate date) {
    for (IPortfolioWithTransactions portfolio : this.portfolios) {
      if (portfolio.getName().equals(portfolioName)) {
        return portfolio.getComposition(date);
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
    for (IPortfolioWithTransactions portfolio : this.portfolios) {
      if (portfolio.getName().equals(portfolioName)) {
        return portfolio.getValueDistribution(date);
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
    for (IPortfolioWithTransactions portfolio : this.portfolios) {
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

  /**
   * Get the portfolio writer for this model. This implementation of IModel2 uses a PortfolioTxtWriter
   * to write portfolios. This can be overridden in a subclass to use a different portfolio writer.
   * @return the portfolio writer for this model.
   */
  protected IPortfolioWriter getPortfolioWriter() {
    return new PortfolioTxtWriter();
  }

  /**
   * Get the value of the portfolio over time given a period of time.
   * @param portfolioName the name of the portfolio
   * @param startDate the start date
   * @param endDate the end date
   * @return the value of the portfolio over time as a bar chart
   */
  @Override
  public String getPortfolioPerformanceOverTime(String portfolioName, LocalDate startDate,
                                         LocalDate endDate) {
    for (IPortfolioWithTransactions portfolio : this.portfolios) {
      if (portfolio.getName().equals(portfolioName)) {
        return portfolio.getPerformanceOverTime(startDate, endDate);
      }
    }
    throw new IllegalArgumentException("Portfolio does not exist");
  }

  /**
   * Load a portfolio from a file placed in the "portfolios" directory. The file should be named as
   * the portfolio name.
   * @param portfolioName is the name of the portfolio to be loaded into the model.
   */
  @Override
  public void loadPortfolio(String portfolioName) {
    portfolios.removeIf(portfolio -> portfolio.getName().equals(portfolioName));
    portfolios.add(getPortfolioReader(portfolioName).getPortfolio());
  }

  /**
   * Re-balance the portfolio to the given stock weights on the given date.
   * @param portfolioName the name of the portfolio
   * @param date the date to re-balance the portfolio.
   * @param stockWeights the weights of the stocks in the portfolio.
   */
  @Override
  public void rebalancePortfolio(String portfolioName, LocalDate date,
                                 Map<String, Integer> stockWeights) {
    for (IPortfolioWithTransactions portfolio : this.portfolios) {
      if (portfolio.getName().equals(portfolioName)) {
        portfolio.rebalance(date, stockWeights);
        updatePortfolioFile(portfolioName);
        return;
      }
    }
    throw new IllegalArgumentException("Portfolio does not exist");
  }
}
