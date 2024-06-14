package controller.io.writers;

import model.portfolio.IPortfolioWithTransactions;

/**
 * This interface represents a writer that writes a portfolio with transactions.
 * The writer writes the portfolio with transactions to a file.
 * The writer can be used to write a portfolio with transactions to a file.
 */
public interface IPortfolioWriter {
  /**
   * Writes the portfolio with transactions to a file.
   *
   * @param portfolio the portfolio with transactions to write
   */
  void write(IPortfolioWithTransactions portfolio);
}
