package controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.function.Supplier;

import controller.commands.ICommand;
import model.IModel2;

/**
 * This class represents a controller for the stock market simulator.
 * The controller is responsible for taking in user input and executing the appropriate commands.
 */
public class AController {
  protected final Map<String, Supplier<ICommand>> commandMap;

  public AController() {
    commandMap = new HashMap<>();
  }

  protected void addCommand(String commandName, Supplier<ICommand> command) {
    commandMap.put(commandName, command);
  }

  protected void removeCommand(String commandName) {
    commandMap.remove(commandName);
  }

  protected void runCommand(String command, IModel2 model, Scanner scanner,
                            Appendable output) throws IOException {
    try {
      ICommand commandToRun = this.commandMap.get(command).get();
      commandToRun.execute(model, scanner);
    } catch (Exception e) {
      String message = "Failed to process " + command + " command.\n"
              + "Please check the inputs and try again.";
      output.append(message);
    }
  }
}
