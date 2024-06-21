package main;

import java.io.IOException;
import java.io.InputStreamReader;

import controller.textcontroller.TextControllerImpl2;
import controller.guicontroller.GUIController;
import model.IModel2;

import controller.textcontroller.ITextController;
import model.ModelImpl2;
import view.guiview.GUIView;
import view.guiview.IGUIView;
import view.IView;
import view.textview.TextViewImpl;

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
  public static void main(String[] args) throws IOException {
    if (args.length == 0) {
      launchGUIView();
    }
    else if (args[0].equals("-text")) {
      launchTextView();
    }
  }

  private static void launchTextView() throws IOException {
    IView view = new TextViewImpl(System.out);
    IModel2 model = new ModelImpl2();
    Readable input = new InputStreamReader(System.in);
    ITextController controller = new TextControllerImpl2(input, view);
    controller.control(model);
  }

  private static void launchGUIView() {
    IGUIView view = new GUIView();
    IModel2 model = new ModelImpl2();
    new GUIController(model, view);
    view.setVisible(true);
  }
}
