package model;

import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;

import model.portfolio.ATransaction;
import model.portfolio.BuyTransaction;
import model.stock.IStock;
import model.stock.Stock;

import static org.junit.Assert.assertEquals;

public class ATransactionTest {
  ATransaction transaction;

  @Before
  public void setUp() throws Exception {
    LocalDate date = LocalDate.of(2024, 6, 5);
     transaction = new BuyTransaction(new Stock("GOOG") ,1, date);
  }


  @Test
  public void getStock() {
    IStock stock = new Stock("GOOG");
    assertEquals(stock, transaction.getStock());
  }

  @Test
  public void getDate() {
    LocalDate date = LocalDate.of(2024, 6, 5);
    assertEquals(date, transaction.getDate());
  }

  @Test
  public void getValue() {
    assertEquals(177.07, transaction.getValue(), 0.01);
  }

}