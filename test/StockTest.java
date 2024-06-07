import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;

import model.stock.IStock;
import model.stock.Stock;

import static controller.StockDataPoint.CLOSE;
import static org.junit.Assert.*;

public class StockTest {

  IStock stock1;
  IStock stock1Clone;
  IStock stock2;
  LocalDate date1;
  LocalDate date2;
  LocalDate date3;


  @Before
  public void setUp() {
    date1 = LocalDate.of(2024, 6, 2);
    date2 = LocalDate.of(2024, 6, 3);
    date3 = LocalDate.of(2024, 6, 4);
    stock1 = new Stock("GOOG");
    stock1Clone = new Stock("GOOG");
  }

  @Test
  public void getClosePrice() {
    assertEquals(173.96, stock1.getClosePrice(date1), 0.01);
    assertEquals(174.42, stock1.getClosePrice(date2), 0.01);
    assertEquals(175.13, stock1.getClosePrice(date3), 0.01);
  }

  @Test
  public void getTicker() {
    assertEquals("GOOG", stock1.getTicker());
    assertEquals("MSFT", stock2.getTicker());
  }

  @Test
  public void equals() {

  }

  @Test
  public void hashCodeTest() {

  }
}