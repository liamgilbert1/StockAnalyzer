package model.portfolio;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import model.stock.IStock;
import model.stock.Stock;

/**
 * Represents a portfolio of holdings. A portfolio is a collection of holdings, and can be used to
 * calculate the value of the holdings in the portfolio. A portfolio can also be used to add a
 * holding to the portfolio.
 */
public class PortfolioWithTransactions implements IPortfolioWithTransactions {
  private final String name;
  private final List<ITransaction> transactions;

  /**
   * Constructs a portfolio with the given name. The portfolio is initially empty.
   *
   * @param name the name of the portfolio.
   */
  public PortfolioWithTransactions(String name) {
    this.name = name;
    this.transactions = new ArrayList<>();
  }

  /**
   * Constructs a portfolio with the given name and transactions.
   *
   * @param name the name of the portfolio.
   * @param transactions the transactions in the portfolio.
   */
  public PortfolioWithTransactions(String name, List<ITransaction> transactions) {
    this.name = name;
    this.transactions = transactions;
  }

  /**
   * Adds the given transaction to the portfolio.
   * @param transaction the transaction to add.
   * @return the portfolio with the given transaction added.
   */
  @Override
  public IPortfolioWithTransactions addTransaction(ITransaction transaction) {
    if (!transactions.isEmpty() && transaction.getDate().isBefore(getLatestTransactionDate())) {
      throw new IllegalArgumentException("Transaction date is before previous transaction date.");
    }
    if (transaction.action().equals("sell") && getStocksQuantity(transaction.getStock().getTicker(),
            transaction.getDate()) < transaction.getQuantity()) {
      List<ITransaction> newTransactions = new ArrayList<>(transactions);
      newTransactions.add(new SellTransaction(transaction.getStock(),
              getStocksQuantity(transaction.getStock().getTicker(), transaction.getDate()),
              transaction.getDate()));
      return new PortfolioWithTransactions(name, newTransactions);
    }
    List<ITransaction> newTransactions = new ArrayList<>(transactions);
    newTransactions.add(transaction);
    return new PortfolioWithTransactions(name, newTransactions);
  }

  /**
   * Gets the composition of the portfolio on the given date (List of stocks and their quantities).
   *
   * @param date the date to get the composition of the portfolio on.
   * @return the composition of the portfolio on the given date.
   */
  @Override
  public String getComposition(LocalDate date) {
    StringBuilder composition = new StringBuilder();
    for (ITransaction transaction : transactions) {
      if (!composition.toString().contains(transaction.getStock().getTicker())
              && (transaction.getDate().isBefore(date) || transaction.getDate().isEqual(date))) {
        composition.append(transaction.getStock().getTicker()).append(": ")
                .append(String.format("%.2f", getStockQuantity(transaction))).append("\n");
      }
    }
    return composition.toString();
  }

  /**
   * Gets the quantity of the stock in the portfolio.
   * @param transaction the transaction to get the quantity of.
   * @return the quantity of the stock in the portfolio.
   */
  private double getStockQuantity(ITransaction transaction) {
    double quantity = 0;
    for (ITransaction iTransaction : transactions) {
      if (iTransaction.getStock().getTicker().equals(transaction.getStock().getTicker())) {
        quantity += iTransaction.realQuantity();
      }
    }
    return quantity;
  }

  /**
   * Gets the value distribution of the portfolio on the given date (List of stocks and their
   * values).
   *
   * @param date the date to get the value distribution of the portfolio on.
   * @return the value distribution of the portfolio on the given date.
   */
  @Override
  public String getValueDistribution(LocalDate date) {
    StringBuilder distribution = new StringBuilder();
    for (IHolding holding : getPortfolioWithHoldings(date).getHoldings()) {
      distribution.append(holding.getStock().getTicker()).append(": $")
              .append(String.format("%.2f", holding.getValue(date))).append("\n");
    }
    return distribution.toString();
  }

  /**
   * Gets the value of the portfolio on the given date.
   * @param date the date to get the value of the portfolio on.
   * @return the value of the portfolio on the given date.
   */
  @Override
  public double getValue(LocalDate date) {
    return this.getPortfolioWithHoldings(date).getValue(date);
  }

