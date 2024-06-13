package controller.commands.newCommands;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import controller.commands.AWriterCommand;
import controller.readers.CSVReader;
import controller.readers.IReader;
import model.IModel;
import model.IModel2;
import model.stock.IStock;

public class PerformanceOverTimeCommand extends AWriterCommand {

  public PerformanceOverTimeCommand(Appendable out) {
    super(out);
  }

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

//    try {
//      List<LocalDate> dates = model.getPortfolioPerformanceDates(portfolioName, startDate, endDate);
//      for (LocalDate date : dates) {
//        tryWrite(stocks, date);
//      }
//    } catch (Exception e) {
//      throw new IllegalArgumentException("Failed to process command.");
//    }

    try {
      this.out.append(model.getPortfolioPerformanceOverTime(portfolioName, startDate, endDate));
    } catch (Exception e) {
      throw new IllegalArgumentException("Failed to process command.");
    }
  }

  @Override
  public String getInstructions() {
    StringBuilder instructions;
    instructions = new StringBuilder();
    instructions.append("Get Portfolio Performance Over Time: \n");
    instructions.append("This command calculates the performance of a portfolio over a " +
            "given period of time.\n");
    instructions.append("Enter the following parameters separated by spaces:\n");
    instructions.append("1. Command name (GetPortfolioPerformanceOverTime)\n");
    instructions.append("2. Portfolio name\n");
    instructions.append("3. Start date in the format yyyy-mm-dd\n");
    instructions.append("4. End date in the format yyyy-mm-dd\n");
    return instructions.toString();
  }
}
