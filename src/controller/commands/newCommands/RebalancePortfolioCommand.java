package controller.commands.newCommands;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import controller.commands.AWriterCommand;
import model.IModel2;

/**
 * This class represents a command to rebalance a portfolio to the given stock weights.
 * The user must provide the portfolio name, date, and stock weights.
 * The command will then rebalance the portfolio to the given stock weights.
 */
public class RebalancePortfolioCommand extends AWriterCommand {

  /**
   * Constructs a RebalancePortfolioCommand object.
   * @param out the appendable object to output messages to
   */
  public RebalancePortfolioCommand(Appendable out) {
    super(out);
  }

  /**
   * Executes the RebalancePortfolioCommand. This command rebalances the portfolio to the given
   * stock weights.
   * @param model the model to perform the command on
   * @param scanner the scanner object to read user input from
   */
  @Override
  public void execute(IModel2 model, Scanner scanner) {
    String portfolioName = scanner.next();
    String date = scanner.next();

    Map<String, Integer> stockWeights = new HashMap<>();

    while (scanner.hasNext()) {
      stockWeights.put(getNextString(scanner), getPositiveInt(scanner));
    }

    for (String stock : stockWeights.keySet()) {
      writeStockData(stock);
    }

    try {
      model.rebalancePortfolio(portfolioName, LocalDate.parse(date), stockWeights);
      out.append("Portfolio rebalanced\n");
    } catch (Exception e) {
      throw new IllegalArgumentException(e.getMessage());
    }
  }

  /**
   * Gets the command instructions.
   * @return the instructions for the user
   */
  @Override
  public String getInstructions() {
    return "Rebalance Portfolio: \n"
            + "This command rebalances the portfolio to the given stock weights.\n"
            + "Whatever stocks you give weight will be purchased and whatever stocks you do not" +
            " will be sold, regardless of the current portfolio holdings.\n"
            + "Enter the following parameters separated by spaces:\n"
            + "1. Command name (RebalancePortfolio)\n"
            + "2. Portfolio name\n"
            + "3. Date (yyyy-mm-dd)\n"
            + "4. Stock weights (stock1 weight1 stock2 weight2 ...)\n";
  }
}
