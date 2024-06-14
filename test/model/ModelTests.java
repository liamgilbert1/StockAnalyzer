package model;

import org.junit.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.IModel;
import model.ModelImpl;

import static org.junit.Assert.assertEquals;

/**
 * This class tests the Model class.
 */
public class ModelTests {

  @Test
  public void testModelGainOrLoss() {
    LocalDate startDate = LocalDate.of(2024, 6, 4);
    LocalDate endDate = LocalDate.of(2024, 6, 5);
    IModel model = new ModelImpl();
    assertEquals(1.94, model.calculateGainOrLoss("GOOG", startDate, endDate),
            0.01);
  }

  @Test
  public void testInvalidTicker() {
    LocalDate startDate = LocalDate.of(2024, 6, 4);
    LocalDate endDate = LocalDate.of(2024, 6, 5);
    IModel model = new ModelImpl();
    try {
      model.calculateGainOrLoss("INVALID", startDate, endDate);
    } catch (IllegalStateException e) {
      assertEquals("Could not read from file", e.getMessage());
    }
  }



  @Test
  public void testModelMovingAverage() {
    IModel model = new ModelImpl();
    assertEquals(169.477, model.movingAverage("GOOG", LocalDate.of(2024,
            5, 29), 30), 0.001);
  }

  @Test
  public void testModelCrossOver() {
    IModel model = new ModelImpl();
    List<LocalDate> expectedDates = new ArrayList<>(Arrays.asList(
            LocalDate.of(2024, 3, 22),
            LocalDate.of(2024, 3, 21),
            LocalDate.of(2024, 3, 20),
            LocalDate.of(2024, 3, 19),
            LocalDate.of(2024, 3, 18),
            LocalDate.of(2024, 3, 15),
            LocalDate.of(2024, 3, 14)));
    assertEquals(expectedDates, model.crossOver("GOOG",
            LocalDate.of(2024, 3, 7),
            LocalDate.of(2024, 3, 22), 30));
  }

  @Test
  public void testModelCreatePortfolio() {
    IModel model = new ModelImpl();
    model.createPortfolio("TestPortfolio");
    List<String> portfolios = model.getPortfolioNames();
    assertEquals(List.of("TestPortfolio"), portfolios);

    model.createPortfolio("TestPortfolio2");
    portfolios = model.getPortfolioNames();
    assertEquals(List.of("TestPortfolio", "TestPortfolio2"), portfolios);

    model.createPortfolio("TestPortfolio3");
    portfolios = model.getPortfolioNames();
    assertEquals(List.of("TestPortfolio", "TestPortfolio2", "TestPortfolio3"), portfolios);
  }

  @Test
  public void testAddPortfolioHolding() {
    IModel model = new ModelImpl();
    model.createPortfolio("TestPortfolio");
    model.addPortfolioHolding("TestPortfolio", "GOOG", 10);
    model.addPortfolioHolding("TestPortfolio", "MSFT", 20);
    model.addPortfolioHolding("TestPortfolio", "AMZN", 30);
    List<String> stocks = model.getStocksInPortfolio("TestPortfolio");
    assertEquals(List.of("GOOG", "MSFT", "AMZN"), stocks);
  }

  @Test
  public void testGetPortfolioValue() {
    IModel model = new ModelImpl();
    LocalDate testDate = LocalDate.of(2024, 6, 4);
    model.createPortfolio("TestPortfolio");
    model.addPortfolioHolding("TestPortfolio", "GOOG", 10);
    model.addPortfolioHolding("TestPortfolio", "MSFT", 20);
    model.addPortfolioHolding("TestPortfolio", "AMZN", 30);
    assertEquals(15452.90, model.getPortfolioValue("TestPortfolio", testDate),
            0.01);
    assertEquals(15335.40, model.getPortfolioValue("TestPortfolio",
                    LocalDate.of(2024, 6, 1)), 0.01);
    assertEquals(15335.40, model.getPortfolioValue("TestPortfolio",
            LocalDate.of(2024, 5, 31)), 0.01);
    try {
      model.getPortfolioValue("TestPortfolio",
              LocalDate.of(1990, 5, 31));
    } catch (IllegalArgumentException e) {
      assertEquals("Most recent date not found", e.getMessage());
    }
  }

  @Test
  public void getStocksInPortfolio() {
    IModel model = new ModelImpl();
    model.createPortfolio("TestPortfolio");
    model.addPortfolioHolding("TestPortfolio", "GOOG", 10);
    model.addPortfolioHolding("TestPortfolio", "MSFT", 20);
    model.addPortfolioHolding("TestPortfolio", "AMZN", 30);
    List<String> stocks = model.getStocksInPortfolio("TestPortfolio");
    assertEquals(List.of("GOOG", "MSFT", "AMZN"), stocks);
  }

