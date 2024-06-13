package controller.commands.newCommands;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import controller.commands.AWriterCommand;
import model.IModel;
import model.IModel2;
import model.stock.IStock;

public class GetPortfolioValueCommand2 extends AWriterCommand {

  public GetPortfolioValueCommand2(Appendable out) {
    super(out);
  }

  @Override
  public void execute(IModel2 model, Scanner scanner) {
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

    tryWrite(stocks, date);
    try {
      double portfolioValue = model.getPortfolioValue(portfolioName, date);

      this.out.append(String.format("Portfolio value is: %.2f\n ", portfolioValue));
    } catch (Exception e) {
      throw new IllegalArgumentException("Failed to process command.");
    }
  }

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
