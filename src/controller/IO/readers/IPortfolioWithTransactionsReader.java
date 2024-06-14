package controller.IO.readers;

import model.portfolio.IPortfolioWithTransactions;

/**
 * This interface represents a reader that reads a portfolio with transactions.
 * The reader reads the portfolio with transactions and returns it.
 * The reader can be used to read a portfolio with transactions from a file.
 */
public interface IPortfolioWithTransactionsReader extends IReader {
  /**
   * Gets the portfolio with transactions from the reader.
   *
   * @return the portfolio with transactions from the reader
   */
  IPortfolioWithTransactions getPortfolio();
}
