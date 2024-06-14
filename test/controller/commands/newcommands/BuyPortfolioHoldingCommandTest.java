package controller.commands.newcommands;

import org.junit.Before;
import org.junit.Test;

import java.io.StringReader;

import java.util.Scanner;


import controller.commands.ICommand;
import model.IModel2;
import model.ModelImpl2;

import static org.junit.Assert.assertEquals;

/**
 * Test class for the BuyPortfolioHoldingCommand.
 */
public class BuyPortfolioHoldingCommandTest {
  ICommand command;
  Appendable output;
  IModel2 model;
  Scanner scanner;

  Readable input;

  @Before
  public void setUp() {
    model = new ModelImpl2();
    output = new StringBuilder();
    command = new BuyPortfolioHoldingCommand(output);
    model.createPortfolio("myPortfolio");
  }

  @Test
  public void execute() {
    input = new StringReader("myPortfolio GOOG 5 2024-06-05");
    scanner = new Scanner(input);
    command.execute(model, scanner);
    assertEquals("Portfolio shares have been purchased\n", output.toString());
  }

  @Test
  public void getInstructions() {
    String instructions = command.getInstructions();
    assertEquals("Buy Portfolio Holding's on Date Command: \n" +
            "This command purchases shares to an existing portfolio on a specific date.\n" +
            "Enter the following parameters separated by spaces:\n" +
            "1. Command name (BuyPortfolioHolding)\n" +
            "2. Portfolio name\n" +
            "3. Stock ticker symbol\n" +
            "4. Quantity (number of shares)\n" +
            "5. Date in the format yyyy-mm-dd\n", instructions);
  }
}
