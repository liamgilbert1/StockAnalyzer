import org.junit.Test;

import java.io.StringReader;
import java.util.Scanner;

import controller.commands.CrossoverCommand;
import controller.commands.ICommand;
import model.ModelImpl;

import static org.junit.Assert.assertEquals;

/**
 * Test cases for CrossoverCommand.
 */
public class CrossoverCommandTest {

  @Test
  public void testValidTickerAndDateRange() {
    Readable input = new StringReader("GOOG 2023-01-01 2023-01-31 10\n");
    Appendable output = new StringBuilder();
    ICommand command = new CrossoverCommand(output);
    command.execute(new ModelImpl(), new Scanner(input));
    assertEquals("Number of crossovers: 17\n" +
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
  public void testValidTickerAndDateRange2() {
    Readable input = new StringReader("MSFT 2023-02-01 2023-02-28 5\n");
    Appendable output = new StringBuilder();
    ICommand command = new CrossoverCommand(output);
    command.execute(new ModelImpl(), new Scanner(input));
    assertEquals("Number of crossovers: 10\n" +
            "2023-02-15\n" +
            "2023-02-14\n" +
            "2023-02-13\n" +
            "2023-02-09\n" +
            "2023-02-08\n" +
            "2023-02-07\n" +
            "2023-02-06\n" +
            "2023-02-03\n" +
            "2023-02-02\n" +
            "2023-02-01\n", output.toString());
  }

  @Test
  public void testInvalidTicker() {
    Readable input = new StringReader("INVALID 2023-01-01 2023-01-31 10\n");
    Appendable output = new StringBuilder();
    ICommand command = new CrossoverCommand(output);
    try {
      command.execute(new ModelImpl(), new Scanner(input));
    } catch (Exception e) {
      assertEquals("Could not read from file", e.getMessage());
    }
  }

  @Test
  public void testInvalidDays() {
    Readable input = new StringReader("GOOG 2023-01-01 2023-01-31 0\n");
    Appendable output = new StringBuilder();
    ICommand command = new CrossoverCommand(output);
    try {
      command.execute(new ModelImpl(), new Scanner(input));
    } catch (Exception e) {
      assertEquals("Days must be greater than 0", e.getMessage());
    }
  }

  @Test
  public void testGetInstructions() {
    Appendable output = new StringBuilder();
    ICommand command = new CrossoverCommand(output);
    assertEquals("Crossovers: \n" +
            "This command determines which days are x-day crossovers for a given stock, over a " +
            "specified time period \n" +
            "Enter the following parameters separated by spaces:\n" +
            "1. Command name (Crossover)\n" +
            "2. Stock ticker symbol\n" +
            "3. Starting date in the format yyyy-mm-dd\n" +
            "4. Ending date in the format yyyy-mm-dd\n" +
            "5. The number of days to check the crossover over (x)\n", command.getInstructions());
  }
}