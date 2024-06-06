import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import model.portfolio.Holding;
import model.portfolio.IHolding;
import model.portfolio.IPortfolio;
import model.portfolio.Portfolio;
import model.stock.IStock;
import model.stock.IStockData;
import model.stock.Stock;
import model.stock.StockData;

import static org.junit.Assert.*;

public class PortfolioTest {

  IHolding holding1;
  IHolding holding2;
  IHolding holding3;
  IStock stock1;
  IStock stock2;
  IStockData day1;
  IStockData day2;
  IStockData day3;
  Map<LocalDate, IStockData> stockData1;
  LocalDate date1;
  LocalDate date2;
  LocalDate date3;

  IPortfolio portfolio1;
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
    stock2 = new Stock("MSFT", stockData1);
    holding1 = new Holding(stock1, 1000);
    holding2 = new Holding(stock1, 2000);
    holding3 = new Holding(stock2, 5000);
    portfolio1 = new Portfolio("MyPortfolio");
  }

  @Test
  public void addHolding() {
    portfolio1 = portfolio1.addHolding(holding1);
    assertEquals(1, portfolio1.getHoldings().size());
    assertEquals(105000, portfolio1.getHoldings().get(0).getValue(date1), .0001);
    portfolio1 = portfolio1.addHolding(holding2);
    assertEquals(1, portfolio1.getHoldings().size());
    assertEquals(315000, portfolio1.getHoldings().get(0).getValue(date1), .0001);
    portfolio1 = portfolio1.addHolding(holding3);
    assertEquals(2, portfolio1.getHoldings().size());
    assertEquals(315000, portfolio1.getHoldings().get(0).getValue(date1), .0001);
    assertEquals(525000, portfolio1.getHoldings().get(1).getValue(date1), .0001);
  }

  @Test
  public void getValue() {
    portfolio1 = portfolio1.addHolding(holding1);
    assertEquals(105000, portfolio1.getValue(date1), .0001);
    portfolio1 = portfolio1.addHolding(holding2);
    assertEquals(315000, portfolio1.getValue(date1), .0001);
    portfolio1 = portfolio1.addHolding(holding3);
    assertEquals(840000, portfolio1.getValue(date1), .0001);
  }

  @Test
  public void testGetHoldings() {
    portfolio1 = portfolio1.addHolding(holding1);
    ArrayList<IHolding> expected = new ArrayList<>();
    expected.add(holding1);
    assertEquals(expected, portfolio1.getHoldings());
    IHolding expectedHolding = new Holding(stock1, 3000);
    portfolio1 = portfolio1.addHolding(holding2);
    expected.remove(holding1);
    expected.add(expectedHolding);
    assertEquals(expected, portfolio1.getHoldings());
    portfolio1 = portfolio1.addHolding(holding3);
    expected.add(holding3);
    assertEquals(expected, portfolio1.getHoldings());
  }

  @Test
  public void testEquals() {
    IPortfolio portfolio2 = new Portfolio("MyPortfolio");
    IPortfolio portfolio3 = new Portfolio("MyOtherPortfolio");
    assertNotEquals(portfolio1, portfolio3);
    assertEquals(portfolio1, portfolio2);
    portfolio1 = portfolio1.addHolding(holding1);
    assertNotEquals(portfolio1, portfolio2);
    portfolio2 = portfolio2.addHolding(holding1);
    assertEquals(portfolio1, portfolio2);
  }

  @Test
  public void testHashCode() {
    IPortfolio portfolio2 = new Portfolio("MyPortfolio");
    assertEquals(portfolio1.hashCode(), portfolio2.hashCode());
    portfolio1 = portfolio1.addHolding(holding1);
    portfolio2 = portfolio2.addHolding(holding1);
    assertEquals(portfolio1.hashCode(), portfolio2.hashCode());
    portfolio1 = portfolio1.addHolding(holding2);
    portfolio2 = portfolio2.addHolding(holding2);
    assertEquals(portfolio1.hashCode(), portfolio2.hashCode());
  }
}