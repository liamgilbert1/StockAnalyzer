import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import model.stock.IStock;
import model.stock.IStockData;
import model.stock.Stock;
import model.stock.StockData;

import static org.junit.Assert.*;

public class StockTest {

  IStock stock1;
  IStock stock1Clone;
  IStock stock2;
  IStock stock3;
  IStockData day1;
  IStockData day2;
  IStockData day3;
  Map<LocalDate, IStockData> stockData1;
  LocalDate date1;
  LocalDate date2;
  LocalDate date3;


  @Before
  public void setUp() {
    day1 = new StockData(100, 110, 90, 105, 1000000);
    day2 = new StockData(105, 115, 95, 110, 1000000);
    day3 = new StockData(110, 120, 100, 115, 1000000);
    date1 = LocalDate.of(2018, 1, 1);
    date2 = LocalDate.of(2018, 1, 2);
    date3 = LocalDate.of(2018, 1, 3);
    stockData1 = new HashMap<>();
    stockData1.put(date1, day1);
    stockData1.put(date2, day2);
    stockData1.put(date3, day3);
    stock1 = new Stock("GOOG");
    stock1Clone = new Stock("GOOG");
    stock2 = new Stock("MSFT", stockData1);
  }

  @Test
  public void getPrice() {
    assertEquals(105, stock2.getPrice(date1), .0001);
    assertEquals(110, stock2.getPrice(date2), .0001);
    assertEquals(115, stock2.getPrice(date3), .0001);

    try {
      stock1.getPrice(date1);
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals("No stock data found for GOOG on 2018-01-01", e.getMessage());
    }
  }

  @Test
  public void getTicker() {
    assertEquals("GOOG", stock1.getTicker());
    assertEquals("MSFT", stock2.getTicker());
  }

  @Test
  public void addStockData() {
    stock3 = stock1.addStockData(date1, day1);
    assertEquals(day1.getClose(), stock3.getPrice(date1), .0001);
    stock3 = stock3.addStockData(date2, day2);
    assertEquals(day1.getClose(), stock3.getPrice(date1), .0001);
    assertEquals(day2.getClose(), stock3.getPrice(date2), .0001);
    stock3 = stock3.addStockData(date3, day3);
    assertEquals(day1.getClose(), stock3.getPrice(date1), .0001);
    assertEquals(day2.getClose(), stock3.getPrice(date2), .0001);
    assertEquals(day3.getClose(), stock3.getPrice(date3), .0001);
  }

  @Test
  public void getStockData() {
    assertEquals(stockData1, stock2.getStockData());
  }

  @Test
  public void equals() {
    assertEquals(stock1, stock1Clone);
    stock1Clone.addStockData(date1, day1);
    assertEquals(stock1, stock1Clone);
    assertNotEquals(stock1, stock2);
  }

  @Test
  public void hashCodeTest() {
    assertEquals(stock1.hashCode(), stock1Clone.hashCode());
    stock1.addStockData(date1, day1);
    assertEquals(stock1.hashCode(), stock1Clone.hashCode());
  }
}