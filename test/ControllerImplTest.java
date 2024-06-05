import org.junit.Test;

import java.io.StringReader;

import controller.ControllerImpl;
import controller.IController;
import model.IModel;
import model.MockModelImpl;

import static org.junit.Assert.assertEquals;

public class ControllerImplTest {

  @Test
  public void testControllerMovingAverage() {
    StringBuilder log = new StringBuilder();
    IModel mockModel = new MockModelImpl(log);

    Readable readable = new StringReader("MovingAverage GOOG 12");
    IController controller = new ControllerImpl(readable);
    controller.go(mockModel);
    assertEquals("MovingAverage GOOG 12", log.toString());
  }

}
