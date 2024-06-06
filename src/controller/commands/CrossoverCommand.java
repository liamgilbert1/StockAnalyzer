package controller.commands;

import java.io.File;
import java.time.LocalDate;
import java.util.Objects;
import java.util.Scanner;

import controller.AlphaVantageStreamReader;
import controller.CSVReader;
import controller.CSVWriter;
import model.IModel;

/**
 * This class represents the CrossoverCommand class that implements the IController interface.
 * A crossover happens when the closing price for a day is greater than the x day moving average.
 * The crossover command determines which days are x-day crossovers for a given stock, a
 * specified date range, and a specified value of x (the number of days in the moving average).
 */
public class CrossoverCommand implements ICommand {
  private final Appendable out;

  public CrossoverCommand(Appendable out) {
    this.out = Objects.requireNonNull(out);
  }

  @Override
  public void execute(IModel model, Scanner scanner) {
    String ticker = "";
    String date = "";
    int days = 0;

    if (scanner.hasNext()) {
      ticker = scanner.next();
    }

    String fileName = ticker + ".csv";
    File file = new File(fileName);
    if (!file.exists()) {
      Readable stockAPIData = new AlphaVantageStreamReader(ticker).getReadable();
      new CSVWriter().write(ticker, stockAPIData);
    }

    if (scanner.hasNext()) {
      date = scanner.next();
    }

    if (scanner.hasNextInt()) {
      days = scanner.nextInt();
    }

    if (!date.isEmpty() && days > 0) {
      LocalDate dateEntered = LocalDate.parse(date);
      if (new CSVReader(ticker).getMostRecentDate().isBefore(dateEntered)) {
        Readable stockAPIData = new AlphaVantageStreamReader(ticker).getReadable();
        new CSVWriter().write(ticker, stockAPIData);
        System.out.print("Data has been updateD DUE TO INPUT BEING AFTER MOST RECENT DATE");
      }
      if (!new CSVReader(ticker).checkContainsDates(dateEntered, days)) {
        Readable stockAPIData = new AlphaVantageStreamReader(ticker).getReadable();
        new CSVWriter().write(ticker, stockAPIData);
      }

      boolean isCrossOver = model.crossOver(ticker, date, days);

      try {
        this.out.append(String.format("Is there a crossover: " + isCrossOver));
      } catch (Exception e) {
        throw new IllegalStateException("Could not write to file");
      }
    }
  }

  @Override
  public String getInstructions() {
    return null;
  }
}
