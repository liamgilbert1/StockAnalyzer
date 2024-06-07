import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.List;

import model.stock.IStock;
import model.stock.Stock;
import model.stock.StockDataPoint;

import static model.stock.StockDataPoint.CLOSE;
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
  public void getTicker() {
    stock2 = new Stock("TEST");
    assertEquals("GOOG", stock1.getTicker());
    assertEquals("TEST", stock2.getTicker());
  }

  @Test
  public void checkContainsDates() {
    assertTrue(stock1.checkContainsDates(date1, 1));
    assertTrue(stock1.checkContainsDates(date1, 1));
  }


  @Test
  public void checkContainsDateRange() {
    assertTrue(stock1.checkContainsDateRange(date1, date3));
    assertTrue(stock1.checkContainsDateRange(date1, date2));
  }


  @Test
  public void getMostRecentDate() {
    stock2 = new Stock("TEST");
    assertEquals(LocalDate.of(2024, 6, 5), stock1.getMostRecentDate());
    assertEquals(LocalDate.of(2024, 6, 5), stock1.getMostRecentDate());
  }

  @Test
  public void getClosePrice() {
    assertEquals(173.96, stock1.getClosePrice(date1), 0.01);
    assertEquals(174.42, stock1.getClosePrice(date2), 0.01);
    assertEquals(175.13, stock1.getClosePrice(date3), 0.01);
  }

  @Test
  public void getOpenPrice() {
    assertEquals(173.4, stock1.getOpenPrice(date1), 0.01);
    assertEquals(173.88, stock1.getOpenPrice(date2), 0.01);
    assertEquals(174.45, stock1.getOpenPrice(date3), 0.01);
  }

  @Test
  public void getHighPrice() {
    assertEquals(174.42, stock1.getHighPrice(date1), 0.01);
    assertEquals(175.86, stock1.getHighPrice(date2), 0.01);
    assertEquals(175.19, stock1.getHighPrice(date3), 0.01);
  }

  @Test
  public void getLowPrice() {
    assertEquals(170.97, stock1.getLowPrice(date1), 0.01);
    assertEquals(172.45, stock1.getLowPrice(date2), 0.01);
    assertEquals(173.22, stock1.getLowPrice(date3), 0.01);
  }

  @Test
  public void getVolume() {
    assertEquals(28085151, stock1.getVolume(date1), 0.01);
    assertEquals(20742798, stock1.getVolume(date2), 0.01);
    assertEquals(14066602, stock1.getVolume(date3), 0.01);
  }

  @Test
  public void getDataAcrossDays() {
    List<String> data = stock1.getDataAcrossDays(date3, 3, CLOSE);
    assertEquals(3, data.size());
    assertEquals("175.1300", data.get(0));
    assertEquals("174.4200", data.get(1));
    assertEquals("173.9600", data.get(2));
  }

  @Test
  public void equals() {
    assertEquals(stock1, stock1Clone);
    assertNotEquals(stock1, stock2);
  }

  @Test
  public void hashCodeTest() {
    assertEquals(stock1.hashCode(), stock1Clone.hashCode());
  }
}