  /**
   * Gets the performance of the portfolio over time given a period of time.
   * @param startDate the start date of the period.
   * @param endDate the end date of the period.
   * @return the performance of the portfolio over time.
   */
  @Override
  public String getPerformanceOverTime(LocalDate startDate, LocalDate endDate) {
    StringBuilder performance = new StringBuilder();
    List<String> dates = performanceDatesToString(getPerformanceDates(startDate, endDate));
    List<Double> values = getOverTimeValues(getPerformanceDates(startDate, endDate));
    List<String> asterisks = getAsterisks(values);
    performance.append("Performance of ").append(name).append(" from ").append(startDate)
            .append(" to ").append(endDate).append("\n\n");
    for (int i = 0; i < dates.size(); i++) {
      performance.append(dates.get(i)).append(": ").append(asterisks.get(i)).append("\n");
    }
    performance.append("\n").append("Scale: * = $").append(getScale(Collections.max(values)))
            .append("\n");
    return performance.toString();
  }

  /**
   * Gets the performance dates of the portfolio given a period of time.
   * @param startDate the start date of the period.
   * @param endDate the end date of the period.
   * @return the performance dates of the portfolio.
   */
  @Override
  public List<LocalDate> getPerformanceDates(LocalDate startDate, LocalDate endDate) {
    List<LocalDate> dates = new ArrayList<>();
    long daysBetween = ChronoUnit.DAYS.between(startDate, endDate);
    if (daysBetween < 0) {
      throw new IllegalArgumentException("End date must be after start date.");
    } else if (daysBetween < 30) {
      return this.addDaysOccurrence(startDate, endDate, dates, 1);
    } else if (daysBetween < 90) {
      return this.addDaysOccurrence(startDate, endDate, dates, 3);
    } else if (daysBetween < 180) {
      return this.addDaysOccurrence(startDate, endDate, dates, 7);
    } else if (daysBetween < 730) {
      return this.addMonthsOccurrence(startDate, endDate, dates, 1);
    } else if (daysBetween < 1830) {
      return this.addMonthsOccurrence(startDate, endDate, dates, 3);
    }
    return this.addYearOccurrence(startDate, endDate, dates);
  }

  /**
   * Rebalances the portfolio to the given stock weights on the given date.
   * @param date the date to rebalance the portfolio on.
   * @param stockWeights the stock weights to rebalance the portfolio to.
   */
  @Override
  public void rebalance(LocalDate date, Map<String, Integer> stockWeights) {
    if (date.isBefore(getLatestTransactionDate())) {
      throw new IllegalArgumentException("Rebalance date is before latest transaction date.");
    }
    int totalWeight = 0;
    for (int stockWeight : stockWeights.values()) {
      totalWeight += stockWeight;
    }
    if (totalWeight != 100) {
      throw new IllegalArgumentException("Stock weights must add up to 100.");
    }
    double currentPortfolioValue = getValue(date);
    for (String stock : getStocksOnDate(date)) {
      if (!stockWeights.containsKey(stock)) {
        transactions.add(new SellTransaction(getStock(stock),
                getStocksQuantity(stock, date), date));
      }
    }
    for (String stock : stockWeights.keySet()) {
      double stockValue = getStocksQuantity(stock, date) * getStock(stock).getClosePrice(date);
      double targetValue = currentPortfolioValue * (stockWeights.get(stock) / 100.0);
      double quantityToBuy = targetValue - stockValue;
      double quantityInShares = quantityToBuy / getStock(stock).getClosePrice(date);
      if (quantityToBuy > 0) {
        transactions.add(new BuyTransaction(getStock(stock), quantityInShares, date));
      }
      else if (quantityToBuy < 0) {
        transactions.add(new SellTransaction(getStock(stock), -quantityInShares, date));
      }
    }
  }

