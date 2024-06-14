package controller.commands;

import java.util.Scanner;
import model.IModel;
import model.IModel2;

/**
 * This interface represents a command that can be executed by the controller.
 * The command can be executed on the model and can take in user input from the scanner.
 */
public interface ICommand {

  /**
   * Executes the command.
   * (Changed from original method signature to take in an IModel2 instead of an IModel
   * to allow the new model methods to be used with the new commands, along with the old ones.)
   * @param model   the model to execute the command on
   * @param scanner the scanner to read input from
   */
  void execute(IModel2 model, Scanner scanner);

  /**
   * Gets the instructions for the command.
   *
   * @return the instructions for the command
   */
  String getInstructions();
}
