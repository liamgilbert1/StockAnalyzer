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
 * Test class for the GetPortfolioCompositionCommand.
 */
public class GetPortfolioCompositionCommandTest {
  ICommand command;
  Appendable output;
  IModel2 model;
  Scanner scanner;

  Readable input;

  @Before
  public void setUp() {
    model = new ModelImpl2();
    output = new StringBuilder();
    command = new GetPortfolioCompositionCommand(output);
    model.createPortfolio("myPortfolio");
    model.buyPortfolioHolding("myPortfolio", "GOOG", 5,
            java.time.LocalDate.of(2024, 6, 5));
    model.buyPortfolioHolding("myPortfolio", "MSFT", 10,
            java.time.LocalDate.of(2024, 6, 5));
  }

  @Test
  public void execute() {
    input = new StringReader("myPortfolio 2024-06-05");
    scanner = new Scanner(input);
    command.execute(model, scanner);
    assertEquals("GOOG: 5.00\n" +
            "MSFT: 10.00\n", output.toString());
  }

  @Test
  public void getInstructions() {
    String instructions = command.getInstructions();
    assertEquals("Get Portfolio Composition Command: \n" +
            "This command gets the composition of a portfolio (The stocks and the number of shares in each stock) on a specific date.\n" +
            "Enter the following parameters separated by spaces:\n" +
            "1. Command name (GetPortfolioComposition)\n" +
            "2. Portfolio name\n" +
            "3. Date in the format yyyy-mm-dd\n", instructions);
  }
}
