import java.io.InputStreamReader;
import model.IModel;
import model.ModelImpl;
import controller.ControllerImpl;

import controller.IController;

public class Main {
  public static void main(String[] args) {
    IModel model = new ModelImpl();
    Readable input = new InputStreamReader(System.in);
    Appendable output = System.out;
    IController controller = new ControllerImpl(input, output);
    controller.go(model);
  }
}
