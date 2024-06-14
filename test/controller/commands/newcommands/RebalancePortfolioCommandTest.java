package controller.commands.newcommands;

import org.junit.Test;

import java.time.LocalDate;
import java.util.Scanner;

import model.IModel2;
import model.ModelImpl2;

import static org.junit.Assert.assertEquals;

/**
 * Test class for the RebalancePortfolioCommand.
 */
public class RebalancePortfolioCommandTest {

  @Test
  public void testExecute() {
    Appendable out = new StringBuilder();
    RebalancePortfolioCommand rebalancePortfolioCommand = new RebalancePortfolioCommand(out);
    IModel2 model = new ModelImpl2();
    model.createPortfolio("TestPortfolio");
    model.buyPortfolioHolding("TestPortfolio", "GOOG", 10,
            LocalDate.of(2024, 6, 4));
    model.buyPortfolioHolding("TestPortfolio", "MSFT", 20,
            LocalDate.of(2024, 6, 4));
    model.buyPortfolioHolding("TestPortfolio", "AMZN", 30,
            LocalDate.of(2024, 6, 4));
    rebalancePortfolioCommand.execute(model, new Scanner("TestPortfolio" +
            " 2024-06-04 GOOG 10 MSFT 30 AMZN 60"));
    assertEquals("Portfolio rebalanced\n", out.toString());
    assertEquals("GOOG: 8.82\n" +
            "MSFT: 11.14\n" +
            "AMZN: 51.70\n", model.getPortfolioComposition("TestPortfolio",
            LocalDate.of(2024, 6, 4)));
  }

  @Test
  public void testGetInstructions() {
    RebalancePortfolioCommand rebalancePortfolioCommand = new RebalancePortfolioCommand(new StringBuilder());
    assertEquals("Rebalance Portfolio: \n" +
            "This command rebalances the portfolio to the given stock weights.\n" +
            "Whatever stocks you give weight will be purchased and whatever stocks you do not will be sold, regardless of the current portfolio holdings.\n" +
            "Enter the following parameters separated by spaces:\n" +
            "1. Command name (RebalancePortfolio)\n" +
            "2. Portfolio name\n" +
            "3. Date (yyyy-mm-dd)\n" +
            "4. Stock weights (stock1 weight1 stock2 weight2 ...)\n", rebalancePortfolioCommand.getInstructions());
  }
}
