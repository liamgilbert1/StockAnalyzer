package controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.function.Supplier;

import controller.commands.AddPortfolioHoldingCommand;
import controller.commands.CreatePortfolioCommand;
import controller.commands.CrossoverCommand;
import controller.commands.GainOrLossCommand;
import controller.commands.GetPortfolioValueCommand;
import controller.commands.ICommand;
import controller.commands.MovingAverageCommand;
import model.IModel2;

/**
 * This class represents the controller for the stock market simulator.
 * It implements the IController interface.
 * The controller is responsible for taking in user input and executing the appropriate commands.
 * The controller has a map of commands that maps a command name to a Supplier of that command.
 * The controller has a Readable object that takes in user input.
 * The controller has an Appendable object that outputs to the user.
 * The controller has a list of ordered commands that are executed in order.
 */
public class ControllerImpl implements IController {
  protected final Map<String, Supplier<ICommand>> commandMap;
  protected final Readable input;
  protected List<String> orderedCommands;
  protected final Appendable output;

  /**
   * Constructs a ControllerImpl object with the given input and output.
   * @param input the input to be used
   * @param output the output to be used
   */
  public ControllerImpl(Readable input, Appendable output) {
    this.input = input;
    this.output = output;
    commandMap = new HashMap<>();
    commandMap.put("GainOrLoss", () -> new GainOrLossCommand(output));
    commandMap.put("MovingAverage", () -> new MovingAverageCommand(output));
    commandMap.put("Crossover", () -> new CrossoverCommand(output));
    commandMap.put("CreatePortfolio", () -> new CreatePortfolioCommand(output));
    commandMap.put("AddPortfolioHolding", () -> new AddPortfolioHoldingCommand(output));
    commandMap.put("GetPortfolioValue", () -> new GetPortfolioValueCommand(output));
    this.orderedCommands = List.of("GainOrLoss", "MovingAverage", "Crossover", "CreatePortfolio",
            "AddPortfolioHolding", "GetPortfolioValue");
  }

  /**
   * Executes the controller with the given model.
   * @param model the model to execute the controller on
   */
  @Override
  public void control(IModel2 model) {
    printInstructions();
    Scanner scanner = new Scanner(input);
    while (scanner.hasNext()) {
      String command = scanner.next();
      if (command != null) {
        if (command.equals("quit")) {
          return;
        }
        if (command.equals("menu")) {
          printInstructions();
          continue;
        }
        try {
          ICommand commandToRun = this.commandMap.get(command).get();
          commandToRun.execute(model, scanner);
        } catch (Exception e) {
          try {
            output.append("\nAn error occurred while executing the command. Please try again.");
          } catch (IOException ioException) {
            throw new IllegalStateException("Could not append to output.");
          }
        }
      }
    }
  }

  /**
   * Prints the instructions for each command.
   */
  private void printInstructions() {
    for (String command : this.orderedCommands) {
      try {
        output.append("\n");
        output.append(commandMap.get(command).get().getInstructions());
      } catch (IOException e) {
        throw new IllegalStateException("Could not append to output.");
      }
    }
    try {
      output.append("\n");
      Appendable menuCommand = new StringBuilder();
      menuCommand.append("Enter 'menu' to see the instructions again.");
      output.append(menuCommand.toString());
      output.append("\n");
      Appendable quitCommand = new StringBuilder();
      quitCommand.append("Enter 'quit' to quit the program.");
      output.append(quitCommand.toString());
      output.append("\n");
      output.append("\n");
    } catch (IOException e) {
      throw new IllegalStateException("Could not append to output.");
    }
  }
}
