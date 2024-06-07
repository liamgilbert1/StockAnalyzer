package model;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

import model.stock.IStock;


public class MockModelImpl implements IModel {
  private final StringBuilder log;


  public MockModelImpl(StringBuilder log) {
    this.log = Objects.requireNonNull(log);
  }


  @Override
  public double calculateGainOrLoss(String ticker, LocalDate startDate, LocalDate endDate) {
    log.append("GainOrLoss ").append(ticker).append(" ").append(startDate.toString()).append(" ")
            .append(endDate.toString());
    return 0;
  }

  @Override
  public double movingAverage(String ticker, LocalDate date, int days) {
    log.append("MovingAverage ").append(ticker).append(" ").append(date.toString()).append(" ")
            .append(days);
    return 0;
  }

  @Override
  public List<LocalDate> crossOver(String ticker, LocalDate startDate, LocalDate endDate,
                                   int days) {
    log.append("Crossover ").append(ticker).append(" ").append(startDate.toString()).append(" ")
            .append(endDate.toString()).append(" ").append(days);
    return null;
  }

  @Override
  public void createPortfolio(String name) {
    log.append("CreatePortfolio ").append(name);
  }

  @Override
  public void addPortfolioHolding(String portfolioName, String ticker, double quantity) {
    log.append("AddPortfolioHolding ").append(portfolioName).append(" ").append(ticker).append(" ")
            .append(quantity);
  }

  @Override
  public double getPortfolioValue(String portfolioName, LocalDate date) {
    log.append("GetPortfolioValue ").append(portfolioName).append(" ").append(date.toString());
    return 0;
  }

  @Override
  public List<String> getStocksInPortfolio(String portfolioName) {
    return null;
  }

  @Override
  public IStock getStock(String ticker) {
    return null;
  }

  @Override
  public List<String> getPortfolioNames() {
    return null;
  }
}
