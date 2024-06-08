import org.junit.Test;

import java.io.StringReader;

import controller.ControllerImpl;
import controller.IController;
import model.ModelImpl;

import static org.junit.Assert.assertEquals;

/**
 * Test cases for ControllerImpl with a model.
 */
public class ControllerModelTest {
  @Test
  public void testControllerModelGainOrLoss() {
    Appendable output = new StringBuilder();
    Readable input = new StringReader("GainOrLoss GOOG 2024-06-04 2024-06-05\n");
    IController controller = new ControllerImpl(input, output);
    controller.control(new ModelImpl());
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
            "This command determines which days are x-day crossovers for a given stock, over a " +
            "specified time period \n" +
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
            "1. Command name (AddPortfolioHolding)\n" +
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
            "Gain or Loss: 1.94\n", output.toString());
  }

  @Test
  public void testControllerModelMovingAverage() {
    Appendable output = new StringBuilder();
    Readable input = new StringReader("MovingAverage GOOG 2023-01-01 30\n");
    IController controller = new ControllerImpl(input, output);
    controller.control(new ModelImpl());
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
            "This command determines which days are x-day crossovers for a given stock, over a " +
            "specified time period \n" +
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
            "1. Command name (AddPortfolioHolding)\n" +
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
            "Moving average is: 94.21\n", output.toString());
  }

  @Test
  public void testControllerModelCrossover() {
    Appendable output = new StringBuilder();
    Readable input = new StringReader("Crossover GOOG 2023-01-01 2023-01-31 10\n");
    IController controller = new ControllerImpl(input, output);
    controller.control(new ModelImpl());
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
            "This command determines which days are x-day crossovers for a given stock, over a " +
            "specified time period \n" +
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
            "1. Command name (AddPortfolioHolding)\n" +
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
            "Number of crossovers: 17\n" +
            "2023-01-31\n" +
            "2023-01-30\n" +
            "2023-01-27\n" +
            "2023-01-26\n" +
            "2023-01-25\n" +
            "2023-01-24\n" +
            "2023-01-23\n" +
            "2023-01-20\n" +
            "2023-01-19\n" +
            "2023-01-18\n" +
            "2023-01-17\n" +
            "2023-01-13\n" +
            "2023-01-12\n" +
            "2023-01-11\n" +
            "2023-01-10\n" +
            "2023-01-09\n" +
            "2023-01-03\n", output.toString());
  }

  @Test
  public void testControllerModelCreatePortfolio() {
    Appendable output = new StringBuilder();
    Readable input = new StringReader("CreatePortfolio TestPortfolio\n");
    IController controller = new ControllerImpl(input, output);
    controller.control(new ModelImpl());
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
            "This command determines which days are x-day crossovers for a given stock, over a " +
            "specified time period \n" +
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
            "1. Command name (AddPortfolioHolding)\n" +
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
            "Portfolio Created: TestPortfolio\n", output.toString());
  }

  @Test
  public void testControllerModelAddPortfolioHolding() {
    Appendable output = new StringBuilder();
    Readable input = new StringReader("CreatePortfolio TestPortfolio\n" +
            "AddPortfolioHolding TestPortfolio GOOG 10\n");
    IController controller = new ControllerImpl(input, output);
    controller.control(new ModelImpl());
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
            "This command determines which days are x-day crossovers for a given stock, over a " +
            "specified time period \n" +
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
            "1. Command name (AddPortfolioHolding)\n" +
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
  public void testControllerModelGetPortfolioValue() {
    Appendable output = new StringBuilder();
    Readable input = new StringReader("CreatePortfolio TestPortfolio\n" +
            "AddPortfolioHolding TestPortfolio GOOG 10\n" +
            "GetPortfolioValue TestPortfolio 2024-06-04\n");
    IController controller = new ControllerImpl(input, output);
    controller.control(new ModelImpl());
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
            "This command determines which days are x-day crossovers for a given stock, over a " +
            "specified time period \n" +
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
            "1. Command name (AddPortfolioHolding)\n" +
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
            "Portfolio holdings updated\n" +
            "Portfolio value is: 1751.30\n" +
            " ", output.toString());
  }

}
