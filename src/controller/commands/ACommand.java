package controller.commands;

import java.util.Scanner;

/**
 * This class represents an abstract command that implements the ICommand interface. It provides
 * common functionality that is shared among all commands.
 * Has field out that is an Appendable object that gets outputted to the user.
 */
public abstract class ACommand implements ICommand {
  protected final Appendable out;

  /**
   * Constructs an ACommand object with the given Appendable object.
   * @param out the Appendable object that gets outputted to the user
   */
  public ACommand(Appendable out) {
    this.out = out;
  }

  /**
   * Gets the next string from the scanner.
   * @param scanner the scanner to get the next string from
   * @return the next string from the scanner
   * @throws IllegalArgumentException if the next string is empty
   */
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

  /**
   * Gets the next positive integer from the scanner.
   * @param scanner the scanner to get the next positive integer from
   * @return the next positive integer from the scanner
   * @throws IllegalArgumentException if the next integer is not positive
   */
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
