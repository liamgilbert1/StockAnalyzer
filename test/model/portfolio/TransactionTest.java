package model.portfolio;

import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;


import model.stock.Stock;

import static org.junit.Assert.assertEquals;

/**
 * Test cases for SellTransaction.
 */
public class TransactionTest {
  ITransaction sellTransaction;
  ITransaction buyTransaction;

  @Before
  public void setUp() throws Exception {
    sellTransaction = new SellTransaction(new Stock("GOOG"), 1,
            LocalDate.of(2024, 6, 5));
    buyTransaction = new BuyTransaction(new Stock("GOOG"), 1,
            LocalDate.of(2024, 6, 5));
  }

  @Test
  public void getBuyQuantity() {
    assertEquals(1, sellTransaction.getQuantity(), 0.01);
  }

  @Test
  public void realBuyQuantity() {
    assertEquals(-1, sellTransaction.realQuantity(), 0.01);
  }

  @Test
  public void getBuyCopy() {
    ITransaction copy = sellTransaction.getCopy();
    assertEquals(sellTransaction.getStock(), copy.getStock());
    assertEquals(sellTransaction.getDate(), copy.getDate());
    assertEquals(sellTransaction.getQuantity(), copy.getQuantity(), 0.01);
  }

  @Test
  public void action() {
    assertEquals("sell", sellTransaction.action());
  }

  @Test
  public void buyGetQuantity() {
    assertEquals(1, buyTransaction.getQuantity(), 0.01);
  }

  @Test
  public void buyRealQuantity() {
    assertEquals(1, buyTransaction.realQuantity(), 0.01);
  }

  @Test
  public void buyGetCopy() {
    ITransaction copy = buyTransaction.getCopy();
    assertEquals(buyTransaction.getStock(), copy.getStock());
    assertEquals(buyTransaction.getDate(), copy.getDate());
    assertEquals(buyTransaction.getQuantity(), copy.getQuantity(), 0.01);
  }

  @Test
  public void buyAction() {
    assertEquals("buy", buyTransaction.action());
  }
}