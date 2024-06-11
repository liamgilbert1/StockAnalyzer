package controller.writers;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import model.portfolio.IPortfolioWithTransactions;
import model.portfolio.ITransaction;

public class PortfolioTxtWriter implements IPortfolioWriter {
  @Override
  public void write(IPortfolioWithTransactions portfolio) {
    File file = new File(portfolio.getName() + ".txt");
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
