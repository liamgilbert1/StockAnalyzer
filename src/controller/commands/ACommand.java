package controller.commands;

import java.util.Scanner;

public abstract class ACommand implements ICommand {
  protected String getNextString(Scanner scanner) {
    String nextString = "";
    if (scanner.hasNext()) {
      nextString = scanner.next();
    }
    if (nextString.isEmpty()) {
      throw new IllegalArgumentException("Command input instructions not followed. " +
              "Please try again");
    }
    return nextString;
  }

  protected int getPositiveInt(Scanner scanner) {
    int nextInt = 0;
    if (scanner.hasNextInt()) {
      nextInt = scanner.nextInt();
    }
    if (nextInt <= 0) {
      throw new IllegalArgumentException("Days must be greater than 0");
    }
    return nextInt;
  }
}
