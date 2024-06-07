package controller.commands;
import java.util.Scanner;
import model.IModel;

/**
 * This class represents a command that can be executed by the controller. This command creates a
 * new portfolio for the user from the given stocks and number of shares
 */
public class CreatePortfolioCommand implements ICommand {
  @Override
  public void execute(IModel model, Scanner scanner) {

  }

  @Override
  public String getInstructions() {
    return null;
  }
}
