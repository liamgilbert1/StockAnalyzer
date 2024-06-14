package controller.commands.newcommands;

import java.time.LocalDate;
import java.util.Scanner;

import controller.commands.AWriterCommand;

import model.IModel2;

/**
 * This class represents a command to sell shares of a stock in a portfolio on a specific date.
 * The user must provide the portfolio name, stock ticker symbol, quantity of shares, and date.
 * The command will then sell the shares of the stock in the portfolio on the specified date.
 */
public class SellPortfolioHoldingCommand extends AWriterCommand {

  /**
   * Constructs a SellPortfolioHoldingCommand object.
   * @param out the appendable object to output messages to
   */
  public SellPortfolioHoldingCommand(Appendable out) {
    super(out);
  }

  /**
   * Executes the SellPortfolioHoldingCommand. This command sells shares of a stock in an existing
   * portfolio on a specific date.
   * @param model the model to perform the command on
   * @param scanner the scanner object to read user input from
   */
  @Override
  public void execute(IModel2 model, Scanner scanner) {
    String portfolioName = getNextString(scanner);
    String ticker = getNextString(scanner);
    int quantity = getPositiveInt(scanner);
    LocalDate date = LocalDate.parse(getNextString(scanner));

    writeStockData(ticker);

    try {
      model.sellPortfolioHolding(portfolioName, ticker, quantity, date);
      this.out.append("Portfolio shares have been sold.\n");
    } catch (Exception e) {
      throw new IllegalStateException("Failed to process command.");
    }
  }

  /**
   * Gets the command instructions.
   * @return the instructions for the user
   */
  @Override
  public String getInstructions() {
    StringBuilder instructions;
    instructions = new StringBuilder();
    instructions.append("Sell Portfolio Holding's on Date Command: \n");
    instructions.append("This command sells shares of an existing portfolio on a " +
            "specific date.\n");
    instructions.append("Enter the following parameters separated by spaces:\n");
    instructions.append("1. Command name (SellPortfolioHolding)\n");
    instructions.append("2. Portfolio name\n");
    instructions.append("3. Stock ticker symbol\n");
    instructions.append("4. Quantity (number of shares)\n");
    instructions.append("5. Date in the format yyyy-mm-dd\n");
    return instructions.toString();
  }
}
