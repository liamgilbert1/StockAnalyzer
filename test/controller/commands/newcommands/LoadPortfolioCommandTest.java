package controller.commands.newcommands;

import org.junit.Test;

import java.time.LocalDate;
import java.util.Scanner;

import model.IModel2;
import model.ModelImpl2;

import static org.junit.Assert.assertEquals;


/**
 * Test class for the LoadPortfolioCommand.
 */
public class LoadPortfolioCommandTest {

  @Test
  public void testExecute() {
    Appendable out = new StringBuilder();
    LoadPortfolioCommand loadPortfolioCommand = new LoadPortfolioCommand(out);
    IModel2 model = new ModelImpl2();
    Scanner scanner = new Scanner("TestPortfolio");
    loadPortfolioCommand.execute(model, scanner);
    assertEquals("Portfolio loaded successfully.\n", out.toString());
  }

  @Test
  public void testGetInstructions() {
    LoadPortfolioCommand loadPortfolioCommand = new LoadPortfolioCommand(new StringBuilder());
    assertEquals("Load Portfolio Command: \n" +
            "This command loads an existing portfolio.\n" +
            "Enter the following parameters separated by spaces:\n" +
            "1. Command name (LoadPortfolio)\n" +
            "2. Portfolio name\n", loadPortfolioCommand.getInstructions());
  }
}
