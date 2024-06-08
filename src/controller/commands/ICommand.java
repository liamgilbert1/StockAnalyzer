package controller.commands;

import java.util.Scanner;
import model.IModel;

/**
 * This interface represents a command that can be executed by the controller.
 */
public interface ICommand {

  /**
   * Executes the command.
   *
   * @param model   the model to execute the command on
   * @param scanner the scanner to read input from
   */
  void execute(IModel model, Scanner scanner);

  /**
   * Gets the instructions for the command.
   *
   * @return the instructions for the command
   */
  String getInstructions();
}
