package controller.commands;
import java.util.Scanner;
import model.IModel;

/**
 * This interface represents a command that can be executed by the controller.
 */
public interface ICommand {

  void execute(IModel model, Scanner scanner);

  String getInstructions();
}
