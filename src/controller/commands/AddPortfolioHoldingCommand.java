package controller.commands;

import java.util.Scanner;

import model.IModel;

/**
 * This class represents a command to add a holding to an existing portfolio.
 */
public class AddPortfolioHoldingCommand extends AWriterCommand {
  /**
   * Constructs an AddPortfolioHoldingCommand object.
   * @param out an appendable object to send output to
   */
  public AddPortfolioHoldingCommand(Appendable out) {
    super(out);
  }

  /**
   * Executes the AddPortfolioHoldingCommand with the given model and scanner.
   * @param model the model to execute the command on
   * @param scanner the scanner to get the next inputs from
   * @throws IllegalStateException if the command fails to execute
   */
  @Override
  public void execute(IModel model, Scanner scanner) {
    String portfolioName = getNextString(scanner);
    String ticker = getNextString(scanner);
    double quantity = getPositiveInt(scanner);

    tryWrite(ticker);

    try {
      model.addPortfolioHolding(portfolioName, ticker, quantity);
      this.out.append("Portfolio holdings updated\n");
    } catch (Exception e) {
      throw new IllegalStateException("Failed to process command.");
    }
  }

  /**
   * Gets the instructions for the AddPortfolioHoldingCommand.
   * @return the instructions for the AddPortfolioHoldingCommand
   */
  @Override
  public String getInstructions() {
    StringBuilder instructions;
    instructions = new StringBuilder();
    instructions.append("Add Portfolio Holding: \n");
    instructions.append("This command adds a holding to an existing portfolio.\n");
    instructions.append("Enter the following parameters separated by spaces:\n");
    instructions.append("1. Command name (AddPortfolioHolding)\n");
    instructions.append("2. Portfolio name\n");
    instructions.append("3. Stock ticker symbol\n");
    instructions.append("4. Quantity (number of shares)\n");
    return instructions.toString();
  }
}
