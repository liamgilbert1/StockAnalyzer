package model.portfolio;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import model.stock.IStock;
import model.stock.Stock;

public class PortfolioWithTransactions implements IPortfolioWithTransactions {
  private final String name;
  private final List<ITransaction> transactions;

  public PortfolioWithTransactions(String name) {
    this.name = name;
    this.transactions = new ArrayList<>();
  }

  public PortfolioWithTransactions(String name, List<ITransaction> transactions) {
    this.name = name;
    this.transactions = transactions;
  }


  @Override
  public IPortfolioWithTransactions addTransaction(ITransaction transaction) {
    if (isTransactionBefore(transaction)) {
      throw new IllegalArgumentException("Transaction date is before previous transaction date.");
    }
    List<ITransaction> newTransactions = new ArrayList<>(transactions);
    newTransactions.add(transaction);
    return new PortfolioWithTransactions(name, newTransactions);
  }

  @Override
  public boolean isTransactionBefore(ITransaction transaction) {
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
                .append(String.format("%.2f", getStockQuantity(transaction))).append("\n");
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

  @Override
  public String getValueDistribution(LocalDate date) {
    StringBuilder distribution = new StringBuilder();
    for (IHolding holding : getPortfolioWithHoldings(date).getHoldings()) {
      distribution.append(holding.getStock().getTicker()).append(": ")
              .append(String.format("%.2f", holding.getValue(date))).append("\n");
    }
    return distribution.toString();
  }

  @Override
  public double getValue(LocalDate date) {
    return this.getPortfolioWithHoldings(date).getValue(date);
  }

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
    performance.append("\n").append("Scale: * = ").append(getScale(Collections.max(values)));
    return performance.toString();
  }

  @Override
  public List<LocalDate> getPerformanceDates(LocalDate startDate, LocalDate endDate) {
    List<LocalDate> dates = new ArrayList<>();
    long daysBetween = ChronoUnit.DAYS.between(startDate, endDate);
    if (daysBetween < 0) {
      throw new IllegalArgumentException("End date must be after start date.");
    } else if (daysBetween <= 30) {
      return this.addDaysOccurrence(startDate, endDate, dates, 1);
    } else if (daysBetween <= 90) {
      return this.addDaysOccurrence(startDate, endDate, dates, 3);
    } else if (daysBetween <= 180) {
      return this.addDaysOccurrence(startDate, endDate, dates, 7);
    } else if (daysBetween <= 730) {
      return this.addMonthsOccurrence(startDate, endDate, dates, 1);
    } else if (daysBetween <= 1830) {
      return this.addMonthsOccurrence(startDate, endDate, dates, 3);
    }
    return this.addYearOccurrence(startDate, endDate, dates);
  }

  private List<LocalDate> addDaysOccurrence(LocalDate startDate, LocalDate endDate, List<LocalDate> dates,
                                            int daysToAdd) {
    for (LocalDate date = startDate; date.isBefore(endDate); date = date.plusDays(daysToAdd)) {
      dates.add(date);
    }
    return dates;
  }

  private List<LocalDate> addMonthsOccurrence(LocalDate startDate, LocalDate endDate,
                                              List<LocalDate> dates, int monthsToAdd) {
    for (LocalDate date = startDate; date.isBefore(endDate); date = date.plusMonths(monthsToAdd)) {
      dates.add(date.with(TemporalAdjusters.lastDayOfMonth()));
    }
    return dates;
  }

  private List<LocalDate> addYearOccurrence(LocalDate startDate, LocalDate endDate,
                                            List<LocalDate> dates) {
    for (LocalDate date = startDate; date.isBefore(endDate); date = date.plusYears(1)) {
      dates.add(date.with(TemporalAdjusters.lastDayOfYear()));
    }
    return dates;
  }

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

  private List<Double> getOverTimeValues(List<LocalDate> dates) {
    List<Double> values = new ArrayList<>();
    for (LocalDate date : dates) {
      values.add(this.getPortfolioWithHoldings(date).getValue(date));
    }
    return values;
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

  private IStock getStock(String ticker) {
    return new Stock(ticker);
  }

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

  private IPortfolioWithHoldings getPortfolioWithHoldings(LocalDate date) {
    List<IHolding> holdings = new ArrayList<>();
    for (String stock : getStocksOnDate(date)) {
      holdings.add(new Holding(getStock(stock), getStocksQuantity(stock, date)));
    }
    return new Portfolio(name, holdings);
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
