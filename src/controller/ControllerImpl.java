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
import model.IModel;

public class ControllerImpl implements IController {
  private final Map<String, Supplier<ICommand>> commandMap;
  private final Readable input;
  private final List<String> orderedCommands;
  private final Appendable output;

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
   * This method is used to start the controller.
   * @param model the model to be used
   */
  @Override
  public void go(IModel model) {
    printInstructions();
    Scanner scanner = new Scanner(input);
    while(scanner.hasNext()) {
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
