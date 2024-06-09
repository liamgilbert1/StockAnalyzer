package model;

import java.time.LocalDate;

import model.portfolio.Holding;
import model.portfolio.IPortfolio;
import model.portfolio.PortfolioWithDates;
import model.stock.IStock;
import model.stock.Stock;

public class ModelImpl2 extends ModelImpl implements IModel2 {

  public ModelImpl2() {
    super();
  }

  @Override
  public void buyPortfolioHolding(String portfolioName, String ticker, double quantity,
                                  LocalDate date) {
    IPortfolio portfolioWithDate = new PortfolioWithDates(portfolioName, date);
    for (IPortfolio portfolio : portfolios) {
      if (portfolio.getName().equals(portfolioName)) {
        IStock stock = new Stock(ticker);
        Holding holding = new Holding(stock, quantity);
        IPortfolio newPortfolio = portfolioWithDate.addHolding(holding);
        portfolios.remove(portfolio);
        portfolios.add(newPortfolio);
        return;
      }
    }
    throw new IllegalArgumentException("Portfolio does not exist");
  }

  @Override
  public void sellPortfolioHolding(String portfolioName, String ticker, double quantity, LocalDate date) {

  }

  @Override
  public String getPortfolioComposition(String portfolioName, LocalDate date) {
    return "";
  }

  @Override
  public double getPortfolioValue2(String portfolioName, LocalDate date) {
    return 0;
  }

  @Override
  public String getPortfolioValueDistribution(String portfolioName, LocalDate date) {
    return "";
  }
}
