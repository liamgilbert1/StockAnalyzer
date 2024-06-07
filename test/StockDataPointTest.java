import org.junit.Test;

import static model.stock.StockDataPoint.CLOSE;
import static model.stock.StockDataPoint.DATE;
import static model.stock.StockDataPoint.HIGH;
import static model.stock.StockDataPoint.LOW;
import static model.stock.StockDataPoint.OPEN;
import static model.stock.StockDataPoint.VOLUME;
import static org.junit.Assert.*;

public class StockDataPointTest {
  @Test
  public void getIndex() {
    assertEquals(0, DATE.getIndex());
    assertEquals(1, OPEN.getIndex());
    assertEquals(2, HIGH.getIndex());
    assertEquals(3, LOW.getIndex());
    assertEquals(4, CLOSE.getIndex());
    assertEquals(5, VOLUME.getIndex());
  }
}