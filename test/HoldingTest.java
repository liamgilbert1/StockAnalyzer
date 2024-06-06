import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import model.portfolio.Holding;
import model.portfolio.IHolding;
import model.stock.IStock;
import model.stock.IStockData;
import model.stock.Stock;
import model.stock.StockData;

import static org.junit.Assert.*;

public class HoldingTest {

  IHolding holding1;
  IStock stock1;
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
    stock1 = new Stock("GOOG", stockData1);
    holding1 = new Holding(stock1, 1000);
  }

  @Test
  public void getStock() {
    assertEquals(stock1, holding1.getStock());
  }

  @Test
  public void getQuantity() {
    assertEquals(1000, holding1.getQuantity());
  }

  @Test
  public void getValue() {
    assertEquals(105000,
            holding1.getValue(LocalDate.of(2018, 1, 1)), 0.01);
    assertEquals(110000,
            holding1.getValue(LocalDate.of(2018, 1, 2)), 0.01);
    assertEquals(115000,
            holding1.getValue(LocalDate.of(2018, 1, 3)), 0.01);
  }

  @Test
  public void addQuantity() {
    IHolding newHolding = holding1.addQuantity(1000);
    assertEquals(2000, newHolding.getQuantity());
  }
}