package controller.commands.newCommands;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import controller.commands.AWriterCommand;
import model.IModel2;

public class RebalanceCommand extends AWriterCommand {

  public RebalanceCommand(Appendable out) {
    super(out);
  }

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
