package controller.commands;

import java.io.File;
import java.time.LocalDate;
import java.util.List;

import controller.writers.IStockDataWriter;
import controller.readers.AlphaVantageStreamReader;
import controller.readers.CSVReader;
import controller.writers.CSVWriter;
import model.stock.IStock;

/**
 * This class represents a command that writes stock data to a CSV file.
 */
public abstract class AWriterCommand extends ACommand implements ICommand {
  public AWriterCommand(Appendable out) {
    super(out);
  }

  /**
   * Gets the writer for this command.
   * @return the writer for this command
   */
  protected IStockDataWriter getWriter() {
    return new CSVWriter();
  }

  /**
   * Writes stock data to a CSV file.
   * @param ticker the ticker of the stock
   */
  protected void writeStockData(String ticker) {
    Readable stockAPIData = new AlphaVantageStreamReader(ticker).getReadable();
    getWriter().write(ticker, stockAPIData);
  }

  /**
   * Tries to write stock data to a CSV file.
   * @param ticker the ticker of the stock
   * @param dateEntered the date to check
   * @param days the number of days to check
   */
  protected void tryWrite(String ticker, String dateEntered, int days) {
    LocalDate date = LocalDate.parse(dateEntered);
    String fileName = ticker + ".csv";
    File file = new File(fileName);
    if (new CSVReader(ticker).getMostRecentDate().isBefore(date)
            || !new CSVReader(ticker).checkContainsDates(date, days)
            || !file.exists()) {
      writeStockData(ticker);
    }
  }

  /**
   * Tries to write stock data to a CSV file.
   * @param ticker the ticker of the stock
   * @param startDateEntered the start date to check
   * @param endDateEntered the end date to check
   */
  protected void tryWrite(String ticker, String startDateEntered, String endDateEntered) {
    LocalDate startDate = LocalDate.parse(startDateEntered);
    LocalDate endDate = LocalDate.parse(endDateEntered);
    String fileName = ticker + ".csv";
    File file = new File(fileName);
    if (new CSVReader(ticker).getMostRecentDate().isBefore(endDate)
            || !new CSVReader(ticker).checkContainsDateRange(startDate, endDate)
            || !file.exists()) {
      writeStockData(ticker);
    }
  }

  /**
   * Tries to write stock data to a CSV file.
   * @param ticker the ticker of the stock
   */
  protected void tryWrite(String ticker) {
    String fileName = ticker + ".csv";
    File file = new File(fileName);
    if (!file.exists()) {
      writeStockData(ticker);
    }
  }

  /**
   * Tries to write stock data to a CSV file.
   * @param stocks the list of stocks
   * @param date the date to check
   */
  protected void tryWrite(List<IStock> stocks, LocalDate date) {
    for (IStock stock : stocks) {
      if (!stock.checkContainsDates(date, 1)) {
        writeStockData(stock.getTicker());
      }
    }
  }
}
