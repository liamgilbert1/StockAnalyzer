import org.junit.Test;

import java.time.LocalDate;

import model.IModel;
import model.ModelImpl;

import static org.junit.Assert.assertEquals;

public class ModelTests {

  @Test
  public void testModelGainOrLoss() {
    LocalDate startDate = LocalDate.of(2024, 6, 5);
    IModel model = new ModelImpl();
    model.calculateGainOrLoss("GOOG", startDate, LocalDate.of(2024, 6, 4));
  }

  @Test
  public void testModelMovingAverage() {
    IModel model = new ModelImpl();
    assertEquals(175.145, model.movingAverage("GOOG", LocalDate.of(2024,
            6, 5), 4), 0.01);

  }
}
