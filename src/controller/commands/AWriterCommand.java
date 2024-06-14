package controller.commands;

import java.io.File;
import java.time.LocalDate;
import java.util.List;

import controller.IO.IOUtils;
import controller.IO.readers.IStockReader;
import controller.IO.writers.IStockDataWriter;
import controller.IO.readers.AlphaVantageStreamReader;
import controller.IO.readers.CSVReader;
import controller.IO.writers.CSVWriter;
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
   *
   * @return the writer for this command
   */
  protected IStockDataWriter getWriter() {
    return new CSVWriter();
  }

  protected IStockReader getReader(String ticker) {
    return new CSVReader(ticker);
  }

  /**
   * Writes stock data to a CSV file.
   *
   * @param ticker the ticker of the stock
   */
  protected void writeStockData(String ticker) {
    try {
      Readable stockAPIData = new AlphaVantageStreamReader(ticker).getReadable();
      getWriter().write(ticker, stockAPIData);
    } catch (Exception e) {
      throw new IllegalStateException("Failed to write stock data.");
    }
  }

  /**
   * Tries to write stock data to a CSV file.
   *
   * @param ticker      the ticker of the stock
   * @param dateEntered the date to check
   * @param days        the number of days to check
   */
  protected void tryWrite(String ticker, String dateEntered, int days) {
    LocalDate date = LocalDate.parse(dateEntered);
    File file = IOUtils.getFile(ticker, ".csv", "stockData");
    if (!file.exists()
            || getReader(ticker).getMostRecentDate().isBefore(date)
            || !getReader(ticker).checkContainsDates(date, days)) {
      writeStockData(ticker);
    }
  }

  /**
   * Tries to write stock data to a CSV file.
   *
   * @param ticker           the ticker of the stock
   * @param startDateEntered the start date to check
   * @param endDateEntered   the end date to check
   */
  protected void tryWrite(String ticker, String startDateEntered, String endDateEntered) {
    LocalDate startDate = LocalDate.parse(startDateEntered);
    LocalDate endDate = LocalDate.parse(endDateEntered);
    File file = IOUtils.getFile(ticker, ".csv", "stockData");
    if (!file.exists()
            || getReader(ticker).getMostRecentDate().isBefore(endDate)
            || !getReader(ticker).checkContainsDateRange(startDate, endDate)) {
      writeStockData(ticker);
    }
  }

  /**
   * Tries to write stock data to a CSV file.
   *
   * @param ticker the ticker of the stock
   */
  protected void tryWrite(String ticker) {
    File file = IOUtils.getFile(ticker, ".csv", "stockData");
    if (!file.exists()) {
      writeStockData(ticker);
    }
  }

  /**
   * Tries to write stock data to a CSV file.
   *
   * @param stocks the list of stocks
   * @param date   the date to check
   */
  protected void tryWrite(List<IStock> stocks, LocalDate date) {
    for (IStock stock : stocks) {
      if (!stock.checkContainsDates(date, 1)) {
        writeStockData(stock.getTicker());
      }
    }
  }
}
