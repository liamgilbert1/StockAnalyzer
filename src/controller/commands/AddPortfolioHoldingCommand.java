package controller.commands;

import java.util.Scanner;

import model.IModel;

public class AddPortfolioHoldingCommand extends AWriterCommand {
  private final Appendable out;

  public AddPortfolioHoldingCommand(Appendable out) {
    this.out = out;
  }

  @Override
  public void execute(IModel model, Scanner scanner) {
    String portfolioName = getNextString(scanner);
    String ticker = getNextString(scanner);
    int quantity = getPositiveInt(scanner);

    tryWrite(ticker);

    model.addPortfolioHolding(portfolioName, ticker, quantity);

    try {
      this.out.append("Portfolio holdings updated");
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
