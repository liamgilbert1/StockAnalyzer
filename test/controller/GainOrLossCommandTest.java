package controller;

import org.junit.Test;

import java.io.StringReader;
import java.util.Scanner;

import controller.commands.GainOrLossCommand;
import controller.commands.ICommand;
import model.ModelImpl;
import model.ModelImpl2;

import static org.junit.Assert.assertEquals;

/**
 * This class tests the GainOrLossCommand class.
 */
public class GainOrLossCommandTest {

  /**
   * Test valid input with valid stock ticker and date range.
   * Test case 1: GOOG, start date: 2023-01-01, end date: 2023-01-31.
   * Expected result: Successfully calculate gain or loss for GOOG.
   */
  @Test
  public void testValidTickerAndDateRange() {
    Readable input = new StringReader("GOOG 2023-01-01 2023-01-31\n");
    Appendable output = new StringBuilder();
    ICommand command = new GainOrLossCommand(output);
    command.execute(new ModelImpl2(), new Scanner(input));
    assertEquals("Gain or Loss: 11.14\n", output.toString());
  }

  /**
   * Test valid input with another valid stock ticker and date range.
   * Test case 2: MSFT, start date: 2023-02-01, end date: 2023-02-28.
   * Expected result: Successfully calculate gain or loss for MSFT.
   */
  @Test
  public void testValidTickerAndDateRange2() {
    Readable input = new StringReader("MSFT 2023-02-01 2023-02-28\n");
    Appendable output = new StringBuilder();
    ICommand command = new GainOrLossCommand(output);
    command.execute(new ModelImpl2(), new Scanner(input));
    assertEquals("Gain or Loss: -3.33\n", output.toString());
  }

  /**
   * Test valid input with a third valid stock ticker and date range.
   * Test case 3: AMZN, start date: 2023-03-01, end date: 2023-03-31.
   * Expected result: Successfully calculate gain or loss for AMZN.
   */
  @Test
  public void testValidTickerAndDateRange3() {
    Readable input = new StringReader("AMZN 2023-03-01 2023-03-31\n");
    Appendable output = new StringBuilder();
    ICommand command = new GainOrLossCommand(output);
    command.execute(new ModelImpl2(), new Scanner(input));
    assertEquals("Gain or Loss: 11.12\n", output.toString());
  }

  /**
   * Test invalid stock ticker.
   * Test case 4: INVALID, start date: 2023-01-01, end date: 2023-01-31.
   * Expected result: Model throws an exception indicating invalid ticker symbol.
   */
  @Test
  public void testInvalidTicker() {
    Readable input = new StringReader("INVALID 2023-01-01 2023-01-31\n");
    Appendable output = new StringBuilder();
    ICommand command = new GainOrLossCommand(output);
    try {
      command.execute(new ModelImpl2(), new Scanner(input));
    } catch (Exception e) {
      assertEquals("Most recent date not found", e.getMessage());
    }
  }

  /**
   * Test valid stock ticker with start date later than end date.
   * Test case 5: GOOG, start date: 2023-01-31, end date: 2023-01-01.
   * Expected result: Model throws an exception indicating invalid date range.
   */
  @Test
  public void testStartDateAfterEndDate() {
    Readable input = new StringReader("GOOG 2023-01-31 2023-01-01\n");
    Appendable output = new StringBuilder();
    ICommand command = new GainOrLossCommand(output);
    try {
      command.execute(new ModelImpl2(), new Scanner(input));
    } catch (Exception e) {
      assertEquals("Could not read from file", e.getMessage());
    }
  }

  /**
   * Test valid stock ticker with start date equals end date.
   * Test case 6: MSFT, start date: 2023-01-15, end date: 2023-01-15.
   * Expected result: Successfully calculate gain or loss, which should be zero or very minimal.
   */
  @Test
  public void testStartDateEqualsEndDate() {
    Readable input = new StringReader("MSFT 2023-01-15 2023-01-15\n");
    Appendable output = new StringBuilder();
    ICommand command = new GainOrLossCommand(output);
    command.execute(new ModelImpl2(), new Scanner(input));
    assertEquals("Gain or Loss: 0.00\n", output.toString());
  }

  /**
   * Test valid stock ticker with missing start date.
   * Test case 9: GOOG, start date: , end date: 2023-01-31.
   * Expected result: Command throws an exception indicating missing start date.
   */
  @Test
  public void testMissingStartDate() {
    Readable input = new StringReader("GOOG  2023-01-31\n");
    Appendable output = new StringBuilder();
    ICommand command = new GainOrLossCommand(output);
    try {
      command.execute(new ModelImpl2(), new Scanner(input));
    } catch (Exception e) {
      assertEquals("Command input instructions not followed. Please try again",
              e.getMessage());
    }
  }

  /**
   * Test valid stock ticker with missing end date.
   * Test case 10: GOOG, start date: 2023-01-01, end date: .
   * Expected result: Command throws an exception indicating missing end date.
   */
  @Test
  public void testMissingEndDate() {
    Readable input = new StringReader("GOOG 2023-01-01 \n");
    Appendable output = new StringBuilder();
    ICommand command = new GainOrLossCommand(output);
    try {
      command.execute(new ModelImpl2(), new Scanner(input));
    } catch (Exception e) {
      assertEquals("Command input instructions not followed. Please try again",
              e.getMessage());
    }
  }

  /**
   * Test valid stock ticker with non-existent date.
   * Test case 12: MSFT, start date: 2023-02-30, end date: 2023-03-05.
   * Expected result: Command throws an exception indicating invalid date.
   */
  @Test
  public void testNonExistentDate() {
    Readable input = new StringReader("MSFT 2023-02-30 2023-03-05\n");
    Appendable output = new StringBuilder();
    ICommand command = new GainOrLossCommand(output);
    try {
      command.execute(new ModelImpl2(), new Scanner(input));
    } catch (Exception e) {
      assertEquals("Text '2023-02-30' could not be parsed: Invalid date 'FEBRUARY 30'",
              e.getMessage());
    }
  }

  @Test
  public void testGetInstructions() {
    Appendable output = new StringBuilder();
    ICommand command = new GainOrLossCommand(output);
    assertEquals("Gain or Loss: \n" +
            "This command calculates the gain or loss of a stock over a given period of time.\n" +
            "Enter the following parameters separated by spaces:\n" +
            "1. Command name (GainOrLoss)\n" +
            "2. Stock ticker symbol\n" +
            "3. Starting date in the format yyyy-mm-dd\n" +
            "4. Ending date in the format yyyy-mm-dd\n", command.getInstructions());
  }

}
