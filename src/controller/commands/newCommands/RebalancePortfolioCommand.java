package controller.commands.newCommands;

import java.util.Scanner;

import controller.commands.AWriterCommand;
import model.IModel;
import model.IModel2;

public class RebalancePortfolioCommand extends AWriterCommand {

  public RebalancePortfolioCommand(Appendable out) {
    super(out);
  }

  @Override
  public void execute(IModel2 model, Scanner scanner) {

  }

  @Override
  public String getInstructions() {
    return null;
  }
}
