package controller;

import model.IModel;

/**
 * This interface represents a controller for the stock market simulator.
 */
public interface IController {
  /**
   * This method is used to start the controller.
   * @param model the model to be used
   */
  void go(IModel model);
}
