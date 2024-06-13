package controller.IO.readers;

import model.portfolio.IPortfolioWithTransactions;

public interface IPortfolioWithTransactionsReader extends IReader {
  IPortfolioWithTransactions getPortfolio();
}
