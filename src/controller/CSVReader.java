package controller;

import java.io.FileReader;
import java.io.StringReader;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class CSVReader implements ICSVReader {

  private final String ticker;

  public CSVReader(String ticker) {
    this.ticker = Objects.requireNonNull(ticker);
  }

  @Override
  public Readable getReadable() {
    String file = ticker + ".csv";
    Appendable stockData = new StringBuilder();
    try (Scanner scanner = new Scanner(new FileReader(file))){
      while(scanner.hasNext()) {
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

  public double getPrice(LocalDate date) {
    Readable stockData = getReadable();
    try (Scanner scanner = new Scanner(stockData)) {
      scanner.nextLine();
      while (scanner.hasNextLine()) {
        String line = scanner.nextLine();
        String[] data = line.split(",");
        LocalDate dateInFile = LocalDate.parse(data[0]);
        if (dateInFile.equals(date)) {
          return Double.parseDouble(data[4]);
        }
      }
    }
    throw new IllegalArgumentException("Date not found");
  }

  public List<Double> getPricesAcrossDays(LocalDate date, int days) {
    Readable stockData = getReadable();
    List<Double> prices = new ArrayList<>();
    try (Scanner scanner = new Scanner(stockData)) {
      scanner.nextLine();
      while (scanner.hasNextLine()) {
        String line = scanner.nextLine();
        String[] data = line.split(",");
        LocalDate dateInFile = LocalDate.parse(data[0]);
        if (dateInFile.equals(date)) {
          for (int i = 0; i < days; i++) {
            prices.add(Double.parseDouble(data[4]));
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

}
