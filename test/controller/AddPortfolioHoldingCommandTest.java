package controller;

import org.junit.Before;
import org.junit.Test;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.Scanner;

import controller.commands.AddPortfolioHoldingCommand;
import controller.commands.ICommand;
import model.IModel;
import model.IModel2;
import model.ModelImpl;
import model.ModelImpl2;

import static org.junit.Assert.assertEquals;

/**
 * Test cases for AddPortfolioHoldingCommand.
 */
public class AddPortfolioHoldingCommandTest {
  ICommand command;
  Appendable output;
  IModel2 model;
  Scanner scanner;

  Readable input;

  @Before
  public void setUp() {
    model = new ModelImpl2();
    output = new StringBuilder();
    command = new AddPortfolioHoldingCommand(output);
    model.createPortfolio("myPortfolio");
  }

  @Test
  public void execute() {
    input = new StringReader("myPortfolio GOOG 10 myPortfolio MSFT 20 myPortfolio AMZN 30");
    scanner = new Scanner(input);
    command.execute(model, scanner);
    assertEquals("Portfolio holdings updated\n", output.toString());
    command.execute(model, scanner);
    assertEquals("Portfolio holdings updated\n"
            + "Portfolio holdings updated\n", output.toString());
    command.execute(model, scanner);
    ArrayList<String> expected = new ArrayList<>();
    expected.add("GOOG");
    expected.add("MSFT");
    expected.add("AMZN");
    assertEquals(expected, model.getStocksInPortfolio("myPortfolio"));
  }

  @Test
  public void getInstructions() {
    assertEquals("Add Portfolio Holding: \n" +
            "This command adds a holding to an existing portfolio.\n" +
            "Enter the following parameters separated by spaces:\n" +
            "1. Command name (BuyPortfolioHolding)\n" +
            "2. Portfolio name\n" +
            "3. Stock ticker symbol\n" +
            "4. Quantity (number of shares)\n", command.getInstructions());
  }
}