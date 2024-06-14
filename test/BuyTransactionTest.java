import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;

import model.portfolio.BuyTransaction;
import model.portfolio.ITransaction;
import model.stock.Stock;

import static org.junit.Assert.*;

public class BuyTransactionTest {
  ITransaction transaction;

  @Before
  public void setUp() throws Exception {
    transaction = new BuyTransaction(new Stock("GOOG"), 1,
            LocalDate.of(2024, 6, 5));
  }

  @Test
  public void getQuantity() {
    assertEquals(1, transaction.getQuantity(), 0.01);
  }

  @Test
  public void realQuantity() {
    assertEquals(1, transaction.realQuantity(), 0.01);
  }

  @Test
  public void getCopy() {
    ITransaction copy = transaction.getCopy();
    assertEquals(transaction.getStock(), copy.getStock());
    assertEquals(transaction.getDate(), copy.getDate());
    assertEquals(transaction.getQuantity(), copy.getQuantity(), 0.01);
  }

  @Test
  public void action() {
    assertEquals("buy", transaction.action());
  }
}