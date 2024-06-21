package controller.textcontroller;

import org.junit.Test;

import java.io.IOException;
import java.io.StringReader;
import java.time.LocalDate;

import model.IModel2;
import model.ModelImpl2;

import static org.junit.Assert.assertEquals;

/**
 * Test cases for ControllerImpl2.
 */
public class TextControllerModel2Test {

  @Test
  public void testControllerModelBuyPortfolioHolding() throws IOException {
    Appendable output = new StringBuilder();
    Readable input = new StringReader("CreatePortfolio TestPortfolio\n" +
            "BuyPortfolioHolding TestPortfolio GOOG 10 2024-06-05\n");
    ITextController controller = new TextControllerImpl2(input, output);
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
            "This command determines which days are x-day crossovers for a given stock, " +
            "over a specified time period \n" +
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
            "Buy Portfolio Holding's on Date Command: \n" +
            "This command purchases shares to an existing portfolio on a specific date.\n" +
            "Enter the following parameters separated by spaces:\n" +
            "1. Command name (BuyPortfolioHolding)\n" +
            "2. Portfolio name\n" +
            "3. Stock ticker symbol\n" +
            "4. Quantity (number of shares)\n" +
            "5. Date in the format yyyy-mm-dd\n" +
            "\n" +
            "Sell Portfolio Holding's on Date Command: \n" +
            "This command sells shares of an existing portfolio on a specific date.\n" +
            "Enter the following parameters separated by spaces:\n" +
            "1. Command name (SellPortfolioHolding)\n" +
            "2. Portfolio name\n" +
            "3. Stock ticker symbol\n" +
            "4. Quantity (number of shares)\n" +
            "5. Date in the format yyyy-mm-dd\n" +
            "\n" +
            "Get Portfolio Value: \n" +
            "This command calculates the value of a portfolio on a given date.\n" +
            "Enter the following parameters separated by spaces:\n" +
            "1. Command name (GetPortfolioValue)\n" +
            "2. Portfolio name\n" +
            "3. Date in the format yyyy-mm-dd\n" +
            "\n" +
            "Get Portfolio Composition Command: \n" +
            "This command gets the composition of a portfolio (The stocks and the " +
            "number of shares in each stock) on a specific date.\n" +
            "Enter the following parameters separated by spaces:\n" +
            "1. Command name (GetPortfolioComposition)\n" +
            "2. Portfolio name\n" +
            "3. Date in the format yyyy-mm-dd\n" +
            "\n" +
            "Get Portfolio Distribution Command: \n" +
            "This command gets the distribution of a portfolio " +
            "(The stocks and each stock's value) on a specific date.\n" +
            "Enter the following parameters separated by spaces:\n" +
            "1. Command name (GetPortfolioDistribution)\n" +
            "2. Portfolio name\n" +
            "3. Date in the format yyyy-mm-dd\n" +
            "\n" +
            "Rebalance Portfolio: \n" +
            "This command rebalances the portfolio to the given stock weights.\n" +
            "Whatever stocks you give weight will be purchased and whatever " +
            "stocks you do not will be sold, regardless of the current portfolio holdings.\n" +
            "Enter the following parameters separated by spaces:\n" +
            "1. Command name (RebalancePortfolio)\n" +
            "2. Portfolio name\n" +
            "3. Date (yyyy-mm-dd)\n" +
            "4. Stock weights (stock1 weight1 stock2 weight2 ...)\n" +
            "\n" +
            "Get Portfolio Performance Over Time: \n" +
            "This command calculates the performance of a portfolio " +
            "over a given period of time.\n" +
            "Enter the following parameters separated by spaces:\n" +
            "1. Command name (GetPerformanceOverTime)\n" +
            "2. Portfolio name\n" +
            "3. Start date in the format yyyy-mm-dd\n" +
            "4. End date in the format yyyy-mm-dd\n" +
            "\n" +
            "Load Portfolio Command: \n" +
            "This command loads an existing portfolio.\n" +
            "Enter the following parameters separated by spaces:\n" +
            "1. Command name (LoadPortfolio)\n" +
            "2. Portfolio name\n" +
            "\n" +
            "Enter 'menu' to see the instructions again.\n" +
            "Enter 'quit' to quit the program.\n" +
            "\n" +
            "Portfolio Created: TestPortfolio\n" +
            "Portfolio shares have been purchased\n", output.toString());

  }

  @Test
  public void testControllerModelSellPortfolioHolding() throws IOException {
    Appendable output = new StringBuilder();
    Readable input = new StringReader("CreatePortfolio TestPortfolio\n" +
            "BuyPortfolioHolding TestPortfolio GOOG 5 2024-06-05\n" +
            "SellPortfolioHolding TestPortfolio GOOG 3 2024-06-05\n");
    ITextController controller = new TextControllerImpl2(input, output);
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
            "This command determines which days are x-day crossovers for a given stock, " +
            "over a specified time period \n" +
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
            "Buy Portfolio Holding's on Date Command: \n" +
            "This command purchases shares to an existing portfolio on a specific date.\n" +
            "Enter the following parameters separated by spaces:\n" +
            "1. Command name (BuyPortfolioHolding)\n" +
            "2. Portfolio name\n" +
            "3. Stock ticker symbol\n" +
            "4. Quantity (number of shares)\n" +
            "5. Date in the format yyyy-mm-dd\n" +
            "\n" +
            "Sell Portfolio Holding's on Date Command: \n" +
            "This command sells shares of an existing portfolio on a specific date.\n" +
            "Enter the following parameters separated by spaces:\n" +
            "1. Command name (SellPortfolioHolding)\n" +
            "2. Portfolio name\n" +
            "3. Stock ticker symbol\n" +
            "4. Quantity (number of shares)\n" +
            "5. Date in the format yyyy-mm-dd\n" +
            "\n" +
            "Get Portfolio Value: \n" +
            "This command calculates the value of a portfolio on a given date.\n" +
            "Enter the following parameters separated by spaces:\n" +
            "1. Command name (GetPortfolioValue)\n" +
            "2. Portfolio name\n" +
            "3. Date in the format yyyy-mm-dd\n" +
            "\n" +
            "Get Portfolio Composition Command: \n" +
            "This command gets the composition of a portfolio (The stocks and the number " +
            "of shares in each stock) on a specific date.\n" +
            "Enter the following parameters separated by spaces:\n" +
            "1. Command name (GetPortfolioComposition)\n" +
            "2. Portfolio name\n" +
            "3. Date in the format yyyy-mm-dd\n" +
            "\n" +
            "Get Portfolio Distribution Command: \n" +
            "This command gets the distribution of a portfolio " +
            "(The stocks and each stock's value) on a specific date.\n" +
            "Enter the following parameters separated by spaces:\n" +
            "1. Command name (GetPortfolioDistribution)\n" +
            "2. Portfolio name\n" +
            "3. Date in the format yyyy-mm-dd\n" +
            "\n" +
            "Rebalance Portfolio: \n" +
            "This command rebalances the portfolio to the given stock weights.\n" +
            "Whatever stocks you give weight will be purchased and whatever stocks " +
            "you do not will be sold, regardless of the current portfolio holdings.\n" +
            "Enter the following parameters separated by spaces:\n" +
            "1. Command name (RebalancePortfolio)\n" +
            "2. Portfolio name\n" +
            "3. Date (yyyy-mm-dd)\n" +
            "4. Stock weights (stock1 weight1 stock2 weight2 ...)\n" +
            "\n" +
            "Get Portfolio Performance Over Time: \n" +
            "This command calculates the performance of a portfolio over a " +
            "given period of time.\n" +
            "Enter the following parameters separated by spaces:\n" +
            "1. Command name (GetPerformanceOverTime)\n" +
            "2. Portfolio name\n" +
            "3. Start date in the format yyyy-mm-dd\n" +
            "4. End date in the format yyyy-mm-dd\n" +
            "\n" +
            "Load Portfolio Command: \n" +
            "This command loads an existing portfolio.\n" +
            "Enter the following parameters separated by spaces:\n" +
            "1. Command name (LoadPortfolio)\n" +
            "2. Portfolio name\n" +
            "\n" +
            "Enter 'menu' to see the instructions again.\n" +
            "Enter 'quit' to quit the program.\n" +
            "\n" +
            "Portfolio Created: TestPortfolio\n" +
            "Portfolio shares have been purchased\n" +
            "Portfolio shares have been sold.\n", output.toString());
  }

  @Test
  public void testControllerModelGetPortfolioValue() throws IOException {
    Appendable output = new StringBuilder();
    Readable input = new StringReader("CreatePortfolio TestPortfolio\n" +
            "BuyPortfolioHolding TestPortfolio GOOG 5 2024-06-05\n" +
            "GetPortfolioValue TestPortfolio 2024-06-05\n");
    ITextController controller = new TextControllerImpl2(input, output);
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
            "Buy Portfolio Holding's on Date Command: \n" +
            "This command purchases shares to an existing portfolio on a specific date.\n" +
            "Enter the following parameters separated by spaces:\n" +
            "1. Command name (BuyPortfolioHolding)\n" +
            "2. Portfolio name\n" +
            "3. Stock ticker symbol\n" +
            "4. Quantity (number of shares)\n" +
            "5. Date in the format yyyy-mm-dd\n" +
            "\n" +
            "Sell Portfolio Holding's on Date Command: \n" +
            "This command sells shares of an existing portfolio on a specific date.\n" +
            "Enter the following parameters separated by spaces:\n" +
            "1. Command name (SellPortfolioHolding)\n" +
            "2. Portfolio name\n" +
            "3. Stock ticker symbol\n" +
            "4. Quantity (number of shares)\n" +
            "5. Date in the format yyyy-mm-dd\n" +
            "\n" +
            "Get Portfolio Value: \n" +
            "This command calculates the value of a portfolio on a given date.\n" +
            "Enter the following parameters separated by spaces:\n" +
            "1. Command name (GetPortfolioValue)\n" +
            "2. Portfolio name\n" +
            "3. Date in the format yyyy-mm-dd\n" +
            "\n" +
            "Get Portfolio Composition Command: \n" +
            "This command gets the composition of a portfolio (The stocks and the number of shares in each stock) on a specific date.\n" +
            "Enter the following parameters separated by spaces:\n" +
            "1. Command name (GetPortfolioComposition)\n" +
            "2. Portfolio name\n" +
            "3. Date in the format yyyy-mm-dd\n" +
            "\n" +
            "Get Portfolio Distribution Command: \n" +
            "This command gets the distribution of a portfolio (The stocks and each stock's value) on a specific date.\n" +
            "Enter the following parameters separated by spaces:\n" +
            "1. Command name (GetPortfolioDistribution)\n" +
            "2. Portfolio name\n" +
            "3. Date in the format yyyy-mm-dd\n" +
            "\n" +
            "Rebalance Portfolio: \n" +
            "This command rebalances the portfolio to the given stock weights.\n" +
            "Whatever stocks you give weight will be purchased and whatever stocks you do not will be sold, regardless of the current portfolio holdings.\n" +
            "Enter the following parameters separated by spaces:\n" +
            "1. Command name (RebalancePortfolio)\n" +
            "2. Portfolio name\n" +
            "3. Date (yyyy-mm-dd)\n" +
            "4. Stock weights (stock1 weight1 stock2 weight2 ...)\n" +
            "\n" +
            "Get Portfolio Performance Over Time: \n" +
            "This command calculates the performance of a portfolio over a given period of time.\n" +
            "Enter the following parameters separated by spaces:\n" +
            "1. Command name (GetPerformanceOverTime)\n" +
            "2. Portfolio name\n" +
            "3. Start date in the format yyyy-mm-dd\n" +
            "4. End date in the format yyyy-mm-dd\n" +
            "\n" +
            "Load Portfolio Command: \n" +
            "This command loads an existing portfolio.\n" +
            "Enter the following parameters separated by spaces:\n" +
            "1. Command name (LoadPortfolio)\n" +
            "2. Portfolio name\n" +
            "\n" +
            "Enter 'menu' to see the instructions again.\n" +
            "Enter 'quit' to quit the program.\n" +
            "\n" +
            "Portfolio Created: TestPortfolio\n" +
            "Portfolio shares have been purchased\n" +
            "Portfolio value is: $885.35", output.toString());
  }

  @Test
  public void testControllerModelGetPortfolioValue2() throws IOException {
    Appendable output = new StringBuilder();
    Readable input = new StringReader("CreatePortfolio TestPortfolio\n" +
            "BuyPortfolioHolding TestPortfolio GOOG 5 2024-06-05\n" +
            "GetPortfolioValue TestPortfolio 2024-01-01\n");
    ITextController controller = new TextControllerImpl2(input, output);
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
            "This command determines which days are x-day crossovers for a given stock, " +
            "over a specified time period \n" +
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
            "Buy Portfolio Holding's on Date Command: \n" +
            "This command purchases shares to an existing portfolio on a specific date.\n" +
            "Enter the following parameters separated by spaces:\n" +
            "1. Command name (BuyPortfolioHolding)\n" +
            "2. Portfolio name\n" +
            "3. Stock ticker symbol\n" +
            "4. Quantity (number of shares)\n" +
            "5. Date in the format yyyy-mm-dd\n" +
            "\n" +
            "Sell Portfolio Holding's on Date Command: \n" +
            "This command sells shares of an existing portfolio on a specific date.\n" +
            "Enter the following parameters separated by spaces:\n" +
            "1. Command name (SellPortfolioHolding)\n" +
            "2. Portfolio name\n" +
            "3. Stock ticker symbol\n" +
            "4. Quantity (number of shares)\n" +
            "5. Date in the format yyyy-mm-dd\n" +
            "\n" +
            "Get Portfolio Value: \n" +
            "This command calculates the value of a portfolio on a given date.\n" +
            "Enter the following parameters separated by spaces:\n" +
            "1. Command name (GetPortfolioValue)\n" +
            "2. Portfolio name\n" +
            "3. Date in the format yyyy-mm-dd\n" +
            "\n" +
            "Get Portfolio Composition Command: \n" +
            "This command gets the composition of a portfolio (The stocks and the " +
            "number of shares in each stock) on a specific date.\n" +
            "Enter the following parameters separated by spaces:\n" +
            "1. Command name (GetPortfolioComposition)\n" +
            "2. Portfolio name\n" +
            "3. Date in the format yyyy-mm-dd\n" +
            "\n" +
            "Get Portfolio Distribution Command: \n" +
            "This command gets the distribution of a portfolio " +
            "(The stocks and each stock's value) on a specific date.\n" +
            "Enter the following parameters separated by spaces:\n" +
            "1. Command name (GetPortfolioDistribution)\n" +
            "2. Portfolio name\n" +
            "3. Date in the format yyyy-mm-dd\n" +
            "\n" +
            "Rebalance Portfolio: \n" +
            "This command rebalances the portfolio to the given stock weights.\n" +
            "Whatever stocks you give weight will be purchased and whatever stocks " +
            "you do not will be sold, regardless of the current portfolio holdings.\n" +
            "Enter the following parameters separated by spaces:\n" +
            "1. Command name (RebalancePortfolio)\n" +
            "2. Portfolio name\n" +
            "3. Date (yyyy-mm-dd)\n" +
            "4. Stock weights (stock1 weight1 stock2 weight2 ...)\n" +
            "\n" +
            "Get Portfolio Performance Over Time: \n" +
            "This command calculates the performance of a portfolio over" +
            " a given period of time.\n" +
            "Enter the following parameters separated by spaces:\n" +
            "1. Command name (GetPerformanceOverTime)\n" +
            "2. Portfolio name\n" +
            "3. Start date in the format yyyy-mm-dd\n" +
            "4. End date in the format yyyy-mm-dd\n" +
            "\n" +
            "Load Portfolio Command: \n" +
            "This command loads an existing portfolio.\n" +
            "Enter the following parameters separated by spaces:\n" +
            "1. Command name (LoadPortfolio)\n" +
            "2. Portfolio name\n" +
            "\n" +
            "Enter 'menu' to see the instructions again.\n" +
            "Enter 'quit' to quit the program.\n" +
            "\n" +
            "Portfolio Created: TestPortfolio\n" +
            "Portfolio shares have been purchased\n" +
            "Portfolio value is: $0.00", output.toString());
  }

  @Test
  public void testControllerModelGetPortfolioComposition() throws IOException {
    Appendable output = new StringBuilder();
    Readable input = new StringReader("CreatePortfolio TestPortfolio\n" +
            "BuyPortfolioHolding TestPortfolio GOOG 50 2020-01-01\n" +
            "BuyPortfolioHolding TestPortfolio AMZN 10 2020-01-01\n" +
            "GetPortfolioComposition TestPortfolio 2020-01-01\n");
    ITextController controller = new TextControllerImpl2(input, output);
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
            "This command determines which days are x-day crossovers for a given stock, " +
            "over a specified time period \n" +
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
            "Buy Portfolio Holding's on Date Command: \n" +
            "This command purchases shares to an existing portfolio on a specific date.\n" +
            "Enter the following parameters separated by spaces:\n" +
            "1. Command name (BuyPortfolioHolding)\n" +
            "2. Portfolio name\n" +
            "3. Stock ticker symbol\n" +
            "4. Quantity (number of shares)\n" +
            "5. Date in the format yyyy-mm-dd\n" +
            "\n" +
            "Sell Portfolio Holding's on Date Command: \n" +
            "This command sells shares of an existing portfolio on a specific date.\n" +
            "Enter the following parameters separated by spaces:\n" +
            "1. Command name (SellPortfolioHolding)\n" +
            "2. Portfolio name\n" +
            "3. Stock ticker symbol\n" +
            "4. Quantity (number of shares)\n" +
            "5. Date in the format yyyy-mm-dd\n" +
            "\n" +
            "Get Portfolio Value: \n" +
            "This command calculates the value of a portfolio on a given date.\n" +
            "Enter the following parameters separated by spaces:\n" +
            "1. Command name (GetPortfolioValue)\n" +
            "2. Portfolio name\n" +
            "3. Date in the format yyyy-mm-dd\n" +
            "\n" +
            "Get Portfolio Composition Command: \n" +
            "This command gets the composition of a portfolio (The stocks and the " +
            "number of shares in each stock) on a specific date.\n" +
            "Enter the following parameters separated by spaces:\n" +
            "1. Command name (GetPortfolioComposition)\n" +
            "2. Portfolio name\n" +
            "3. Date in the format yyyy-mm-dd\n" +
            "\n" +
            "Get Portfolio Distribution Command: \n" +
            "This command gets the distribution of a portfolio " +
            "(The stocks and each stock's value) on a specific date.\n" +
            "Enter the following parameters separated by spaces:\n" +
            "1. Command name (GetPortfolioDistribution)\n" +
            "2. Portfolio name\n" +
            "3. Date in the format yyyy-mm-dd\n" +
            "\n" +
            "Rebalance Portfolio: \n" +
            "This command rebalances the portfolio to the given stock weights.\n" +
            "Whatever stocks you give weight will be purchased and whatever stocks you " +
            "do not will be sold, regardless of the current portfolio holdings.\n" +
            "Enter the following parameters separated by spaces:\n" +
            "1. Command name (RebalancePortfolio)\n" +
            "2. Portfolio name\n" +
            "3. Date (yyyy-mm-dd)\n" +
            "4. Stock weights (stock1 weight1 stock2 weight2 ...)\n" +
            "\n" +
            "Get Portfolio Performance Over Time: \n" +
            "This command calculates the performance of a portfolio over a " +
            "given period of time.\n" +
            "Enter the following parameters separated by spaces:\n" +
            "1. Command name (GetPerformanceOverTime)\n" +
            "2. Portfolio name\n" +
            "3. Start date in the format yyyy-mm-dd\n" +
            "4. End date in the format yyyy-mm-dd\n" +
            "\n" +
            "Load Portfolio Command: \n" +
            "This command loads an existing portfolio.\n" +
            "Enter the following parameters separated by spaces:\n" +
            "1. Command name (LoadPortfolio)\n" +
            "2. Portfolio name\n" +
            "\n" +
            "Enter 'menu' to see the instructions again.\n" +
            "Enter 'quit' to quit the program.\n" +
            "\n" +
            "Portfolio Created: TestPortfolio\n" +
            "Portfolio shares have been purchased\n" +
            "Portfolio shares have been purchased\n" +
            "GOOG: 50.00\n" +
            "AMZN: 10.00\n", output.toString());
  }

  @Test
  public void testControllerModelGetPortfolioDistribution() throws IOException {
    Appendable output = new StringBuilder();
    Readable input = new StringReader("CreatePortfolio TestPortfolio\n" +
            "BuyPortfolioHolding TestPortfolio GOOG 50 2020-01-01\n" +
            "BuyPortfolioHolding TestPortfolio AMZN 10 2020-01-01\n" +
            "GetPortfolioDistribution TestPortfolio 2020-01-01\n");
    ITextController controller = new TextControllerImpl2(input, output);
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
            "This command determines which days are x-day crossovers for a given stock, " +
            "over a specified time period \n" +
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
            "Buy Portfolio Holding's on Date Command: \n" +
            "This command purchases shares to an existing portfolio on a specific date.\n" +
            "Enter the following parameters separated by spaces:\n" +
            "1. Command name (BuyPortfolioHolding)\n" +
            "2. Portfolio name\n" +
            "3. Stock ticker symbol\n" +
            "4. Quantity (number of shares)\n" +
            "5. Date in the format yyyy-mm-dd\n" +
            "\n" +
            "Sell Portfolio Holding's on Date Command: \n" +
            "This command sells shares of an existing portfolio on a specific date.\n" +
            "Enter the following parameters separated by spaces:\n" +
            "1. Command name (SellPortfolioHolding)\n" +
            "2. Portfolio name\n" +
            "3. Stock ticker symbol\n" +
            "4. Quantity (number of shares)\n" +
            "5. Date in the format yyyy-mm-dd\n" +
            "\n" +
            "Get Portfolio Value: \n" +
            "This command calculates the value of a portfolio on a given date.\n" +
            "Enter the following parameters separated by spaces:\n" +
            "1. Command name (GetPortfolioValue)\n" +
            "2. Portfolio name\n" +
            "3. Date in the format yyyy-mm-dd\n" +
            "\n" +
            "Get Portfolio Composition Command: \n" +
            "This command gets the composition of a portfolio (The stocks and the " +
            "number of shares in each stock) on a specific date.\n" +
            "Enter the following parameters separated by spaces:\n" +
            "1. Command name (GetPortfolioComposition)\n" +
            "2. Portfolio name\n" +
            "3. Date in the format yyyy-mm-dd\n" +
            "\n" +
            "Get Portfolio Distribution Command: \n" +
            "This command gets the distribution of a portfolio " +
            "(The stocks and each stock's value) on a specific date.\n" +
            "Enter the following parameters separated by spaces:\n" +
            "1. Command name (GetPortfolioDistribution)\n" +
            "2. Portfolio name\n" +
            "3. Date in the format yyyy-mm-dd\n" +
            "\n" +
            "Rebalance Portfolio: \n" +
            "This command rebalances the portfolio to the given stock weights.\n" +
            "Whatever stocks you give weight will be purchased and whatever stocks you " +
            "do not will be sold, regardless of the current portfolio holdings.\n" +
            "Enter the following parameters separated by spaces:\n" +
            "1. Command name (RebalancePortfolio)\n" +
            "2. Portfolio name\n" +
            "3. Date (yyyy-mm-dd)\n" +
            "4. Stock weights (stock1 weight1 stock2 weight2 ...)\n" +
            "\n" +
            "Get Portfolio Performance Over Time: \n" +
            "This command calculates the performance of a portfolio over a" +
            " given period of time.\n" +
            "Enter the following parameters separated by spaces:\n" +
            "1. Command name (GetPerformanceOverTime)\n" +
            "2. Portfolio name\n" +
            "3. Start date in the format yyyy-mm-dd\n" +
            "4. End date in the format yyyy-mm-dd\n" +
            "\n" +
            "Load Portfolio Command: \n" +
            "This command loads an existing portfolio.\n" +
            "Enter the following parameters separated by spaces:\n" +
            "1. Command name (LoadPortfolio)\n" +
            "2. Portfolio name\n" +
            "\n" +
            "Enter 'menu' to see the instructions again.\n" +
            "Enter 'quit' to quit the program.\n" +
            "\n" +
            "Portfolio Created: TestPortfolio\n" +
            "Portfolio shares have been purchased\n" +
            "Portfolio shares have been purchased\n" +
            "GOOG: $66851.00\n" +
            "AMZN: $18478.40\n", output.toString());
  }

  @Test
  public void testControllerModelGetPerformanceOverTime() throws IOException {
    Appendable output = new StringBuilder();
    Readable input = new StringReader("CreatePortfolio TestPortfolio\n" +
            "BuyPortfolioHolding TestPortfolio GOOG 50 2020-01-01\n" +
            "BuyPortfolioHolding TestPortfolio AMZN 10 2020-05-01\n" +
            "GetPerformanceOverTime TestPortfolio 2020-01-01 2020-01-30\n");
    ITextController controller = new TextControllerImpl2(input, output);
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
            "This command determines which days are x-day crossovers for a given stock, " +
            "over a specified time period \n" +
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
            "Buy Portfolio Holding's on Date Command: \n" +
            "This command purchases shares to an existing portfolio on a specific date.\n" +
            "Enter the following parameters separated by spaces:\n" +
            "1. Command name (BuyPortfolioHolding)\n" +
            "2. Portfolio name\n" +
            "3. Stock ticker symbol\n" +
            "4. Quantity (number of shares)\n" +
            "5. Date in the format yyyy-mm-dd\n" +
            "\n" +
            "Sell Portfolio Holding's on Date Command: \n" +
            "This command sells shares of an existing portfolio on a specific date.\n" +
            "Enter the following parameters separated by spaces:\n" +
            "1. Command name (SellPortfolioHolding)\n" +
            "2. Portfolio name\n" +
            "3. Stock ticker symbol\n" +
            "4. Quantity (number of shares)\n" +
            "5. Date in the format yyyy-mm-dd\n" +
            "\n" +
            "Get Portfolio Value: \n" +
            "This command calculates the value of a portfolio on a given date.\n" +
            "Enter the following parameters separated by spaces:\n" +
            "1. Command name (GetPortfolioValue)\n" +
            "2. Portfolio name\n" +
            "3. Date in the format yyyy-mm-dd\n" +
            "\n" +
            "Get Portfolio Composition Command: \n" +
            "This command gets the composition of a portfolio (The stocks and the number " +
            "of shares in each stock) on a specific date.\n" +
            "Enter the following parameters separated by spaces:\n" +
            "1. Command name (GetPortfolioComposition)\n" +
            "2. Portfolio name\n" +
            "3. Date in the format yyyy-mm-dd\n" +
            "\n" +
            "Get Portfolio Distribution Command: \n" +
            "This command gets the distribution of a portfolio " +
            "(The stocks and each stock's value) on a specific date.\n" +
            "Enter the following parameters separated by spaces:\n" +
            "1. Command name (GetPortfolioDistribution)\n" +
            "2. Portfolio name\n" +
            "3. Date in the format yyyy-mm-dd\n" +
            "\n" +
            "Rebalance Portfolio: \n" +
            "This command rebalances the portfolio to the given stock weights.\n" +
            "Whatever stocks you give weight will be purchased and whatever stocks you do " +
            "not will be sold, regardless of the current portfolio holdings.\n" +
            "Enter the following parameters separated by spaces:\n" +
            "1. Command name (RebalancePortfolio)\n" +
            "2. Portfolio name\n" +
            "3. Date (yyyy-mm-dd)\n" +
            "4. Stock weights (stock1 weight1 stock2 weight2 ...)\n" +
            "\n" +
            "Get Portfolio Performance Over Time: \n" +
            "This command calculates the performance of a portfolio over a given " +
            "period of time.\n" +
            "Enter the following parameters separated by spaces:\n" +
            "1. Command name (GetPerformanceOverTime)\n" +
            "2. Portfolio name\n" +
            "3. Start date in the format yyyy-mm-dd\n" +
            "4. End date in the format yyyy-mm-dd\n" +
            "\n" +
            "Load Portfolio Command: \n" +
            "This command loads an existing portfolio.\n" +
            "Enter the following parameters separated by spaces:\n" +
            "1. Command name (LoadPortfolio)\n" +
            "2. Portfolio name\n" +
            "\n" +
            "Enter 'menu' to see the instructions again.\n" +
            "Enter 'quit' to quit the program.\n" +
            "\n" +
            "Portfolio Created: TestPortfolio\n" +
            "Portfolio shares have been purchased\n" +
            "Portfolio shares have been purchased\n" +
            "Performance of TestPortfolio from 2020-01-01 to 2020-01-30\n" +
            "\n" +
            "01 JAN 2020: *********************************\n" +
            "02 JAN 2020: **********************************\n" +
            "03 JAN 2020: **********************************\n" +
            "04 JAN 2020: **********************************\n" +
            "05 JAN 2020: **********************************\n" +
            "06 JAN 2020: ***********************************\n" +
            "07 JAN 2020: ***********************************\n" +
            "08 JAN 2020: ***********************************\n" +
            "09 JAN 2020: ***********************************\n" +
            "10 JAN 2020: ************************************\n" +
            "11 JAN 2020: ************************************\n" +
            "12 JAN 2020: ************************************\n" +
            "13 JAN 2020: ************************************\n" +
            "14 JAN 2020: ************************************\n" +
            "15 JAN 2020: ************************************\n" +
            "16 JAN 2020: ************************************\n" +
            "17 JAN 2020: *************************************\n" +
            "18 JAN 2020: *************************************\n" +
            "19 JAN 2020: *************************************\n" +
            "20 JAN 2020: *************************************\n" +
            "21 JAN 2020: *************************************\n" +
            "22 JAN 2020: *************************************\n" +
            "23 JAN 2020: *************************************\n" +
            "24 JAN 2020: *************************************\n" +
            "25 JAN 2020: *************************************\n" +
            "26 JAN 2020: *************************************\n" +
            "27 JAN 2020: ************************************\n" +
            "28 JAN 2020: ************************************\n" +
            "29 JAN 2020: ************************************\n" +
            "30 JAN 2020: ************************************\n" +
            "\n" +
            "Scale: * = $2000\n", output.toString());
  }

  @Test
  public void testRebalancePortfolioCommand() throws IOException {
    IModel2 model = new ModelImpl2();
    model.createPortfolio("TestPortfolio");
    model.buyPortfolioHolding("TestPortfolio", "GOOG", 10,
            LocalDate.of(2024, 6, 4));
    model.buyPortfolioHolding("TestPortfolio", "MSFT", 20,
            LocalDate.of(2024, 6, 4));
    model.buyPortfolioHolding("TestPortfolio", "AMZN", 30,
            LocalDate.of(2024, 6, 4));
    ITextController controller = new TextControllerImpl2(new StringReader("RebalancePortfolio " +
            "TestPortfolio" +
            " 2024-06-04 GOOG 10 MSFT 30 AMZN 60"),
            new StringBuilder());
    controller.control(model);

    assertEquals("GOOG: 8.82\n" +
            "MSFT: 11.14\n" +
            "AMZN: 51.70\n", model.getPortfolioComposition("TestPortfolio",
            LocalDate.of(2024, 6, 4)));
  }

  @Test
  public void testLoadPortfolio() throws IOException {
    IModel2 model = new ModelImpl2();
    model.createPortfolio("TestPortfolio");
    model.buyPortfolioHolding("TestPortfolio", "GOOG", 10,
            LocalDate.of(2024, 6, 4));
    model.buyPortfolioHolding("TestPortfolio", "MSFT", 20,
            LocalDate.of(2024, 6, 4));
    model.buyPortfolioHolding("TestPortfolio", "AMZN", 30,
            LocalDate.of(2024, 6, 4));
    ITextController controller = new TextControllerImpl2(new StringReader("LoadPortfolio TestPortfolio"),
            new StringBuilder());
    controller.control(model);

    assertEquals("GOOG: 10.00\n" +
            "MSFT: 20.00\n" +
            "AMZN: 30.00\n", model.getPortfolioComposition("TestPortfolio",
            LocalDate.of(2024, 6, 4)));
  }
}

