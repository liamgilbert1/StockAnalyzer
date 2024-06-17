package controller.io.readers;

import org.junit.Test;

import java.time.LocalDate;

import model.portfolio.IPortfolio;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Test cases for TxtPortfolioReader.
 */
public class TxtPortfolioReaderTest {

  @Test
  public void testGetPortfolio() {
    IPortfolioWithTransactionsReader reader = new TxtPortfolioReader("myPortfolio");
    IPortfolio portfolio = reader.getPortfolio();
    assertNotNull(portfolio);
    assertEquals("myPortfolio", portfolio.getName());
    assertEquals(1, portfolio.getStocks().size());
    LocalDate testDate = LocalDate.of(2020, 1, 1);
  }
}
