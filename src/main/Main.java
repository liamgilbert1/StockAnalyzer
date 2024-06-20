package main;

import java.io.InputStreamReader;

import controller.ControllerImpl2;
import controller.GUIController;
import controller.IGUIController;
import model.IModel2;

import controller.IController;
import model.ModelImpl2;
import view.GUIView;
import view.IGUIView;
import view.ITextView;
import view.ViewImpl;

/**
 * Main class to run the user interface. This class creates a model and controller and runs the
 * controller with the model. The controller takes in user input and sends it to the model to
 * execute commands.
 */
public class Main {
  /**
   * main.Main method to run the user interface.
   * @param args the arguments to run the user interface.
   */
  public static void main(String[] args) throws NoSuchMethodException {
    if (args.length == 0) {
      launchGUIView();
    }
    else if (args[0].equals("-text")) {
      launchTextView();
    }
  }

  private static void launchTextView() {
    ITextView view = new ViewImpl(System.out);
    IModel2 model = new ModelImpl2();
    Readable input = new InputStreamReader(System.in);
    IController controller = new ControllerImpl2(input, view);
    controller.control(model);
  }

  private static void launchGUIView() throws NoSuchMethodException {
    IGUIView view = new GUIView();
    IModel2 model = new ModelImpl2();
    new GUIController(model, view);
  }
}
