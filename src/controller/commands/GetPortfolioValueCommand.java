package controller.commands;

import java.util.Scanner;

import model.IModel;

/**
 * This class represents a command that can be executed by the controller. This command determines
 * the value of a given portfolio.
 */
public class GetPortfolioValueCommand implements ICommand {
  @Override
  public void execute(IModel model, Scanner scanner) {

  }

  @Override
  public String getInstructions() {
    return null;
  }
}
