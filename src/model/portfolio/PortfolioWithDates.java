package model.portfolio;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PortfolioWithDates implements IPortfolioWithDates {
  private final String name;
  private final List<ITransaction> transactions;

  public PortfolioWithDates(String name) {
    this.name = name;
    this.transactions = new ArrayList<>();
  }

  private PortfolioWithDates(String name, List<ITransaction> transactions) {
    this.name = name;
    this.transactions = transactions;
  }

  @Override
  public IPortfolioWithDates buyTransaction(ITransaction transaction) {
    List<ITransaction> newTransactions = new ArrayList<>(transactions);
    String newTransactionTicker = transaction.getStock().getTicker();
    for (int i = 0; i < transactions.size(); i++) {
      String transactionTicker = transactions.get(i).getStock().getTicker();
      if (newTransactionTicker.equals(transactionTicker)) {
        newTransactions.set(i, transactions.get(i).addQuantity(transaction.getQuantity()));
        return new PortfolioWithDates(name, newTransactions);
      }
    }
    newTransactions.add(transaction);
    return new PortfolioWithDates(name, newTransactions);
  }

  @Override
  public IPortfolioWithDates sellTransaction(ITransaction transaction) {
    List<ITransaction> newTransactions = new ArrayList<>(transactions);
    String newTransactionTicker = transaction.getStock().getTicker();
    for (int i = 0; i < transactions.size(); i++) {
      String transactionTicker = transactions.get(i).getStock().getTicker();
      if (newTransactionTicker.equals(transactionTicker)) {
        newTransactions.set(i, transactions.get(i).removeQuantity(transaction.getQuantity()));
        return new PortfolioWithDates(name, newTransactions);
      }
    }
    throw new IllegalArgumentException("Transaction does not exist");
  }

  @Override
  public String getComposition(LocalDate date) {
    StringBuilder composition = new StringBuilder();
    for (ITransaction transaction : transactions) {
      if (transaction.getStock().getClosePrice(date) != 0) {
        composition.append(transaction.getStock().getTicker()).append(": ")
                .append(transaction.getQuantity()).append("\n");
      }
    }
    return composition.toString();
  }

  public String getValueDistribution(LocalDate date) {
    StringBuilder distribution = new StringBuilder();
    for (ITransaction transaction : transactions) {
      if (transaction.getStock().getClosePrice(date) != 0) {
        distribution.append(transaction.getStock().getTicker()).append(": ")
                .append(transaction.getValue()).append("\n");
      }
    }
    return distribution.toString();
  }



  @Override
  public double getValue() {
    double value = 0;
    for (ITransaction transaction : transactions) {
      value += transaction.getValue();
    }
    return value;
  }

  @Override
  public List<ITransaction> getTransactions() {
    return List.of();
  }

  @Override
  public String getName() {
    return "";
  }

  @Override
  public List<String> getStocks() {
    return List.of();
  }

  @Override
  public boolean isDateBeforeFirstTransaction(LocalDate date) {
    for (ITransaction transaction : transactions) {
      if (transaction.getDate().isBefore(date)) {
        return false;
      }
    }
    return true;
  }
}
