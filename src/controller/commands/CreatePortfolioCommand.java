package controller.commands;

import java.util.Scanner;
import model.IModel;
import model.IModel2;

/**
 * This class represents a command that can be executed by the controller. This command creates a
 * new portfolio for the user from the given stocks and number of shares
 */
public class CreatePortfolioCommand extends ACommand {

  /**
   * Constructs a CreatePortfolioCommand object.
   * @param out an appendable object to send output to
   */
  public CreatePortfolioCommand(Appendable out) {
    super(out);
  }

  /**
   * Executes the CreatePortfolioCommand with the given model and scanner.
   * @param model the model to execute the command on
   * @param scanner the scanner to get the next inputs from
   * @throws IllegalArgumentException if the command fails to execute
   */
  @Override
  public void execute(IModel2 model, Scanner scanner) {
    String portfolioName = getNextString(scanner);

    try {
      model.createPortfolio(portfolioName);
      this.out.append("Portfolio Created: " + portfolioName + "\n");
    } catch (Exception e) {
      throw new IllegalArgumentException("Failed to process command.");
    }
  }

  /**
   * Gets the instructions for the CreatePortfolioCommand.
   * @return the instructions for the CreatePortfolioCommand
   */
  @Override
  public String getInstructions() {
    StringBuilder instructions;
    instructions = new StringBuilder();
    instructions.append("Create Portfolio: \n");
    instructions.append("This command creates a new portfolio for the user.\n");
    instructions.append("Enter the following parameters separated by spaces:\n");
    instructions.append("1. Command name (CreatePortfolio)\n");
    instructions.append("2. Portfolio name\n");
    return instructions.toString();
  }
}
