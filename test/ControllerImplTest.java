import org.junit.Test;

import java.io.StringReader;

import controller.ControllerImpl;
import controller.IController;
import model.IModel;
import model.MockModelImpl;

import static org.junit.Assert.assertEquals;

public class ControllerImplTest {

  @Test
  public void testControllerGainOrLoss() {
    StringBuilder log = new StringBuilder();
    IModel mockModel = new MockModelImpl(log);

    Readable readable = new StringReader("GainOrLoss GOOG 2018-11-01 2018-11-30");
    IController controller = new ControllerImpl(readable);
    controller.go(mockModel);
    assertEquals("GainOrLoss GOOG 2018-11-01 2018-11-30", log.toString());
  }

  @Test
  public void testControllerMovingAverage() {
    StringBuilder log = new StringBuilder();
    IModel mockModel = new MockModelImpl(log);

    Readable readable = new StringReader("MovingAverage GOOG 2020-10-05 12");
    IController controller = new ControllerImpl(readable);
    controller.go(mockModel);
    assertEquals("MovingAverage GOOG 2020-10-05 12", log.toString());
  }

  @Test
  public void testControllerCrossover() {
    StringBuilder log = new StringBuilder();
    IModel mockModel = new MockModelImpl(log);

    Readable readable = new StringReader("Crossover GOOG 2020-10-05 12");
    IController controller = new ControllerImpl(readable);
    controller.go(mockModel);
    assertEquals("Crossover GOOG 2020-10-05 12", log.toString());
  }

}
