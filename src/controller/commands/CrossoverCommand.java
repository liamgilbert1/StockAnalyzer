package controller.commands;

import java.time.LocalDate;
import java.util.Objects;
import java.util.Scanner;

import model.IModel;

/**
 * This class represents the CrossoverCommand class that implements the IController interface.
 * A crossover happens when the closing price for a day is greater than the x day moving average.
 * The crossover command determines which days are x-day crossovers for a given stock, a
 * specified date range, and a specified value of x (the number of days in the moving average).
 */
public class CrossoverCommand extends AWriterCommand {
  private final Appendable out;

  public CrossoverCommand(Appendable out) {
    this.out = Objects.requireNonNull(out);
  }

  @Override
  public void execute(IModel model, Scanner scanner) {
    String ticker = getNextString(scanner);
    String dateEntered = getNextString(scanner);
    int days = getPositiveInt(scanner);

    tryWrite(ticker, dateEntered, days);

    boolean isCrossOver = model.crossOver(ticker, LocalDate.parse(dateEntered), days);

    try {
      this.out.append(String.format("Is there a crossover: " + isCrossOver));
    } catch (Exception e) {
      throw new IllegalStateException("Could not write to file");
    }
  }

  @Override
  public String getInstructions() {
    return null;
  }
}
