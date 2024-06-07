package controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.function.Supplier;

import controller.commands.CrossoverCommand;
import controller.commands.GainOrLossCommand;
import controller.commands.ICommand;
import controller.commands.MovingAverageCommand;
import model.IModel;

public class ControllerImpl implements IController {
  private final Map<String, Supplier<ICommand>> commandMap;
  private final Readable input;

  private final Appendable output;

  public ControllerImpl(Readable input, Appendable output) {
    this.input = input;
    this.output = output;
    this.commandMap = new HashMap<>();
    this.commandMap.put("GainOrLoss", () -> new GainOrLossCommand(output));
    this.commandMap.put("MovingAverage", () -> new MovingAverageCommand(output));
    this.commandMap.put("Crossover", () -> new CrossoverCommand(output));
  }

  /**
   * This method is used to start the controller.
   * @param model the model to be used
   */
  @Override
  public void go(IModel model) {
    for (String command : this.commandMap.keySet()) {
      try {
        output.append("\n");
        output.append(commandMap.get(command).get().getInstructions());
      } catch (IOException e) {
        throw new IllegalStateException("Could not append to output.");
      }
    }
    Scanner scanner = new Scanner(input);
    while(scanner.hasNext()) {
      String command = scanner.next();
      if (command != null) {
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
}
