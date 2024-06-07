package controller.commands;

import java.util.Scanner;

import model.IModel;

public class AddPortfolioHoldingCommand extends AWriterCommand {
  public AddPortfolioHoldingCommand(Appendable out) {
    super(out);
  }

  @Override
  public void execute(IModel model, Scanner scanner) {
    String portfolioName = getNextString(scanner);
    String ticker = getNextString(scanner);
    double quantity = getPositiveInt(scanner);

    tryWrite(ticker);

    try {
      model.addPortfolioHolding(portfolioName, ticker, quantity);
      this.out.append("Portfolio holdings updated\n");
    } catch (Exception e) {
      throw new IllegalStateException("Failed to process command.");
    }
  }

  @Override
  public String getInstructions() {
    StringBuilder instructions;
    instructions = new StringBuilder();
    instructions.append("Add Portfolio Holding: \n");
    instructions.append("This command adds a holding to an existing portfolio.\n");
    instructions.append("Enter the following parameters separated by spaces:\n");
    instructions.append("1. Command name (AddPortfolioHolding)\n");
    instructions.append("2. Portfolio name\n");
    instructions.append("3. Stock ticker symbol\n");
    instructions.append("4. Quantity (number of shares)\n");
    return instructions.toString();
  }
}
