package controller.commands;

import java.util.Scanner;

import model.IModel;

public class SellPortfolioHoldingCommand extends AWriterCommand {
  public SellPortfolioHoldingCommand(Appendable out) {
    super(out);
  }

  @Override
  public void execute(IModel model, Scanner scanner) {

  }

  @Override
  public String getInstructions() {
    return "";
  }
}
