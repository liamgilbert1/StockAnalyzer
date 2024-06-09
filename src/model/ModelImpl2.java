package model;

import java.time.LocalDate;
import java.util.List;

import model.stock.IStock;

public class ModelImpl2 implements IModel2 {
  private final IModel delegateModel;

  public ModelImpl2(IModel delegateModel) {
    this.delegateModel = new ModelImpl();
  }

  @Override
  public double calculateGainOrLoss(String ticker, LocalDate startDate, LocalDate endDate) {
    return delegateModel.calculateGainOrLoss(ticker, startDate, endDate);
  }

  @Override
  public double movingAverage(String ticker, LocalDate date, int days) {
    return delegateModel.movingAverage(ticker, date, days);
  }

  @Override
  public List<LocalDate> crossOver(String ticker, LocalDate startDate, LocalDate endDate, int days) {
    return delegateModel.crossOver(ticker, startDate, endDate, days);
  }

  @Override
  public void createPortfolio(String name) {
    delegateModel.createPortfolio(name);
  }

  @Override
  public void addPortfolioHolding(String portfolioName, String ticker, double quantity) {
    delegateModel.addPortfolioHolding(portfolioName, ticker, quantity);
  }

  @Override
  public double getPortfolioValue(String portfolioName, LocalDate date) {
    return delegateModel.getPortfolioValue(portfolioName, date);
  }

  @Override
  public List<String> getStocksInPortfolio(String portfolioName) {
    return delegateModel.getStocksInPortfolio(portfolioName);
  }

  @Override
  public IStock getStock(String ticker) {
    return delegateModel.getStock(ticker);
  }

  @Override
  public List<String> getPortfolioNames() {
    return delegateModel.getPortfolioNames();
  }
}
