package controller.commands;

import java.util.Scanner;

import model.IModel;

public class GetPortfolioCompositionCommand extends AWriterCommand {

  public GetPortfolioCompositionCommand(Appendable out) {
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
