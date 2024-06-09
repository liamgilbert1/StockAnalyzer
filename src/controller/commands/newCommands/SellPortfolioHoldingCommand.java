package controller.commands.newCommands;

import java.util.Scanner;

import controller.commands.ACommand;
import model.IModel;

public class SellPortfolioHoldingCommand extends ACommand {

  public SellPortfolioHoldingCommand(Appendable out) {
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
