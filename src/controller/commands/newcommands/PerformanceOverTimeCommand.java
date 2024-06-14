package controller.commands.newcommands;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import controller.commands.AWriterCommand;
import model.IModel2;
import model.stock.IStock;

/**
 * This class represents a command to get the performance of a portfolio over a specific period of
 * time. The user must provide the portfolio name, start date, and end date. The command will then
 * return the performance of the portfolio over the specified period of time.
 */
public class PerformanceOverTimeCommand extends AWriterCommand {

  /**
   * Constructs a PerformanceOverTimeCommand object.
   * @param out the appendable object to output messages to
   */
  public PerformanceOverTimeCommand(Appendable out) {
    super(out);
  }

  /**
   * Executes the PerformanceOverTimeCommand. This command calculates the performance of a portfolio
   * over a given period of time.
   * @param model the model to perform the command on
   * @param scanner the scanner object to read user input from
   */
  @Override
  public void execute(IModel2 model, Scanner scanner) {
    String portfolioName = getNextString(scanner);
    LocalDate startDate = LocalDate.parse(getNextString(scanner));
    LocalDate endDate = LocalDate.parse(getNextString(scanner));

    List<IStock> stocks = new ArrayList<>();

    try {
      List<String> tickersInPortfolio = model.getStocksInPortfolio(portfolioName);
      for (String ticker : tickersInPortfolio) {
        stocks.add(model.getStock(ticker));
      }
    } catch (Exception e) {
      try {
        this.out.append("Failed to find stocks in portfolio. Please check the portfolio name.");
      } catch (Exception ex) {
        throw new IllegalStateException("Failed to append to output.");
      }
    }

    try {
      this.out.append(model.getPortfolioPerformanceOverTime(portfolioName, startDate, endDate));
    } catch (Exception e) {
      throw new IllegalArgumentException("Failed to process command.");
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
    instructions.append("Get Portfolio Performance Over Time: \n");
    instructions.append("This command calculates the performance of a portfolio over a " +
            "given period of time.\n");
    instructions.append("Enter the following parameters separated by spaces:\n");
    instructions.append("1. Command name (GetPerformanceOverTime)\n");
    instructions.append("2. Portfolio name\n");
    instructions.append("3. Start date in the format yyyy-mm-dd\n");
    instructions.append("4. End date in the format yyyy-mm-dd\n");
    return instructions.toString();
  }
}
