package controller.commands;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.StringReader;
import java.time.LocalDate;
import java.util.Scanner;

import model.IModel2;
import model.ModelImpl2;

import static org.junit.Assert.assertEquals;

/**
 * This class tests the GetPortfolioValueCommand class.
 */
public class GetPortfolioValueCommandTest {

  ICommand command;
  Appendable output;

  IModel2 model;

  Scanner scanner;

  Readable input;

  @Before
  public void setUp() {
    model = new ModelImpl2();
    output = new StringBuilder();
    command = new GetPortfolioValueCommand(output);
    model.createPortfolio("myPortfolio");
  }

  @Test
  public void testExecute1() throws IOException {
    input = new StringReader("myPortfolio 2023-01-01\n");
    scanner = new Scanner(input);
    command.execute(model, scanner);
    assertEquals("Portfolio value is: $0.00\n ", output.toString());
  }

  @Test
  public void testExecute2() throws IOException {
    input = new StringReader("myPortfolio 2023-01-01\n");
    model.buyPortfolioHolding("myPortfolio", "GOOG", 10,
            LocalDate.of(2023, 1, 1));
    scanner = new Scanner(input);
    command.execute(model, scanner);
    assertEquals("Portfolio value is: $887.30\n ", output.toString());
  }

  @Test
  public void testExecute3() throws IOException {
    input = new StringReader("myPortfolio 2023-01-01\n");
    model.buyPortfolioHolding("myPortfolio", "GOOG", 10,
            LocalDate.of(2023, 1, 1));
    model.buyPortfolioHolding("myPortfolio", "GOOG", 20,
            LocalDate.of(2023, 1, 1));
    model.buyPortfolioHolding("myPortfolio", "GOOG", 30,
            LocalDate.of(2023, 1, 1));
    scanner = new Scanner(input);
    command.execute(model, scanner);
    assertEquals("Portfolio value is: $5323.80\n ", output.toString());
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