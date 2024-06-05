package controller;

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

  public ControllerImpl(Readable input) {
    this.commandMap = new HashMap<>();
    this.commandMap.put("GainOrLoss", () -> new GainOrLossCommand(System.out));
    this.commandMap.put("MovingAverage", () -> new MovingAverageCommand(System.out));
    this.commandMap.put("Crossover", () -> new CrossoverCommand(System.out));
    this.input = input;
  }

  /**
   * This method is used to start the controller.
   * @param model the model to be used
   */
  @Override
  public void go(IModel model) {
    Scanner scanner = new Scanner(input);

    while(scanner.hasNext()) {
      String command = scanner.next();
      ICommand commandToRun = this.commandMap.get(command).get();
      if (command != null) {
        commandToRun.execute(model, scanner);
      }
    }
  }
}
