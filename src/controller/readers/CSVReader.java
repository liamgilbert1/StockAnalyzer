package controller.readers;

import java.io.FileReader;
import java.io.StringReader;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

import model.stock.StockDataPoint;

/**
 * Represents a reader that can read stock data from a CSV file. The CSV file should be formatted
 * such that the first column contains the date, the second column contains the open price, the
 * third column contains the high price, the fourth column contains the low price, the fifth column
 * contains the close price, and the sixth column contains the volume.
 */
public class CSVReader implements IStockReader {
  private final String ticker;

  /**
   * Constructs an object of the CSVReader with the given ticker symbol.
   *
   * @param ticker the ticker symbol of the stock.
   */
  public CSVReader(String ticker) {
    this.ticker = Objects.requireNonNull(ticker);
  }

  @Override
  public Readable getReadable() {
    String file = ticker + ".csv";
    Appendable stockData = new StringBuilder();
    try (Scanner scanner = new Scanner(new FileReader(file))) {
      while (scanner.hasNext()) {
        stockData.append(scanner.next());
        stockData.append("\n");
      }
    } catch (Exception e) {
      throw new IllegalStateException("Could not read from file");
    }
    return new StringReader(stockData.toString());
  }

  @Override
  public boolean checkContainsDates(LocalDate date, int days) {
    date = findLastOpenDate(date);
    Readable stockData = getReadable();
    try (Scanner scanner = new Scanner(stockData)) {
      scanner.nextLine();
      while (scanner.hasNextLine()) {
        String line = scanner.nextLine();
        String[] data = line.split(",");
        LocalDate dateInFile = LocalDate.parse(data[0]);
        if (dateInFile.equals(date)) {
          for (int i = 0; i < days - 1; i++) {
            if (!scanner.hasNextLine()) {
              return false;
            }
            scanner.nextLine();
          }
          return true;
        }
      }
      return false;
    }
  }

  @Override
  public boolean checkContainsDateRange(LocalDate startDate, LocalDate endDate) {
    startDate = findLastOpenDate(startDate);
    endDate = findLastOpenDate(endDate);
    boolean containsStartDate = false;
    boolean containsEndDate = false;
    Readable stockData = getReadable();
    try (Scanner scanner = new Scanner(stockData)) {
      scanner.nextLine();
      while (scanner.hasNextLine()) {
        String line = scanner.nextLine();
        String[] data = line.split(",");
        LocalDate dateInFile = LocalDate.parse(data[0]);
        if (dateInFile.equals(startDate)) {
          containsStartDate = true;
        }
        if (dateInFile.equals(endDate)) {
          containsEndDate = true;
        }
        if (containsStartDate && containsEndDate) {
          return true;
        }
        if (dateInFile.isBefore(endDate) && !containsEndDate) {
          return false;
        }
      }
      return false;
    }
  }

  /**
   * Finds the last open date before the given date. If the given date is an open date (a date
   * where the stock market was open), it will return the given date.
   *
   * @param date the date to find the last open date before.
   * @return the last open date before the given date.
   */
  private LocalDate findLastOpenDate(LocalDate date) {
    Readable stockData = getReadable();
    try (Scanner scanner = new Scanner(stockData)) {
      scanner.nextLine();
      while (scanner.hasNextLine()) {
        String line = scanner.nextLine();
        String[] data = line.split(",");
        LocalDate dateInFile = LocalDate.parse(data[0]);
        if (dateInFile.equals(date) || dateInFile.isBefore(date)) {
          return dateInFile;
        }
      }
    }
    throw new IllegalArgumentException("Most recent date not found");
  }

