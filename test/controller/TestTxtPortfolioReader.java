package controller;

import org.junit.Test;

import java.time.LocalDate;

import controller.IO.readers.IPortfolioWithTransactionsReader;
import model.portfolio.IPortfolio;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import controller.IO.readers.TxtPortfolioReader;

public class TestTxtPortfolioReader {

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
