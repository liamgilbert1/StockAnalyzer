package controller.readers;

import java.io.FileReader;
import java.io.StringReader;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import model.portfolio.BuyTransaction;
import model.portfolio.IPortfolio;
import model.portfolio.IPortfolioWithTransactions;
import model.portfolio.ITransaction;
import model.portfolio.PortfolioWithTransactions;
import model.portfolio.SellTransaction;
import model.stock.Stock;

public class TxtPortfolioReader implements IPortfolioReader {
  private final String fileName;

  public TxtPortfolioReader(String fileName) {
    this.fileName = fileName;
  }

  @Override
  public Readable getReadable() {
    Appendable portfolioData = new StringBuilder();
    try (Scanner scanner = new Scanner(new FileReader(fileName))) {
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
  public IPortfolio getPortfolio() {
    Readable readable = new TxtPortfolioReader("myPortfolio.txt").getReadable();
    Scanner scanner = new Scanner(readable);
    LocalDate currentDate = null;
    String currentString;
    String stockSymbol;
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
          stockSymbol = scanner.next();
        } catch (Exception e2) {
          throw new IllegalStateException("Failed to parse transaction data.");
        }
        switch (currentString) {
          case "buy":
            try {
              int intQuantity = Math.toIntExact(Math.round(Double.parseDouble(quantity)));
              transactions.add(new BuyTransaction(new Stock(stockSymbol), intQuantity,
                      currentDate));
            } catch (Exception e4) {
              throw new IllegalStateException("Failed to parse buy transaction.");
            }
            break;
          case "sell":
            transactions.add(new SellTransaction(new Stock(stockSymbol),
                    Double.parseDouble(quantity), currentDate));
            break;
          default:
            throw new IllegalStateException("Invalid transaction type.");
        }
      }
    }
    IPortfolioWithTransactions portfolio = new PortfolioWithTransactions("testPortfolio");
    for (ITransaction transaction : transactions) {
      portfolio = portfolio.addTransaction(transaction);
    }
    return portfolio;
  }
}
