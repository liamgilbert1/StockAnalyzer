import org.junit.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import model.IModel;
import model.ModelImpl;

import static org.junit.Assert.assertEquals;

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
}
