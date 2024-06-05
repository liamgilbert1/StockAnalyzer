package controller.commands;

import java.util.Scanner;

import model.IModel;

/**
 * This class represents a command that can be executed by the controller. This command determines,
 * over a given period of time, the moving average of a given stock.
 * The moving average is the average of the closing prices of a stock over a specified number of
 * days, going back from the given date.
 * (Closing prices only).
 */
public class MovingAverageCommand implements ICommand {
  @Override
  public void execute(IModel model, Scanner scanner) {

  }

  @Override
  public String getInstructions() {
    return null;
  }
}
