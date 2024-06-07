package controller.commands;

import java.time.LocalDate;
import java.util.List;
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

  /**
   * Constructs a CrossoverCommand object.
   * @param out the appendable object to output the results of the command
   */
  public CrossoverCommand(Appendable out) {
    super(out);
  }

  /**
   * Executes the CrossoverCommand with the given model and scanner.
   * @param model the model to execute the command on
   * @param scanner the scanner to get the next inputs from
   * @throws IllegalArgumentException if the command fails to execute
   */
  @Override
  public void execute(IModel model, Scanner scanner) {
    String ticker = getNextString(scanner);
    String startDateEntered = getNextString(scanner);
    String endDateEntered = getNextString(scanner);
    int xDays = getPositiveInt(scanner);

    tryWrite(ticker, startDateEntered, endDateEntered);

    try {
      List<LocalDate> isCrossOver = model.crossOver(ticker, LocalDate.parse(startDateEntered),
              LocalDate.parse(endDateEntered), xDays);
      int numCrossOvers = isCrossOver.size();
      this.out.append(String.format("Number of crossovers: " + numCrossOvers + "\n"));
      for (LocalDate date : isCrossOver) {
        this.out.append(String.format(date.toString() + "\n"));
      }
    } catch (Exception e) {
      throw new IllegalStateException("Failed to process command.");
    }
  }

  /**
   * Gets the instructions for the CrossoverCommand.
   * @return the instructions for the CrossoverCommand
   */
  @Override
  public String getInstructions() {
    StringBuilder instructions;
    instructions = new StringBuilder();
    instructions.append("Crossovers: \n");
    instructions.append("This command determines which days are x-day crossovers for a given "
            + "stock, over a specified time period \n");
    instructions.append("Enter the following parameters separated by spaces:\n");
    instructions.append("1. Command name (Crossover)\n");
    instructions.append("2. Stock ticker symbol\n");
    instructions.append("3. Starting date in the format yyyy-mm-dd\n");
    instructions.append("4. Ending date in the format yyyy-mm-dd\n");
    instructions.append("5. The number of days to check the crossover over (x)\n");
    return instructions.toString();
  }
}
