package controller.textcontroller;

import java.io.IOException;

import model.IModel2;

/**
 * This interface represents a controller for the stock market simulator.
 * The controller takes in user input and sends it to the model to execute commands.
 */
public interface ITextController {
  /**
   * This method is used to start the controller.
   * (Changed original method signature to take in an IModel2 instead of an IModel
   * to allow the new model methods to be used in the controller, along with the old ones.)
   *
   * @param model the model to be used
   */
  void control(IModel2 model) throws IOException;
}
