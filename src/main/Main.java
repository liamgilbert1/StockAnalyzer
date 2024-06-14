package main;

import java.io.InputStreamReader;

import controller.ControllerImpl2;
import model.IModel2;

import controller.IController;
import model.ModelImpl2;
import view.IView;
import view.ViewImpl;

/**
 * main.Main class to run the user interface. This class creates a model and controller and runs the
 * controller with the model. The controller takes in user input and sends it to the model to
 * execute commands.
 */
public class Main {
  /**
   * main.Main method to run the user interface.
   * @param args the arguments to run the user interface.
   */
  public static void main(String[] args) {
    IView view = new ViewImpl(System.out);
    IModel2 model = new ModelImpl2();
    Readable input = new InputStreamReader(System.in);
    IController controller = new ControllerImpl2(input, view);
    controller.control(model);
  }
}
