import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;

import model.portfolio.Holding;
import model.portfolio.IHolding;
import model.stock.IStock;
import model.stock.Stock;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * This class tests the Holding class.
 */
public class HoldingTest {

  IHolding holding1;
  IStock stock1;
  LocalDate date1;
  LocalDate date2;
  LocalDate date3;

  @Before
  public void setUp() {
    date1 = LocalDate.of(2018, 1, 1);
    date2 = LocalDate.of(2018, 1, 2);
    date3 = LocalDate.of(2018, 1, 3);
    stock1 = new Stock("GOOG");
    holding1 = new Holding(stock1, 1000);
  }

  @Test
  public void testGetStock() {
    assertEquals(stock1, holding1.getStock());
  }

  @Test
  public void testGetQuantity() {
    assertEquals(1000, holding1.getQuantity(), .01);
  }

  @Test
  public void testGetValue() {
    assertEquals(1046400,
            holding1.getValue(LocalDate.of(2018, 1, 1)), 0.01);
    assertEquals(1065000,
            holding1.getValue(LocalDate.of(2018, 1, 2)), 0.01);
    assertEquals(1082480,
            holding1.getValue(LocalDate.of(2018, 1, 3)), 0.01);
  }

  @Test
  public void testAddQuantity() {
    IHolding newHolding = holding1.addQuantity(1000);
    assertEquals(2000, newHolding.getQuantity(), .01);
  }

  @Test
  public void testEquals() {
    IHolding holding1Clone = new Holding(stock1, 1000);
    assertEquals(holding1, holding1Clone);
    holding1Clone = holding1Clone.addQuantity(1000);
    assertNotEquals(holding1, holding1Clone);
  }

  @Test
  public void testHashCode() {
    IHolding holding1Clone = new Holding(stock1, 1000);
    assertEquals(holding1.hashCode(), holding1Clone.hashCode());
  }
}