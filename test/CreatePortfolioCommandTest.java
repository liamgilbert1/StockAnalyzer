import org.junit.Before;
import org.junit.Test;

import java.io.StringReader;
import java.util.Scanner;

import controller.commands.CreatePortfolioCommand;
import controller.commands.ICommand;
import model.IModel;
import model.ModelImpl;

import static org.junit.Assert.assertEquals;

/**
 * Test cases for CreatePortfolioCommand.
 */
public class CreatePortfolioCommandTest {
  ICommand command;
  Appendable output;
  IModel model;
  Scanner scanner;
  Readable input;

  @Before
  public void setUp() {
    model = new ModelImpl();
    output = new StringBuilder();
    command = new CreatePortfolioCommand(output);
  }

  @Test
  public void execute() {
    input = new StringReader("myPortfolio myPortfolio2");
    scanner = new Scanner(input);
    command.execute(model, scanner);
    assertEquals("Portfolio Created: myPortfolio\n", output.toString());
    command.execute(model, scanner);
    assertEquals("Portfolio Created: myPortfolio\n"
            + "Portfolio Created: myPortfolio2\n", output.toString());
    model.getStocksInPortfolio("myPortfolio");

  }

  @Test
  public void getInstructions() {
    assertEquals("Create Portfolio: \n"
            + "This command creates a new portfolio for the user.\n"
            + "Enter the following parameters separated by spaces:\n"
            + "1. Command name (CreatePortfolio)\n"
            + "2. Portfolio name\n", command.getInstructions());
  }
}