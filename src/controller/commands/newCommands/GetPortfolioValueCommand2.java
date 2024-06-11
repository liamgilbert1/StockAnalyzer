package controller.commands.newCommands;

import java.util.Scanner;

import controller.commands.AWriterCommand;
import model.IModel;
import model.IModel2;

public class GetPortfolioValueCommand2 extends AWriterCommand {

  public GetPortfolioValueCommand2(Appendable out) {
    super(out);
  }

  @Override
  public void execute(IModel2 model, Scanner scanner) {

  }

  @Override
  public String getInstructions() {
    return "";
  }
}
