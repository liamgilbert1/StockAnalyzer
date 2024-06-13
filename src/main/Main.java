package main;

import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;

import controller.ControllerImpl2;
import model.IModel;
import model.IModel2;
import model.ModelImpl;
import controller.ControllerImpl;

import controller.IController;
import model.ModelImpl2;

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
    IModel2 model = new ModelImpl2();
    Readable input = new InputStreamReader(System.in);
    Appendable output = System.out;
    IController controller = new ControllerImpl2(input, output);
    controller.control(model);
  }
}
