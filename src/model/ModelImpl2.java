package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import model.portfolio.IPortfolioWithDates;

import model.portfolio.Transaction;


public class ModelImpl2 extends ModelImpl implements IModel2 {
  private final List<IPortfolioWithDates> portfoliosWithDates;

  public ModelImpl2() {
    super();
    this.portfoliosWithDates = new ArrayList<>();
  }

  @Override
  public void buyPortfolioHolding(String portfolioName, String ticker, double quantity,
                                  LocalDate date) {
    for (IPortfolioWithDates portfolio : this.portfoliosWithDates) {
      if (portfolio.getName().equals(portfolioName)) {
        IPortfolioWithDates newPortfolio = portfolio.buyTransaction(
                new Transaction(getStock(ticker), quantity, date));
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
    for (IPortfolioWithDates portfolio : this.portfoliosWithDates) {
      if (portfolio.getName().equals(portfolioName)) {
        IPortfolioWithDates newPortfolio =
                portfolio.sellTransaction(new Transaction(getStock(ticker), quantity, date));
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
    for (IPortfolioWithDates portfolio : this.portfoliosWithDates) {
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
    for (IPortfolioWithDates portfolio : this.portfoliosWithDates) {
      if (portfolio.getName().equals(portfolioName)) {
        if (portfolio.isDateBeforeFirstTransaction(date)) {
          return 0;
        }
        return portfolio.getValue();
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
    for (IPortfolioWithDates portfolio : this.portfoliosWithDates) {
      if (portfolio.getName().equals(portfolioName)) {
        return portfolio.getValueDistribution(date);
      }
    }
    throw new IllegalArgumentException("Portfolio does not exist");
  }
}
