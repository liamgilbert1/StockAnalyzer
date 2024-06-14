package controller.io.writers;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import controller.io.IOUtils;

import model.portfolio.IPortfolioWithTransactions;
import model.portfolio.ITransaction;

/**
 * This class represents a PortfolioTxtWriter that writes a portfolio to a txt file. The txt file
 * will be named after the portfolio and will be saved in the "portfolios" directory.
 * The portfolio is written in the format of a txt file.
 */
public class PortfolioTxtWriter implements IPortfolioWriter {

  /**
   * Writes the given portfolio to a txt file. The file will be named after the portfolio and will
   * be saved in the "portfolios" directory.
   *
   * @param portfolio the portfolio to write.
   */
  @Override
  public void write(IPortfolioWithTransactions portfolio) {
    File file = IOUtils.getFile(portfolio.getName(), ".txt", "portfolios");

    try (FileWriter writer = new FileWriter(file)) {
      writer.write(portfolio.getName());
      List<ITransaction> transactions = portfolio.getTransactions();
      for (ITransaction transaction : transactions) {
        writer.write("\n\n");
        writer.write(transaction.getDate().toString());
        writer.write("\n");
        writer.write(transaction.action() + " "
                + transaction.getQuantity() + " shares "
                + transaction.getStock().getTicker());
      }
    } catch (IOException e) {
      throw new IllegalStateException("Could not write portfolio to txt file.");
    }
  }
}
