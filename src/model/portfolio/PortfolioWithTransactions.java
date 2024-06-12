package model.portfolio;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PortfolioWithTransactions implements IPortfolioWithTransactions {
  private final String name;
  private final List<ITransaction> transactions;

  public PortfolioWithTransactions(String name) {
    this.name = name;
    this.transactions = new ArrayList<>();
  }

  private PortfolioWithTransactions(String name, List<ITransaction> transactions) {
    this.name = name;
    this.transactions = transactions;
  }

  @Override
  public IPortfolioWithTransactions addTransaction(ITransaction transaction) {
    if (isTransactionBefore(transaction)) {
      throw new IllegalArgumentException("New transaction for this stock can't be before " +
              "the stock's last transaction.");
    }
    List<ITransaction> newTransactions = new ArrayList<>(transactions);
    newTransactions.add(transaction);
    return new PortfolioWithTransactions(name, newTransactions);
  }

  private boolean isTransactionBefore(ITransaction transaction) {
    String newTransactionTicker = transaction.getStock().getTicker();
    for (ITransaction iTransaction : transactions) {
      String transactionTicker = iTransaction.getStock().getTicker();
      if (newTransactionTicker.equals(transactionTicker)) {
        if (iTransaction.getDate().isAfter(transaction.getDate())) {
          return true;
        }
      }
    }
    return false;
  }

  @Override
  public String getComposition(LocalDate date) {
    StringBuilder composition = new StringBuilder();
    for (ITransaction transaction : transactions) {
      if (transaction.getStock().getClosePrice(date) != 0
      && !composition.toString().contains(transaction.getStock().getTicker())) {
        composition.append(transaction.getStock().getTicker()).append(": ")
                .append(String.format("%.2f",getStockQuantity(transaction))).append("\n");
      }
    }
    return composition.toString();
  }

  private double getStockQuantity(ITransaction transaction) {
    double quantity = 0;
    for (ITransaction iTransaction : transactions) {
      if (iTransaction.getStock().getTicker().equals(transaction.getStock().getTicker())) {
        quantity += iTransaction.realQuantity();
      }
    }
    return quantity;
  }

  public String getValueDistribution(LocalDate date) {
    StringBuilder distribution = new StringBuilder();
    for (ITransaction transaction : transactions) {
      if (transaction.getStock().getClosePrice(date) != 0
              && !distribution.toString().contains(transaction.getStock().getTicker())) {
        distribution.append(transaction.getStock().getTicker()).append(": ")
                .append(String.format("%.2f", getStockValue(transaction))).append("\n");
      }
    }
    return distribution.toString();
  }

  private double getStockValue(ITransaction transaction) {
    double value = 0;
    for (ITransaction iTransaction : transactions) {
      if (iTransaction.getStock().getTicker().equals(transaction.getStock().getTicker())) {
        value += iTransaction.getValue();
      }
    }
    return value;
  }

  @Override
  public double getValue(LocalDate date) {
    double value = 0;
    for (ITransaction transaction : transactions) {
      value += transaction.getValue();
    }
    return value;
  }

  @Override
  public List<ITransaction> getTransactions() {
    List<ITransaction> transactionsCopy = new ArrayList<>();
    for (ITransaction transaction : transactions) {
      transactionsCopy.add(transaction.getCopy());
    }
    return transactionsCopy;
  }

  @Override
  public String getName() {
    return this.name;
  }

  @Override
  public List<String> getStocks() {
    List<String> stocks = new ArrayList<>();
    for (ITransaction transaction : transactions) {
      if (!stocks.contains(transaction.getStock().getTicker())) {
        stocks.add(transaction.getStock().getTicker());
      }
    }
    return stocks;
  }

  @Override
  public boolean isDateBeforeFirstTransaction(LocalDate date) {
    for (ITransaction transaction : transactions) {
      if (transaction.getDate().isAfter(date)) {
        return true;
      }
    }
    return false;
  }

  @Override
  public IPortfolioWithTransactions loadPortfolio() {
    return null;
  }

}
