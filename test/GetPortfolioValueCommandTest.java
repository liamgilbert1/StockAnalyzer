import org.junit.Before;
import org.junit.Test;

import java.io.StringReader;
import java.util.Scanner;

import controller.commands.GetPortfolioValueCommand;
import controller.commands.ICommand;
import model.IModel;
import model.ModelImpl;

import static org.junit.Assert.*;

public class GetPortfolioValueCommandTest {

  ICommand command;
  Appendable output;

  IModel model;

  Scanner scanner;

  Readable input;

  @Before
  public void setUp() {
    model = new ModelImpl();
    output = new StringBuilder();
    command = new GetPortfolioValueCommand(output);
    model.createPortfolio("myPortfolio");
  }

  @Test
  public void testExecute1() {
    input = new StringReader("myPortfolio 2023-01-01\n");
    scanner = new Scanner(input);
    command.execute(model, scanner);
    assertEquals("Portfolio value is: 0.00\n ", output.toString());
  }

  @Test
  public void testExecute2() {
    input = new StringReader("myPortfolio 2023-01-01\n");
    model.addPortfolioHolding("myPortfolio", "GOOG", 10);
    scanner = new Scanner(input);
    command.execute(model, scanner);
    assertEquals("Portfolio value is: 887.30\n ", output.toString());
  }

  @Test
  public void testExecute3() {
    input = new StringReader("myPortfolio 2023-01-01\n");
    model.addPortfolioHolding("myPortfolio", "GOOG", 10);
    model.addPortfolioHolding("myPortfolio", "GOOG", 20);
    model.addPortfolioHolding("myPortfolio", "GOOG", 30);
    scanner = new Scanner(input);
    command.execute(model, scanner);
    assertEquals("Portfolio value is: 5323.80\n ", output.toString());
  }

  @Test
  public void testGetInstructions() {
    assertEquals("Get Portfolio Value: \n"
            + "This command calculates the value of a portfolio on a given date.\n"
            + "Enter the following parameters separated by spaces:\n"
            + "1. Command name (GetPortfolioValue)\n"
            + "2. Portfolio name\n"
            + "3. Date in the format yyyy-mm-dd\n", command.getInstructions());
  }
}