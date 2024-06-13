import org.junit.Test;

import java.io.StringReader;

import controller.ControllerImpl;
import controller.ControllerImpl2;
import controller.IController;
import model.ModelImpl2;

import static org.junit.Assert.assertEquals;

public class ControllerModel2Test {

  @Test
  public void testControllerModelBuyPortfolioHolding() {
    Appendable output = new StringBuilder();
    Readable input = new StringReader("CreatePortfolio TestPortfolio\n" +
            "BuyPortfolioHolding TestPortfolio GOOG 10 2024-06-05\n");
    IController controller = new ControllerImpl2(input, output);
    controller.control(new ModelImpl2());
    assertEquals("\n" +
            "Gain or Loss: \n" +
            "This command calculates the gain or loss of a stock over a given period of time.\n" +
            "Enter the following parameters separated by spaces:\n" +
            "1. Command name (GainOrLoss)\n" +
            "2. Stock ticker symbol\n" +
            "3. Starting date in the format yyyy-mm-dd\n" +
            "4. Ending date in the format yyyy-mm-dd\n" +
            "\n" +
            "Moving Average: \n" +
            "This command calculates the moving average of a stock over a given period of time.\n" +
            "Enter the following parameters separated by spaces:\n" +
            "1. Command name (MovingAverage)\n" +
            "2. Stock ticker symbol\n" +
            "3. Ending date in the format yyyy-mm-dd\n" +
            "4. Number of days to calculate the moving average over\n" +
            "\n" +
            "Crossovers: \n" +
            "This command determines which days are x-day crossovers for a given stock, over a specified time period \n" +
            "Enter the following parameters separated by spaces:\n" +
            "1. Command name (Crossover)\n" +
            "2. Stock ticker symbol\n" +
            "3. Starting date in the format yyyy-mm-dd\n" +
            "4. Ending date in the format yyyy-mm-dd\n" +
            "5. The number of days to check the crossover over (x)\n" +
            "\n" +
            "Create Portfolio: \n" +
            "This command creates a new portfolio for the user.\n" +
            "Enter the following parameters separated by spaces:\n" +
            "1. Command name (CreatePortfolio)\n" +
            "2. Portfolio name\n" +
            "\n" +
            "Add Portfolio Holding: \n" +
            "This command adds a holding to an existing portfolio.\n" +
            "Enter the following parameters separated by spaces:\n" +
            "1. Command name (BuyPortfolioHolding)\n" +
            "2. Portfolio name\n" +
            "3. Stock ticker symbol\n" +
            "4. Quantity (number of shares)\n" +
            "\n" +
            "Get Portfolio Value: \n" +
            "This command calculates the value of a portfolio on a given date.\n" +
            "Enter the following parameters separated by spaces:\n" +
            "1. Command name (GetPortfolioValue)\n" +
            "2. Portfolio name\n" +
            "3. Date in the format yyyy-mm-dd\n" +
            "\n" +
            "Enter 'menu' to see the instructions again.\n" +
            "Enter 'quit' to quit the program.\n" +
            "\n" +
            "Portfolio Created: TestPortfolio\n" +
            "Portfolio holdings updated\n", output.toString());
  }

  @Test
  public void testControllerModelSellPortfolioHolding() {
    Appendable output = new StringBuilder();
    Readable input = new StringReader("CreatePortfolio TestPortfolio\n" +
            "BuyPortfolioHolding TestPortfolio GOOG 5 2024-06-05\n" +
            "SellPortfolioHolding TestPortfolio GOOG 3 2024-06-05\n" +
            "GetPortfolioValue TestPortfolio 2024-06-05\n");
    IController controller = new ControllerImpl2(input, output);
    controller.control(new ModelImpl2());
  }

  @Test
  public void testControllerModelGetPortfolioValue() {
    Appendable output = new StringBuilder();
    Readable input = new StringReader("CreatePortfolio TestPortfolio\n" +
            "BuyPortfolioHolding TestPortfolio GOOG 50 2020-01-01\n" +
            "GetPortfolioValue TestPortfolio 2020-01-01\n");
    IController controller = new ControllerImpl2(input, output);
    controller.control(new ModelImpl2());
  }

  @Test
  public void testControllerModelGetPerformanceOverTime() {
    Appendable output = new StringBuilder();
    Readable input = new StringReader("CreatePortfolio TestPortfolio\n" +
            "BuyPortfolioHolding TestPortfolio GOOG 50 2020-01-01\n" +
            "BuyPortfolioHolding TestPortfolio AMZN 10 2020-05-01\n" +
            "GetPerformanceOverTime TestPortfolio 2020-01-01 2021-01-01\n");
    IController controller = new ControllerImpl2(input, output);
    controller.control(new ModelImpl2());
  }
}

