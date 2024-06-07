package controller.commands;

import java.time.LocalDate;
import java.util.Objects;
import java.util.Scanner;

import model.IModel;

/**
 * This class represents a command that can be executed by the controller. This command determines,
 * over a given period of time, the gain or loss of a given stock.
 * (Closing prices only).
 */
public class GainOrLossCommand extends AWriterCommand {
  private final Appendable out;

  public GainOrLossCommand(Appendable out) {
    this.out = Objects.requireNonNull(out);
  }

  @Override
  public void execute(IModel model, Scanner scanner) {
    String ticker = getNextString(scanner);
    String startDateEntered = getNextString(scanner);
    String endDateEntered = getNextString(scanner);


    tryWrite(ticker, startDateEntered, endDateEntered);

    double gainOrLoss = model.calculateGainOrLoss(ticker, LocalDate.parse(startDateEntered),
            LocalDate.parse(endDateEntered));

    try {
      this.out.append(String.format("Gain or Loss: %.2f\n", gainOrLoss));
    } catch (Exception e) {
      throw new IllegalStateException("Failed to process command.");
    }
  }

  @Override
  public String getInstructions() {
    return null;
  }
}
