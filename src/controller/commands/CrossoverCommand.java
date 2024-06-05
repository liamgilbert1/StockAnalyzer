package controller.commands;

import java.util.Scanner;

import model.IModel;

/**
 * This class represents the CrossoverCommand class that implements the IController interface.
 * A crossover happens when the closing price for a day is greater than the x day moving average.
 * The crossover command determines which days are x-day crossovers for a given stock, a
 * specified date range, and a specified value of x (the number of days in the moving average).
 */
public class CrossoverCommand implements ICommand {
  @Override
  public void execute(IModel model, Scanner scanner) {

  }

  @Override
  public String getInstructions() {
    return null;
  }
}
