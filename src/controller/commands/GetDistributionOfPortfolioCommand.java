package controller.commands;

import java.util.Scanner;

import model.IModel;

public class GetDistributionOfPortfolioCommand extends AWriterCommand {

  public GetDistributionOfPortfolioCommand(Appendable out) {
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
