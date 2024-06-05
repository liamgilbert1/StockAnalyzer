package controller.commands;

import java.util.Objects;
import java.util.Scanner;

import model.IModel;

/**
 * This class represents a command that can be executed by the controller. This command determines,
 * over a given period of time, the gain or loss of a given stock.
 * (Closing prices only).
 */
public class GainOrLossCommand implements ICommand {
  private final Appendable out;

  public GainOrLossCommand(Appendable out) {
    this.out = Objects.requireNonNull(out);
  }

  @Override
  public void execute(IModel model, Scanner scanner) {
    String startDate = "";
    String endDate = "";
    if (scanner.hasNext()) {
      startDate = scanner.next();
    }

    if (scanner.hasNext()) {
      endDate = scanner.next();
    }

    if (!startDate.isEmpty() || !endDate.isEmpty()) {
      double gainOrLoss = model.calculateGainOrLoss(startDate, endDate);

      try {
        this.out.append(String.format("Gain or Loss: %.2f\n", gainOrLoss));
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