  /**
   * Adds the given number of days to the list of dates.
   * @param startDate the start date.
   * @param endDate the end date.
   * @param dates the list of dates.
   * @param daysToAdd the number of days to add.
   * @return the list of dates with the added days.
   */
  private List<LocalDate> addDaysOccurrence(LocalDate startDate, LocalDate endDate, List<LocalDate> dates,
                                            int daysToAdd) {
    for (LocalDate date = startDate; date.isBefore(endDate.plusDays(1));
         date = date.plusDays(daysToAdd)) {
      dates.add(date);
    }
    return dates;
  }

  /**
   * Adds the given number of months to the list of dates.
   * @param startDate the start date.
   * @param endDate the end date.
   * @param dates the list of dates.
   * @param monthsToAdd the number of months to add.
   * @return the list of dates with the added months.
   */
  private List<LocalDate> addMonthsOccurrence(LocalDate startDate, LocalDate endDate,
                                              List<LocalDate> dates, int monthsToAdd) {
    for (LocalDate date = startDate; date.isBefore(endDate); date = date.plusMonths(monthsToAdd)) {
      dates.add(date.with(TemporalAdjusters.lastDayOfMonth()));
    }
    return dates;
  }

  /**
   * Adds the given number of years to the list of dates.
   * @param startDate the start date.
   * @param endDate the end date.
   * @param dates the list of dates.
   * @return the list of dates with the added years.
   */
  private List<LocalDate> addYearOccurrence(LocalDate startDate, LocalDate endDate,
                                            List<LocalDate> dates) {
    for (LocalDate date = startDate; date.isBefore(endDate); date = date.plusYears(1)) {
      dates.add(date.with(TemporalAdjusters.lastDayOfYear()));
    }
    return dates;
  }

  /**
   * Converts a list of dates to a list of strings.
   * @param dates the list of dates.
   * @return the list of strings.
   */
  private List<String> performanceDatesToString(List<LocalDate> dates) {
    List<String> dateStrings = new ArrayList<>();
    for (LocalDate date : dates) {
      if (ChronoUnit.DAYS.between(dates.get(0), dates.get(dates.size() - 1)) <= 180) {
        String dateStr = String.format("%02d %s %d", date.getDayOfMonth(),
                date.getMonth().toString().substring(0, 3), date.getYear());
        dateStrings.add(dateStr);
      }
      else if (ChronoUnit.DAYS.between(dates.get(0), dates.get(dates.size() - 1)) <= 1830) {
        dateStrings.add(date.getMonth().toString().substring(0, 3) + " " + date.getYear());
      }
      else {
        dateStrings.add(date.getYear() + "");
      }
    }
    return dateStrings;
  }

  /**
   * Gets the asterisks for the performance over time.
   * @param values the values of the portfolio over time.
   * @return the asterisks for the performance over time.
   */
  private List<String> getAsterisks(List<Double> values) {
    List<String> asterisks = new ArrayList<>();
    double maxValue = Collections.max(values);
    int scale = Integer.parseInt(getScale(maxValue));
    for (Double value : values) {
      StringBuilder asteriskString = new StringBuilder();
      double numAsterisks = Math.round(value / scale);
      for (int i = 0; i < numAsterisks; i++) {
        asteriskString.append("*");
      }
      asterisks.add(asteriskString.toString());
    }
    return asterisks;
  }

  /**
   * Gets the scale for the performance over time.
   * @param maxValue the maximum value of the portfolio over time.
   * @return the scale for the performance over time.
   */
  private String getScale(double maxValue) {
    double scale;
    if (maxValue <= 300) {
      return "10";
    }
    else if (maxValue <= 3000) {
      return "100";
    }
    else {
      scale = Math.ceil(maxValue / 50 / 1000) * 1000;
    }
    return String.format("%.0f", scale);
  }

  /**
   * Gets the values of the portfolio over time.
   * @param dates the dates of the portfolio over time.
   * @return the values of the portfolio over time.
   */
  private List<Double> getOverTimeValues(List<LocalDate> dates) {
    List<Double> values = new ArrayList<>();
    for (LocalDate date : dates) {
      values.add(this.getPortfolioWithHoldings(date).getValue(date));
    }
    return values;
  }

