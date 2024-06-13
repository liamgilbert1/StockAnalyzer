package controller.commands.newCommands;

import java.util.Scanner;

import controller.commands.ACommand;
import controller.commands.AWriterCommand;
import controller.commands.ICommand;
import model.IModel2;

public class LoadPortfolioCommand extends AWriterCommand implements ICommand {

  public LoadPortfolioCommand(Appendable out) {
    super(out);
  }

  @Override
  public void execute(IModel2 model, Scanner scanner) {
    String portfolioName = getNextString(scanner);

    try {
      model.loadPortfolio(portfolioName);
      out.append("Portfolio loaded successfully.");
    } catch (Exception e) {
      throw new IllegalArgumentException("Failed to load portfolio.");
    }

    for (String ticker : model.getStocksInPortfolio(portfolioName)) {
      writeStockData(ticker);
    }
  }

  @Override
  public String getInstructions() {
    StringBuilder instructions;
    instructions = new StringBuilder();
    instructions.append("Load Portfolio Command: \n");
    instructions.append("This command loads an existing portfolio.\n");
    instructions.append("Enter the following parameters separated by spaces:\n");
    instructions.append("1. Command name (LoadPortfolio)\n");
    instructions.append("2. Portfolio name\n");
    return instructions.toString();
  }
}
