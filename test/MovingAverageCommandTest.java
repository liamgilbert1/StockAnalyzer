import org.junit.Test;

import java.io.StringReader;
import java.util.Scanner;

import controller.commands.GainOrLossCommand;
import controller.commands.ICommand;
import controller.commands.MovingAverageCommand;
import model.ModelImpl;

import static org.junit.Assert.assertEquals;

public class MovingAverageCommandTest {

  @Test
  public void testValidTickerAndDateRange() {
    Readable input = new StringReader("GOOG 2023-01-01 30\n");
    Appendable output = new StringBuilder();
    ICommand command = new MovingAverageCommand(output);
    command.execute(new ModelImpl(), new Scanner(input));
    assertEquals("Moving average is: 94.21\n", output.toString());
  }

  @Test
  public void testValidTickerAndDateRange2() {
    Readable input = new StringReader("MSFT 2023-02-01 5\n");
    Appendable output = new StringBuilder();
    ICommand command = new MovingAverageCommand(output);
    command.execute(new ModelImpl(), new Scanner(input));
    assertEquals("Moving average is: 247.89\n", output.toString());
  }

  @Test
  public void testValidTickerAndDateRange3() {
    Readable input = new StringReader("AMZN 2023-03-01 15\n");
    Appendable output = new StringBuilder();
    ICommand command = new MovingAverageCommand(output);
    command.execute(new ModelImpl(), new Scanner(input));
    assertEquals("Moving average is: 96.77\n", output.toString());
  }

  @Test
  public void testInvalidTicker() {
    Readable input = new StringReader("INVALID 2023-01-01 30\n");
    Appendable output = new StringBuilder();
    ICommand command = new MovingAverageCommand(output);
    try {
      command.execute(new ModelImpl(), new Scanner(input));
    } catch (Exception e) {
      assertEquals("Could not read from file", e.getMessage());
    }
  }

  @Test
  public void testInvalidDays() {
    Readable input = new StringReader("GOOG 2023-01-01 0\n");
    Appendable output = new StringBuilder();
    ICommand command = new MovingAverageCommand(output);
    try {
      command.execute(new ModelImpl(), new Scanner(input));
    } catch (Exception e) {
      assertEquals("Days must be greater than 0", e.getMessage());
    }
  }

}