  /**
   * Gets the list of transactions in the portfolio.
   *
   * @return the transactions in the portfolio.
   */
  @Override
  public List<ITransaction> getTransactions() {
    List<ITransaction> transactionsCopy = new ArrayList<>();
    for (ITransaction transaction : transactions) {
      transactionsCopy.add(transaction.getCopy());
    }
    return transactionsCopy;
  }

  /**
   * Gets the name of the portfolio.
   * @return the name of the portfolio.
   */
  @Override
  public String getName() {
    return this.name;
  }

  /**
   * Gets the stocks in the portfolio.
   * @return the stocks in the portfolio.
   */
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

  /**
   * Gets the stock with the given ticker.
   * @param ticker the ticker of the stock.
   * @return the stock with the given ticker.
   */
  private IStock getStock(String ticker) {
    return new Stock(ticker);
  }

  /**
   * Gets the stocks in the portfolio on the given date.
   * @param date the date to get the stocks in the portfolio on.
   * @return the stocks in the portfolio on the given date.
   */
  private List<String> getStocksOnDate(LocalDate date) {
    List<String> stocks = new ArrayList<>();
    for (ITransaction transaction : transactions) {
      if (!stocks.contains(transaction.getStock().getTicker()) &&
              (transaction.getDate().isBefore(date) || transaction.getDate().isEqual(date))) {
        stocks.add(transaction.getStock().getTicker());
      }
    }
    return stocks;
  }

  /**
   * Gets the quantity of the stock in the portfolio on the given date.
   * @param stock the stock to get the quantity of.
   * @param date the date to get the quantity of the stock on.
   * @return the quantity of the stock in the portfolio on the given date.
   */
  private double getStocksQuantity(String stock, LocalDate date) {
    double quantity = 0;
    for (ITransaction transaction : transactions) {
      if (transaction.getStock().getTicker().equals(stock)
              && (transaction.getDate().isBefore(date)
              || transaction.getDate().isEqual(date))) {
        quantity += transaction.realQuantity();
      }
    }
    return quantity;
  }

  /**
   * Gets the portfolio with holdings on the given date.
   * @param date the date to get the portfolio with holdings on.
   * @return the portfolio with holdings on the given date.
   */
  private IPortfolioWithHoldings getPortfolioWithHoldings(LocalDate date) {
    List<IHolding> holdings = new ArrayList<>();
    for (String stock : getStocksOnDate(date)) {
      holdings.add(new Holding(getStock(stock), getStocksQuantity(stock, date)));
    }
    return new Portfolio(name, holdings);
  }

  /**
   * Determines if the date is before the first transaction in the portfolio.
   * @param date the date to check.
   * @return true if the date is before the first transaction in the portfolio, false otherwise.
   */
  @Override
  public boolean isDateBeforeFirstTransaction(LocalDate date) {
    for (ITransaction transaction : transactions) {
      if (transaction.getDate().isAfter(date)) {
        return true;
      }
    }
    return false;
  }

  /**
   * Gets the latest transaction date in the portfolio for the user.
   * @return the latest transaction date in the portfolio.
   */
  @Override
  public LocalDate getLatestTransactionDate() {
    LocalDate latestTransactionDate = null;
    for (ITransaction transaction : transactions) {
      if (latestTransactionDate == null || transaction.getDate().isAfter(latestTransactionDate)) {
        latestTransactionDate = transaction.getDate();
      }
    }
    return latestTransactionDate;
  }

  /**
   * Determines if this portfolio is equal to the given object.
   * @param o the object to compare.
   * @return true if the portfolio is equal to the given object, false otherwise.
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof PortfolioWithTransactions)) {
      return false;
    }
    PortfolioWithTransactions that = (PortfolioWithTransactions) o;
    return name.equals(that.name) && transactions.equals(that.transactions);
  }

  /**
   * Gets the hashcode of the portfolio.
   * @return the hashcode of the portfolio.
   */
  @Override
  public int hashCode() {
    return Objects.hash(name, transactions);
  }

}
