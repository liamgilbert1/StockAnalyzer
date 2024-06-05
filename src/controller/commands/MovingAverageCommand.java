package controller.commands;

import java.util.Objects;
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
  private final Appendable out;

  public MovingAverageCommand(Appendable out) {
    this.out = Objects.requireNonNull(out);
  }

  @Override
  public void execute(IModel model, Scanner scanner) {
    String ticker = "";
    String numDays = "";
    if (scanner.hasNext()) {
      ticker = scanner.next();
    }

    if (scanner.hasNextInt()) {
      numDays = scanner.next();
    }

    if (ticker != "" && numDays != "") {
      int days = Integer.parseInt(numDays);
      double movingAverage = model.movingAverage(ticker, days);

      try {
        this.out.append(String.format("Moving average is: " + movingAverage));
      } catch (Exception e) {
        e.printStackTrace();
      }
    }

  }

  @Override
  public String getInstructions() {
    return null;
  }
}
