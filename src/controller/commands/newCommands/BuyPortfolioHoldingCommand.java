package controller.commands.newCommands;

import java.util.Scanner;

import controller.commands.AWriterCommand;
import model.IModel;

public class BuyPortfolioHoldingCommand extends AWriterCommand {

  public BuyPortfolioHoldingCommand(Appendable out) {
    super(out);
  }

  @Override
  public void execute(IModel model, Scanner scanner) {
    //TODO
  }

  @Override
  public String getInstructions() {
    //TODO
    return "";
  }
}
