import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.ArrayList;

import model.portfolio.Holding;
import model.portfolio.IHolding;
import model.portfolio.IPortfolio;
import model.portfolio.Portfolio;
import model.stock.IStock;
import model.stock.Stock;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * This class tests the Portfolio class.
 */
public class PortfolioTest {

  IHolding holding1;
  IHolding holding2;
  IHolding holding3;
  IStock stock1;
  IStock stock2;
  LocalDate date1;
  LocalDate date2;
  LocalDate date3;

  IPortfolio portfolio1;
  @Before
  public void setUp() {
    date1 = LocalDate.of(2018, 1, 1);
    date2 = LocalDate.of(2018, 1, 2);
    date3 = LocalDate.of(2018, 1, 3);
    stock1 = new Stock("GOOG");
    stock2 = new Stock("MSFT");
    holding1 = new Holding(stock1, 1000);
    holding2 = new Holding(stock1, 2000);
    holding3 = new Holding(stock2, 5000);
    portfolio1 = new Portfolio("MyPortfolio");
  }

  @Test
  public void testAddHolding() {
    portfolio1 = portfolio1.addHolding(holding1);
    assertEquals(1, portfolio1.getHoldings().size());
    assertEquals(1046400, portfolio1.getHoldings().get(0).getValue(date1), .0001);
    portfolio1 = portfolio1.addHolding(holding2);
    assertEquals(1, portfolio1.getHoldings().size());
    assertEquals(3139200, portfolio1.getHoldings().get(0).getValue(date1), .0001);
    portfolio1 = portfolio1.addHolding(holding3);
    assertEquals(2, portfolio1.getHoldings().size());
    assertEquals(3139200, portfolio1.getHoldings().get(0).getValue(date1), .0001);
    assertEquals(427700, portfolio1.getHoldings().get(1).getValue(date1), .0001);
  }

  @Test
  public void testGetValue() {
    portfolio1 = portfolio1.addHolding(holding1);
    assertEquals(1046400, portfolio1.getValue(date1), .0001);
    portfolio1 = portfolio1.addHolding(holding2);
    assertEquals(3139200, portfolio1.getValue(date1), .0001);
    portfolio1 = portfolio1.addHolding(holding3);
    assertEquals(3566900, portfolio1.getValue(date1), .0001);
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