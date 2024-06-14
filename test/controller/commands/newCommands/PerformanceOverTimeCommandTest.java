package controller.commands.newCommands;

import org.junit.Before;
import org.junit.Test;

import java.io.StringReader;
import java.util.Scanner;

import controller.commands.ICommand;
import model.IModel2;
import model.ModelImpl2;

import static org.junit.Assert.assertEquals;

public class PerformanceOverTimeCommandTest {
  ICommand command;
  Appendable output;
  IModel2 model;
  Scanner scanner;

  Readable input;

  @Before
  public void setUp() {
    model = new ModelImpl2();
    output = new StringBuilder();
    command = new PerformanceOverTimeCommand(output);
    model.createPortfolio("myPortfolio");
    model.buyPortfolioHolding("myPortfolio", "GOOG", 5,
            java.time.LocalDate.of(2024, 6, 5));
    model.buyPortfolioHolding("myPortfolio", "MSFT", 10,
            java.time.LocalDate.of(2024, 6, 5));
  }

  @Test
  public void execute() {
    input = new StringReader("myPortfolio 2024-06-05 2024-06-12");
    scanner = new Scanner(input);
    command.execute(model, scanner);
    assertEquals("Performance of myPortfolio from 2024-06-05 to 2024-06-12\n" +
            "\n" +
            "05 JUN 2024: *****\n" +
            "06 JUN 2024: *****\n" +
            "07 JUN 2024: *****\n" +
            "08 JUN 2024: *****\n" +
            "09 JUN 2024: *****\n" +
            "10 JUN 2024: *****\n" +
            "11 JUN 2024: *****\n" +
            "12 JUN 2024: *****\n" +
            "\n" +
            "Scale: * = 1000\n", output.toString());
  }

  @Test
  public void getInstructions() {
    String instructions = command.getInstructions();
    assertEquals("Get Portfolio Performance Over Time: \n" +
            "This command calculates the performance of a portfolio over a given period of time.\n" +
            "Enter the following parameters separated by spaces:\n" +
            "1. Command name (GetPerformanceOverTime)\n" +
            "2. Portfolio name\n" +
            "3. Start date in the format yyyy-mm-dd\n" +
            "4. End date in the format yyyy-mm-dd\n", instructions);
  }
}
