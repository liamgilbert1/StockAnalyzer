package controller.commands;

import java.io.File;
import java.time.LocalDate;
import java.util.List;

import controller.readers.AlphaVantageStreamReader;
import controller.readers.CSVReader;
import controller.CSVWriter;
import model.stock.IStock;

public abstract class AWriterCommand extends ACommand implements ICommand {
  public AWriterCommand(Appendable out) {
    super(out);
  }

  protected void writeStockData(String ticker) {
    Readable stockAPIData = new AlphaVantageStreamReader(ticker).getReadable();
    new CSVWriter().write(ticker, stockAPIData);
  }

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

  protected void tryWrite(String ticker) {
    String fileName = ticker + ".csv";
    File file = new File(fileName);
    if (!file.exists()) {
      writeStockData(ticker);
    }
  }

  protected void tryWrite(List<IStock> stocks, LocalDate date) {
    for (IStock stock : stocks) {
      if (!stock.checkContainsDates(date, 1)) {
        writeStockData(stock.getTicker());
      }
    }
  }
}