  /**
   * Finds the next open date after the given date. If the given date is an open date (a date
   * where the stock market was open), it will return the given date.
   *
   * @param date the date to find the next open date after.
   * @return the next open date after the given date.
   */
  private LocalDate findNextOpenDate(LocalDate date) {
    Readable stockData = getReadable();
    try (Scanner scanner = new Scanner(stockData)) {
      scanner.nextLine();
      int closestDaysAway = Integer.MAX_VALUE;
      LocalDate closestDate = null;
      while (scanner.hasNextLine()) {
        String line = scanner.nextLine();
        String[] data = line.split(",");
        LocalDate dateInFile = LocalDate.parse(data[0]);
        if (dateInFile.isAfter(date)) {
          int daysAway = Math.toIntExact(date.datesUntil(dateInFile).count());
          if (daysAway < closestDaysAway) {
            closestDaysAway = daysAway;
            closestDate = dateInFile;
          }
        }
        if (dateInFile.equals(date)) {
          return dateInFile;
        }
        if (dateInFile.isBefore(date)) {
          if (closestDate == null) {
            throw new IllegalArgumentException("Next open date not found");
          }
          return closestDate;
        }
      }
    }
    throw new IllegalArgumentException("Most recent date not found");
  }

  @Override
  public LocalDate getMostRecentDate() {
    Readable stockData = getReadable();
    LocalDate dateInFile;
    try (Scanner scanner = new Scanner(stockData)) {
      scanner.nextLine();
      String line = scanner.nextLine();
      String[] data = line.split(",");
      dateInFile = LocalDate.parse(data[0]);
    } catch (Exception e) {
      throw new IllegalStateException("Most recent date not found");
    }
    return dateInFile;
  }

  @Override
  public double getStockData(LocalDate date, StockDataPoint dataPoint) {
    Readable stockData = getReadable();
    date = findLastOpenDate(date);
    try (Scanner scanner = new Scanner(stockData)) {
      scanner.nextLine();
      while (scanner.hasNextLine()) {
        String line = scanner.nextLine();
        String[] data = line.split(",");
        LocalDate dateInFile = LocalDate.parse(data[0]);
        if (dateInFile.equals(date)) {
          return Double.parseDouble(data[dataPoint.getIndex()]);
        }
      }
    }
    throw new IllegalArgumentException("Date not found");
  }

  @Override
  public List<String> getDataAcrossDays(LocalDate date, int days, StockDataPoint dataPoint) {
    Readable stockData = getReadable();
    List<String> prices = new ArrayList<>();
    date = findLastOpenDate(date);
    try (Scanner scanner = new Scanner(stockData)) {
      scanner.nextLine();
      while (scanner.hasNextLine()) {
        String line = scanner.nextLine();
        String[] data = line.split(",");
        LocalDate dateInFile = LocalDate.parse(data[0]);
        if (dateInFile.equals(date)) {
          for (int i = 0; i < days; i++) {
            prices.add(data[dataPoint.getIndex()]);
            if (!scanner.hasNextLine()) {
              break;
            }
            data = scanner.nextLine().split(",");
          }
          return prices;
        }
      }
    }
    throw new IllegalArgumentException("Date not found");
  }

  @Override
  public List<String> getDataAcrossDays(LocalDate startDate, LocalDate endDate,
                                        StockDataPoint dataPoint) {
    Readable stockData = getReadable();
    List<String> prices = new ArrayList<>();
    startDate = findNextOpenDate(startDate);
    endDate = findLastOpenDate(endDate);
    try (Scanner scanner = new Scanner(stockData)) {
      scanner.nextLine();
      while (scanner.hasNextLine()) {
        String line = scanner.nextLine();
        String[] data = line.split(",");
        LocalDate dateInFile = LocalDate.parse(data[0]);
        if (dateInFile.equals(endDate)) {
          while (dateInFile.isAfter(startDate) || dateInFile.equals(startDate)) {
            prices.add(data[dataPoint.getIndex()]);
            if (!scanner.hasNextLine()) {
              break;
            }
            data = scanner.nextLine().split(",");
            dateInFile = LocalDate.parse(data[0]);
          }
          return prices;
        }
      }
    }
    throw new IllegalArgumentException("Date not found");
  }
}
