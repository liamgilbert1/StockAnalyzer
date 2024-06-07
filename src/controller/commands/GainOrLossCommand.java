package controller.commands;

import java.time.LocalDate;
import java.util.Scanner;

import model.IModel;

/**
 * This class represents a command that can be executed by the controller. This command determines,
 * over a given period of time, the gain or loss of a given stock.
 * (Closing prices only).
 */
public class GainOrLossCommand extends AWriterCommand {

  /**
   * Constructs a GainOrLossCommand object.
   * @param out an appendable object to send output to
   */
  public GainOrLossCommand(Appendable out) {
    super(out);
  }

  /**
   * Executes the GainOrLossCommand with the given model and scanner.
   * @param model the model to execute the command on
   * @param scanner the scanner to get the next inputs from
   * @throws IllegalStateException if the command fails to execute
   */
  @Override
  public void execute(IModel model, Scanner scanner) {
    String ticker = getNextString(scanner);
    String startDateEntered = getNextString(scanner);
    String endDateEntered = getNextString(scanner);

    tryWrite(ticker, startDateEntered, endDateEntered);

    try {
      double gainOrLoss = model.calculateGainOrLoss(ticker, LocalDate.parse(startDateEntered),
              LocalDate.parse(endDateEntered));
      this.out.append(String.format("Gain or Loss: %.2f\n", gainOrLoss));
    } catch (Exception e) {
      throw new IllegalStateException("Failed to process command.");
    }
  }

  /**
   * Gets the instructions for the GainOrLossCommand.
   * @return the instructions for the GainOrLossCommand
   */
  @Override
  public String getInstructions() {
    StringBuilder instructions;
    instructions = new StringBuilder();
    instructions.append("Gain or Loss: \n");
    instructions.append("This command calculates the gain or loss of a stock over a given period "
            + "of time.\n");
    instructions.append("Enter the following parameters separated by spaces:\n");
    instructions.append("1. Command name (GainOrLoss)\n");
    instructions.append("2. Stock ticker symbol\n");
    instructions.append("3. Starting date in the format yyyy-mm-dd\n");
    instructions.append("4. Ending date in the format yyyy-mm-dd\n");
    return instructions.toString();
  }
}
