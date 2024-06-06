import org.junit.Before;
import org.junit.Test;


import model.stock.IStockData;
import model.stock.StockData;

import static org.junit.Assert.*;

public class StockDataTest {

  IStockData day1;
  IStockData day2;
  IStockData day3;

  @Before
  public void setUp() {
    day1 = new StockData(100, 110, 90, 105, 1000000);
    day2 = new StockData(105, 115, 95, 110, 1000000);
    day3 = new StockData(110, 120, 100, 115, 1000000);
  }

  @Test
  public void getOpen() {
    assertEquals(100, day1.getOpen(), .0001);
    assertEquals(105, day2.getOpen(), .0001);
    assertEquals(110, day3.getOpen(), .0001);
  }

  @Test
  public void getHigh() {
    assertEquals(110, day1.getHigh(), .0001);
    assertEquals(115, day2.getHigh(), .0001);
    assertEquals(120, day3.getHigh(), .0001);
  }

  @Test
  public void getLow() {
    assertEquals(90, day1.getLow(), .0001);
    assertEquals(95, day2.getLow(), .0001);
    assertEquals(100, day3.getLow(), .0001);
  }

  @Test
  public void getClose() {
    assertEquals(105, day1.getClose(), .0001);
    assertEquals(110, day2.getClose(), .0001);
    assertEquals(115, day3.getClose(), .0001);
  }

  @Test
  public void getVolume() {
    assertEquals(1000000, day1.getVolume());
    assertEquals(1000000, day2.getVolume());
    assertEquals(1000000, day3.getVolume());
  }

  @Test
  public void equals() {
    IStockData day1Clone = new StockData(100, 110, 90, 105, 1000000);
    IStockData day2Clone = new StockData(105, 115, 95, 110, 1000000);
    IStockData day3Clone = new StockData(110, 120, 100, 115, 1000000);
    assertEquals(day1, day1Clone);
    assertEquals(day2, day2Clone);
    assertEquals(day3, day3Clone);
    assertNotEquals(day1, day2);
    assertNotEquals(day1, day3);
    assertNotEquals(day2, day3);
  }

  @Test
  public void hashCodeTest() {
    IStockData day1Clone = new StockData(100, 110, 90, 105, 1000000);
    IStockData day2Clone = new StockData(105, 115, 95, 110, 1000000);
    IStockData day3Clone = new StockData(110, 120, 100, 115, 1000000);
    assertEquals(day1.hashCode(), day1Clone.hashCode());
    assertEquals(day2.hashCode(), day2Clone.hashCode());
    assertEquals(day3.hashCode(), day3Clone.hashCode());
  }
}