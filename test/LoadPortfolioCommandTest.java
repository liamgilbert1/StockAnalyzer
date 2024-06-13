
import org.junit.Test;

import java.util.Scanner;

import controller.commands.newCommands.LoadPortfolioCommand;
import model.IModel2;
import model.ModelImpl2;

import static org.junit.Assert.assertEquals;


public class LoadPortfolioCommandTest {

  @Test
  public void testExecute() {
    Appendable out = new StringBuilder();
    LoadPortfolioCommand loadPortfolioCommand = new LoadPortfolioCommand(out);
    IModel2 model = new ModelImpl2();
    Scanner scanner = new Scanner("myPortfolio");
    loadPortfolioCommand.execute(model, scanner);
    assertEquals("Portfolio loaded successfully.", out.toString());
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
