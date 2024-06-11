package controller;

import model.IModel;
import model.IModel2;

/**
 * This interface represents a controller for the stock market simulator.
 */
public interface IController {
  /**
   * This method is used to start the controller.
   * (Changed from original method signature to take in an IModel2 instead of an IModel)
   * @param model the model to be used
   */
  void control(IModel2 model);
}
