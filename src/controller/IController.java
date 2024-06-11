package controller;

import model.IModel;
import model.IModel2;

/**
 * This interface represents a controller for the stock market simulator.
 */
public interface IController {
  /**
   * This method is used to start the controller.
   * (Changed original method signature to take in an IModel2 instead of an IModel
   * to allow the new model methods to be used in the controller, along with the old ones.)
   *
   * @param model the model to be used
   */
  void control(IModel2 model);
}
