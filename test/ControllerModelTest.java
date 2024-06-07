import org.junit.Test;

import java.io.StringReader;

import controller.ControllerImpl;
import controller.IController;
import model.ModelImpl;

import static org.junit.Assert.assertEquals;

public class ControllerModelTest {

  /**
   * Test controller's command GainOrLoss through ModelImpl
   */
  @Test
  public void testControllerModelGainOrLoss() {
    Appendable output = new StringBuilder();
    Readable input = new StringReader("GainOrLoss GOOG 2024-06-05 2024-06-04\n");
    IController controller = new ControllerImpl(input, output);
    controller.go(new ModelImpl());
    assertEquals("\n" +
            "Moving Average: \n" +
            "This command calculates the moving average of a stock over a given period of time.\n" +
            "Enter the following parameters separated by spaces:\n" +
            "1. Command name (MovingAverage)\n" +
            "2. Stock ticker symbol\n" +
            "3. Ending date in the format yyyy-mm-dd\n" +
            "4. Number of days to calculate the moving average over\n" +
            "\n" +
            "Create Portfolio: \n" +
            "This command creates a new portfolio for the user.\n" +
            "Enter the following parameters separated by spaces:\n" +
            "1. Command name (CreatePortfolio)\n" +
            "2. Portfolio name\n" +
            "\n" +
            "Gain or Loss: \n" +
            "This command calculates the gain or loss of a stock over a given period of time.\n" +
            "Enter the following parameters separated by spaces:\n" +
            "1. Command name (GainOrLoss)\n" +
            "2. Stock ticker symbol\n" +
            "3. Starting date in the format yyyy-mm-dd\n" +
            "4. Ending date in the format yyyy-mm-dd\n" +
            "\n" +
            "Crossovers: \n" +
            "This command determines which days are x-day crossovers for a given stock, over a specified time period \n" +
            "Enter the following parameters separated by spaces:\n" +
            "1. Command name (Crossover)\n" +
            "2. Stock ticker symbol\n" +
            "3. Starting date in the format yyyy-mm-dd\n" +
            "4. Ending date in the format yyyy-mm-dd\n" +
            "5. The number of days to check the crossover over (x)\n" +
            "Gain or Loss: -1.94\n", output.toString());
  }

}
