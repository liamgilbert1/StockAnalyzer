package controller;

import model.IModel;

public interface IController {
  /**
   * This method is used to start the controller.
   * @param model the model to be used
   */
  void go(IModel model);
}
