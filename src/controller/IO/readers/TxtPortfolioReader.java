package controller.IO.readers;

import java.io.File;
import java.io.StringReader;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import controller.IO.IOUtils;
import model.portfolio.BuyTransaction;
import model.portfolio.IPortfolioWithTransactions;
import model.portfolio.ITransaction;
import model.portfolio.PortfolioWithTransactions;
import model.portfolio.SellTransaction;
import model.stock.Stock;


/**
 * This class represents a TxtPortfolioReader that reads a portfolio from a txt file. The txt file
 * should be formatted by having the first line be the name of the portfolio, and the transactions
 * below it. The transactions are formatted by have the date of the transaction yyyy-mm-dd one
 * line, and all the transactions which occurred on that date below it, each on a new line.
 * The transactions are formatted by having the action (buy or sell), the number of shares,
 * "shares", and the ticker symbol of the stock, each separated by a space. For example:
 * PortfolioName
 * 2020-01-01
 * buy 10 shares AAPL
 * sell 5 shares AAPL
 * 2020-01-02
 * buy 5 shares AAPL
 * sell 2 shares AAPL
 * The transactions and dates may be in any order, but the transactions for a given date must be
 * grouped together.
 */
public class TxtPortfolioReader implements IPortfolioWithTransactionsReader {
  private final String portfolioName;

  public TxtPortfolioReader(String portfolioName) {
    this.portfolioName = portfolioName;
  }

  @Override
  public Readable getReadable() {
    File file = IOUtils.getFile(portfolioName, ".txt", "portfolios");
    Appendable portfolioData = new StringBuilder();
    try (Scanner scanner = new Scanner(file)) {
      while (scanner.hasNext()) {
        portfolioData.append(scanner.next());
        portfolioData.append("\n");
      }
    } catch (Exception e) {
      throw new IllegalStateException("Could not read from file");
    }
    return new StringReader(portfolioData.toString());
  }

  @Override
  public IPortfolioWithTransactions getPortfolio() {
    Readable readable = getReadable();
    Scanner scanner = new Scanner(readable);
    LocalDate currentDate = null;
    String currentString;
    String ticker;
    String quantity;
    List<ITransaction> transactions = new ArrayList<>();
    while (scanner.hasNext()) {
      currentString = scanner.next();
      try {
        currentDate = LocalDate.parse(currentString);
      } catch (DateTimeParseException e) {
        if (currentDate == null) {
          continue;
        }
        try {
          quantity = scanner.next();
          scanner.next();
          ticker = scanner.next();
        } catch (Exception e2) {
          throw new IllegalStateException("Failed to parse transaction data.");
        }
        transactions.add(parseTransaction(currentString, quantity, ticker, currentDate));
      }
    }
    return createPortfolio(portfolioName, transactions);
  }

  private ITransaction parseTransaction(String action, String quantity, String ticker,
                                        LocalDate currentDate) {
    switch (action) {
      case "buy":
        try {
          int intQuantity = Math.toIntExact(Math.round(Double.parseDouble(quantity)));
          return new BuyTransaction(new Stock(ticker), intQuantity,
                  currentDate);
        } catch (Exception e4) {
          throw new IllegalStateException("Failed to parse buy transaction.");
        }
      case "sell":
        return new SellTransaction(new Stock(ticker),
                Double.parseDouble(quantity), currentDate);
      default:
        throw new IllegalStateException("Invalid transaction type.");
    }
  }

  private IPortfolioWithTransactions createPortfolio(String name, List<ITransaction> transactions) {
    IPortfolioWithTransactions portfolio = new PortfolioWithTransactions(name);
    for (ITransaction transaction : transactions) {
      portfolio = portfolio.addTransaction(transaction);
    }
    return portfolio;
  }
}


