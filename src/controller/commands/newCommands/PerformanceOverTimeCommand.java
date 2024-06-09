package controller.commands.newCommands;

import java.util.Scanner;

import controller.commands.AWriterCommand;
import model.IModel;

public class PerformanceOverTimeCommand extends AWriterCommand {

  public PerformanceOverTimeCommand(Appendable out) {
    super(out);
  }

  @Override
  public void execute(IModel model, Scanner scanner) {

  }

  @Override
  public String getInstructions() {
    return null;
  }
}
