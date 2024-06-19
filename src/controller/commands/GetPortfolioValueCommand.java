package controller.commands;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import model.IModel2;
import model.stock.IStock;

/**
 * This class represents a command that can be executed by the controller. This command determines
 * the value of a given portfolio.
 */
public class GetPortfolioValueCommand extends AWriterCommand {
  public GetPortfolioValueCommand(Appendable out) {
    super(out);
  }

  /**
   * Executes the GetPortfolioValueCommand with the given model and scanner.
   * @param model the model to execute the command on
   * @param scanner the scanner to get the next inputs from
   * @throws IllegalArgumentException if the command fails to execute
   */
  @Override
  public void execute(IModel2 model, Scanner scanner) throws IOException {
    String portfolioName = getNextString(scanner);
    String dateEntered = getNextString(scanner);
    LocalDate date = LocalDate.parse(dateEntered);

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
      for (IStock stock : stocks) {
        tryWrite(stock.getTicker());
      }
    } catch (Exception e) {
      throw new IllegalStateException("Failed to write stock data in " +
              "GetPortfolioCompositionCommand");
    }

    try {
      double portfolioValue = model.getPortfolioValue(portfolioName, date);

      this.out.append(String.format("Portfolio value is: $%.2f\n ", portfolioValue));
    } catch (Exception e) {
      throw new IllegalArgumentException("Failed to process command.");
    }
  }

  /**
   * Gets the instructions for the GetPortfolioValueCommand.
   * @return the instructions for the GetPortfolioValueCommand
   */
  @Override
  public String getInstructions() {
    StringBuilder instructions;
    instructions = new StringBuilder();
    instructions.append("Get Portfolio Value: \n");
    instructions.append("This command calculates the value of a portfolio on a given date.\n");
    instructions.append("Enter the following parameters separated by spaces:\n");
    instructions.append("1. Command name (GetPortfolioValue)\n");
    instructions.append("2. Portfolio name\n");
    instructions.append("3. Date in the format yyyy-mm-dd\n");
    return instructions.toString();
  }
}
