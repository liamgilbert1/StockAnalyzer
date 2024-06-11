package controller.writers;

import model.portfolio.IPortfolioWithTransactions;

public interface IPortfolioWriter {
  void write(IPortfolioWithTransactions portfolio);
}