  @Test
  public void testBuyPortfolioHolding() {
    IModel2 model = new ModelImpl2();
    model.createPortfolio("TestPortfolio");
    model.buyPortfolioHolding("TestPortfolio", "GOOG", 10,
            LocalDate.of(2024, 6, 4));
    model.buyPortfolioHolding("TestPortfolio", "MSFT", 20,
            LocalDate.of(2024, 6, 4));
    model.buyPortfolioHolding("TestPortfolio", "AMZN", 30,
            LocalDate.of(2024, 6, 4));
    List<String> stocks = model.getStocksInPortfolio("TestPortfolio");
    assertEquals(List.of("GOOG", "MSFT", "AMZN"), stocks);
    String composition = model.getPortfolioComposition("TestPortfolio",
            LocalDate.of(2024, 6, 4));
    assertEquals("GOOG: 10.00\n" +
            "MSFT: 20.00\n" +
            "AMZN: 30.00\n", composition);

    try {
      model.buyPortfolioHolding("TestPortfolio", "GOOG", 10,
              LocalDate.of(2024, 6, 3));
    } catch (IllegalArgumentException e) {
      assertEquals("Transaction date is before previous transaction date.",
              e.getMessage());
    }
  }

  @Test
  public void testSellPortfolioHolding() {
    IModel2 model = new ModelImpl2();
    model.createPortfolio("TestPortfolio");
    model.buyPortfolioHolding("TestPortfolio", "GOOG", 10,
            LocalDate.of(2024, 6, 4));
    model.buyPortfolioHolding("TestPortfolio", "MSFT", 20,
            LocalDate.of(2024, 6, 4));
    model.buyPortfolioHolding("TestPortfolio", "AMZN", 30,
            LocalDate.of(2024, 6, 4));
    model.sellPortfolioHolding("TestPortfolio", "GOOG", 5,
            LocalDate.of(2024, 6, 5));
    model.sellPortfolioHolding("TestPortfolio", "MSFT", 10,
            LocalDate.of(2024, 6, 5));
    model.sellPortfolioHolding("TestPortfolio", "AMZN", 15,
            LocalDate.of(2024, 6, 5));
    List<String> stocks = model.getStocksInPortfolio("TestPortfolio");
    assertEquals(List.of("GOOG", "MSFT", "AMZN"), stocks);
    String composition = model.getPortfolioComposition("TestPortfolio",
            LocalDate.of(2024, 6, 5));
    assertEquals("GOOG: 5.00\n" +
            "MSFT: 10.00\n" +
            "AMZN: 15.00\n", composition);

    try {
      model.sellPortfolioHolding("TestPortfolio", "GOOG", 10,
              LocalDate.of(2024, 6, 3));
    } catch (IllegalArgumentException e) {
      assertEquals("Transaction date is before previous transaction date.",
              e.getMessage());
    }
  }

  @Test
  public void testGetPortfolioValue2() {
    IModel2 model = new ModelImpl2();
    LocalDate testDate = LocalDate.of(2024, 6, 4);
    model.createPortfolio("TestPortfolio");
    model.buyPortfolioHolding("TestPortfolio", "GOOG", 10,
            LocalDate.of(2024, 6, 4));
    model.buyPortfolioHolding("TestPortfolio", "MSFT", 20,
            LocalDate.of(2024, 6, 4));
    model.buyPortfolioHolding("TestPortfolio", "AMZN", 30,
            LocalDate.of(2024, 6, 4));
    assertEquals(15452.90, model.getPortfolioValue("TestPortfolio", testDate),
            0.01);
    assertEquals(0, model.getPortfolioValue("TestPortfolio",
            LocalDate.of(2024, 6, 1)), 0.01);
  }

  @Test
  public void testGetPortfolioComposition() {
    IModel2 model = new ModelImpl2();
    model.createPortfolio("TestPortfolio");
    model.buyPortfolioHolding("TestPortfolio", "GOOG", 10,
            LocalDate.of(2024, 6, 4));
    model.buyPortfolioHolding("TestPortfolio", "MSFT", 20,
            LocalDate.of(2024, 6, 4));
    model.buyPortfolioHolding("TestPortfolio", "AMZN", 30,
            LocalDate.of(2024, 6, 4));
    String composition = model.getPortfolioComposition("TestPortfolio",
            LocalDate.of(2024, 6, 4));
    assertEquals("GOOG: 10.00\n" +
            "MSFT: 20.00\n" +
            "AMZN: 30.00\n", composition);

    String composition2 = model.getPortfolioComposition("TestPortfolio",
            LocalDate.of(2024, 6, 3));
    assertEquals("", composition2);
  }

