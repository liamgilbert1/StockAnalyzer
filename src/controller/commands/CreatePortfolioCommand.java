package controller.commands;
import java.time.LocalDate;
import java.util.Scanner;
import model.IModel;

/**
 * This class represents a command that can be executed by the controller. This command creates a
 * new portfolio for the user from the given stocks and number of shares
 */
public class CreatePortfolioCommand extends ACommand {

  public CreatePortfolioCommand(Appendable out) {
    super(out);
  }

  @Override
  public void execute(IModel model, Scanner scanner) {
    String portfolioName = getNextString(scanner);

    try {
      model.createPortfolio(portfolioName);
      this.out.append("Portfolio Created\n");
    } catch (Exception e) {
      throw new IllegalArgumentException("Failed to process command.");
    }
  }

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
