import java.io.InputStreamReader;
import model.IModel;
import model.ModelImpl;
import controller.ControllerImpl;

import controller.IController;

/**
 * Main class to run the user interface. This class creates a model and controller and runs the
 * controller with the model. The controller takes in user input and sends it to the model to
 * execute commands.
 */
public class Main {
  /**
   * Main method to run the user interface.
   * @param args the arguments to run the user interface.
   */
  public static void main(String[] args) {
    IModel model = new ModelImpl();
    Readable input = new InputStreamReader(System.in);
    Appendable output = System.out;
    IController controller = new ControllerImpl(input, output);
    controller.control(model);
  }
}
