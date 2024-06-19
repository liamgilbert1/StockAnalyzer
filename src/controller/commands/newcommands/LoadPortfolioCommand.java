package controller.commands.newcommands;

import java.util.Scanner;

import controller.commands.AWriterCommand;
import controller.commands.ICommand;
import model.IModel2;

/**
 * This class represents a command to load an existing portfolio.
 * The user must provide the portfolio name.
 * The command will then load the portfolio and display the stocks in the portfolio.
 */
public class LoadPortfolioCommand extends AWriterCommand implements ICommand {

  /**
   * Constructs a LoadPortfolioCommand object.
   * @param out the appendable object to output messages to
   */
  public LoadPortfolioCommand(Appendable out) {
    super(out);
  }

  /**
   * Executes the LoadPortfolioCommand. This command loads an existing portfolio.
   * @param model the model to perform the command on
   * @param scanner the scanner object to read user input from
   */
  @Override
  public void execute(IModel2 model, Scanner scanner) {
    String portfolioName = getNextString(scanner);

    try {
      model.loadPortfolio(portfolioName);
      out.append("Portfolio loaded successfully.\n");
    } catch (Exception e) {
      throw new IllegalArgumentException("Failed to load portfolio.");
    }
  }

  /**
   * Gets the command instructions.
   * @return the instructions for the user
   */
  @Override
  public String getInstructions() {
    StringBuilder instructions;
    instructions = new StringBuilder();
    instructions.append("Load Portfolio Command: \n");
    instructions.append("This command loads an existing portfolio.\n");
    instructions.append("Enter the following parameters separated by spaces:\n");
    instructions.append("1. Command name (LoadPortfolio)\n");
    instructions.append("2. Portfolio name\n");
    return instructions.toString();
  }
}
