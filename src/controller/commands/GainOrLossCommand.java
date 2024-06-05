package controller.commands;

import java.util.Scanner;

import model.IModel;

/**
 * This class represents a command that can be executed by the controller. This command determines,
 * over a given period of time, the gain or loss of a given stock.
 * (Closing prices only).
 */
public class GainOrLossCommand implements ICommand {
  @Override
  public void execute(IModel model, Scanner scanner) {

  }

  @Override
  public String getInstructions() {
    return null;
  }
}
