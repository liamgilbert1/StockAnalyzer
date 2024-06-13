package controller.commands.newCommands;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import controller.commands.AWriterCommand;
import model.IModel2;
import model.stock.IStock;

public class GetPortfolioDistributionCommand extends AWriterCommand {

  public GetPortfolioDistributionCommand(Appendable out) {
    super(out);
  }

  @Override
  public void execute(IModel2 model, Scanner scanner) {
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

    for (IStock stock : stocks) {
      writeStockData(stock.getTicker());
    }

    try {
      this.out.append(model.getPortfolioValueDistribution(portfolioName, date));
    } catch (Exception e) {
      throw new IllegalArgumentException("Failed to process command.");
    }
  }

  @Override
  public String getInstructions() {
    StringBuilder instructions;
    instructions = new StringBuilder();
    instructions.append("Get Portfolio Distribution Command: \n");
    instructions.append("This command gets the distribution of a portfolio " +
            "(The stocks and each stock's value) on a specific date.\n");
    instructions.append("Enter the following parameters separated by spaces:\n");
    instructions.append("1. Command name (GetPortfolioDistribution)\n");
    instructions.append("2. Portfolio name\n");
    instructions.append("3. Date in the format yyyy-mm-dd\n");
    return instructions.toString();
  }
}
