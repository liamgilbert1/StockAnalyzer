package model.portfolio;

import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import model.portfolio.BuyTransaction;
import model.portfolio.IPortfolioWithTransactions;
import model.portfolio.ITransaction;
import model.portfolio.PortfolioWithTransactions;
import model.portfolio.SellTransaction;
import model.stock.Stock;

import static org.junit.Assert.*;

public class PortfolioWithTransactionsTest {
  IPortfolioWithTransactions portfolio;
  IPortfolioWithTransactions portfolio2;
  IPortfolioWithTransactions portfolio3;
  ITransaction transaction1;
  ITransaction transaction2;
  ITransaction transaction3;

  @Before
  public void setUp() throws Exception {
    transaction1 = new BuyTransaction(new Stock("GOOG"), 5,
            LocalDate.of(2024, 6, 5));
    transaction2 = new SellTransaction(new Stock("GOOG"), 2,
            LocalDate.of(2024, 6, 6));
    transaction3 = new BuyTransaction(new Stock("AAPL"), 5,
            LocalDate.of(2024, 6, 6));
    portfolio = new PortfolioWithTransactions("test");
    portfolio2 = new PortfolioWithTransactions("test2",
            Arrays.asList(transaction1, transaction2));
    portfolio3 = new PortfolioWithTransactions("test3", List.of(transaction1, transaction3));
  }

  @Test
  public void addTransaction() {
    IPortfolioWithTransactions port =
            new PortfolioWithTransactions("test", List.of(transaction1));
    assertEquals(port, portfolio.addTransaction(transaction1));

    try {
      port.addTransaction(new BuyTransaction(new Stock("GOOG"), 1,
              LocalDate.of(2024, 6, 3)));
    } catch (IllegalArgumentException e) {
      assertEquals("Transaction date is before previous transaction date.",
              e.getMessage());
    }

  }


  @Test
  public void getComposition() {
    String comp = portfolio2.getComposition(LocalDate.of(2024, 6, 6));
    assertEquals("GOOG: 3.00\n", comp);
  }

  @Test
  public void getValueDistribution() {
    String dist = portfolio2.getValueDistribution(LocalDate.of(2024, 6, 6));
    assertEquals("GOOG: $535.05\n", dist);
  }

  @Test
  public void getValue() {
    assertEquals(535.05, portfolio2.getValue(LocalDate.of(2024, 6, 6)), 0.01);
  }

  @Test
  public void getPerformanceOverTime() {
    String perf = portfolio.getPerformanceOverTime(LocalDate.of(2024, 6, 5),
            LocalDate.of(2024, 6, 10));
    assertEquals("Performance of test from 2024-06-05 to 2024-06-10\n" +
            "\n" +
            "05 JUN 2024: \n" +
            "06 JUN 2024: \n" +
            "07 JUN 2024: \n" +
            "08 JUN 2024: \n" +
            "09 JUN 2024: \n" +
            "10 JUN 2024: \n" +
            "\n" +
            "Scale: * = $10\n", perf);
  }

  @Test
  public void getPerformanceDates() {
    List<LocalDate> dates = List.of(LocalDate.of(2024, 6, 5),
            LocalDate.of(2024, 6, 6),
            LocalDate.of(2024, 6, 7),
            LocalDate.of(2024, 6, 8),
            LocalDate.of(2024, 6, 9),
            LocalDate.of(2024, 6, 10));
    assertEquals(dates, portfolio.getPerformanceDates(
            LocalDate.of(2024, 6, 5),
            LocalDate.of(2024, 6, 10)));
  }

  @Test
  public void getTransactions() {
    assertEquals(Arrays.asList(), portfolio.getTransactions());
  }

  @Test
  public void getName() {
    assertEquals("test", portfolio.getName());
  }

  @Test
  public void getStocks() {
    assertEquals(List.of("GOOG"), portfolio2.getStocks());
  }

  @Test
  public void isDateBeforeFirstTransaction() {
    assertEquals(false, portfolio.isDateBeforeFirstTransaction(LocalDate.of(2024, 6, 6)));
    assertEquals(true, portfolio2.isDateBeforeFirstTransaction(LocalDate.of(2024, 6, 2)));
  }

  @Test
  public void loadPortfolio() {
  }
}