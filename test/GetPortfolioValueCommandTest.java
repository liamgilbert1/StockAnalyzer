import org.junit.Before;
import org.junit.Test;

import java.io.StringReader;
import java.util.Scanner;

import controller.commands.GetPortfolioValueCommand;
import controller.commands.ICommand;
import model.IModel;
import model.ModelImpl;
import model.portfolio.IPortfolio;

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
    assertEquals("Portfolio value is: 0.0", output.toString());
  }

  @Test
  public void testExecute2() {
    input = new StringReader("myPortfolio 2023-01-01\n");
    model.addPortfolioHolding("myPortfolio", "GOOG", 10);
    scanner = new Scanner(input);
    command.execute(model, scanner);
    assertEquals("Portfolio value is: 10.0", output.toString());
  }

  @Test
  public void testExecute3() {
    input = new StringReader("myPortfolio 2023-01-01\n");
    scanner = new Scanner(input);
    command.execute(model, scanner);
    assertEquals("Portfolio value is: 0.0", output.toString());
    model.addPortfolioHolding("myPortfolio", "GOOG", 10);
  }

  @Test
  public void testGetInstructions() {

  }
}