package controller.commands.newcommands;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import controller.commands.AWriterCommand;
import model.IModel2;
import model.stock.IStock;

/**
 * This class represents a command to get the composition of a portfolio on a specific date.
 * The user must provide the portfolio name and the date.
 * The command will then return the composition of the portfolio on the specified date.
 */
public class GetPortfolioCompositionCommand extends AWriterCommand {

  /**
   * Constructs a GetPortfolioCompositionCommand object.
   * @param out the appendable object to output messages to
   */
  public GetPortfolioCompositionCommand(Appendable out) {
    super(out);
  }

  /**
   * Executes the GetPortfolioCompositionCommand. This command gets the composition of a portfolio
   * on a specific date.
   * @param model the model to perform the command on
   * @param scanner the scanner object to read user input from
   */
  @Override
  public void execute(IModel2 model, Scanner scanner) throws IOException {
    String portfolioName = getNextString(scanner);
    LocalDate date = LocalDate.parse(getNextString(scanner));

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
      String portfolioComposition = model.getPortfolioComposition(portfolioName, date);
      this.out.append(portfolioComposition);
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
    instructions.append("Get Portfolio Composition Command: \n");
    instructions.append("This command gets the composition of a portfolio " +
            "(The stocks and the number of shares in each stock) on a specific date.\n");
    instructions.append("Enter the following parameters separated by spaces:\n");
    instructions.append("1. Command name (GetPortfolioComposition)\n");
    instructions.append("2. Portfolio name\n");
    instructions.append("3. Date in the format yyyy-mm-dd\n");
    return instructions.toString();
  }
}