  @Test
  public void testGetValueDistribution() {
    IModel2 model = new ModelImpl2();
    model.createPortfolio("TestPortfolio");
    model.buyPortfolioHolding("TestPortfolio", "GOOG", 10,
            LocalDate.of(2024, 6, 4));
    model.buyPortfolioHolding("TestPortfolio", "MSFT", 20,
            LocalDate.of(2024, 6, 4));
    model.buyPortfolioHolding("TestPortfolio", "AMZN", 30,
            LocalDate.of(2024, 6, 4));
    String distribution = model.getPortfolioValueDistribution("TestPortfolio",
            LocalDate.of(2024, 6, 4));
    assertEquals("GOOG: $1751.30\n" +
            "MSFT: $8321.40\n" +
            "AMZN: $5380.20\n", distribution);

    assertEquals("", model.getPortfolioValueDistribution("TestPortfolio",
            LocalDate.of(2024, 6, 3)));
  }

  @Test
  public void testGetStocksInPortfolio2() {
    IModel2 model = new ModelImpl2();
    model.createPortfolio("TestPortfolio");
    model.buyPortfolioHolding("TestPortfolio", "GOOG", 10,
            LocalDate.of(2024, 6, 4));
    model.buyPortfolioHolding("TestPortfolio", "MSFT", 20,
            LocalDate.of(2024, 6, 4));
    model.buyPortfolioHolding("TestPortfolio", "AMZN", 30,
            LocalDate.of(2024, 6, 4));
    List<String> stocks = model.getStocksInPortfolio("TestPortfolio");
    assertEquals(List.of("GOOG", "MSFT", "AMZN"), stocks);
  }

  @Test
  public void testGetPerformanceOverTime() {
    IModel2 model = new ModelImpl2();
    model.createPortfolio("TestPortfolio");
    model.buyPortfolioHolding("TestPortfolio", "GOOG", 10,
            LocalDate.of(2024, 6, 4));
    model.buyPortfolioHolding("TestPortfolio", "MSFT", 20,
            LocalDate.of(2024, 6, 4));
    model.buyPortfolioHolding("TestPortfolio", "AMZN", 30,
            LocalDate.of(2024, 6, 4));
    String performance = model.getPortfolioPerformanceOverTime("TestPortfolio",
            LocalDate.of(2024, 6, 4),
            LocalDate.of(2024, 6, 5));
    assertEquals("Performance of TestPortfolio from 2024-06-04 to 2024-06-05\n" +
            "\n" +
            "04 JUN 2024: ***************\n" +
            "05 JUN 2024: ****************\n" +
            "\n" +
            "Scale: * = $1000\n", performance);

    String performance2 = model.getPortfolioPerformanceOverTime("TestPortfolio",
            LocalDate.of(2024, 6, 2),
            LocalDate.of(2024, 6, 3));

    assertEquals("Performance of TestPortfolio from 2024-06-02 to 2024-06-03\n" +
            "\n" +
            "02 JUN 2024: \n" +
            "03 JUN 2024: \n" +
            "\n" +
            "Scale: * = $10\n", performance2);
  }

  @Test
  public void testRebalancePortfolio() {
    IModel2 model = new ModelImpl2();
    model.createPortfolio("TestPortfolio");
    model.buyPortfolioHolding("TestPortfolio", "GOOG", 10,
            LocalDate.of(2024, 6, 4));
    model.buyPortfolioHolding("TestPortfolio", "MSFT", 20,
            LocalDate.of(2024, 6, 4));
    model.buyPortfolioHolding("TestPortfolio", "AMZN", 30,
            LocalDate.of(2024, 6, 4));
    model.rebalancePortfolio("TestPortfolio",
            LocalDate.of(2024, 6, 4),
            new HashMap<>(Map.of("GOOG", 10, "MSFT", 30, "AMZN", 60)));
    List<String> stocks = model.getStocksInPortfolio("TestPortfolio");
    assertEquals(List.of("GOOG", "MSFT", "AMZN"), stocks);
    assertEquals("GOOG: 8.82\n" +
            "MSFT: 11.14\n" +
            "AMZN: 51.70\n", model.getPortfolioComposition("TestPortfolio",
            LocalDate.of(2024, 6, 4)));
  }
}
