package controller.textcontroller;

import org.junit.Test;

import java.io.IOException;
import java.io.StringReader;

import model.IModel2;
import model.MockModelImpl;

import static org.junit.Assert.assertEquals;

/**
 * Test cases for ControllerImpl with a mock model.
 */
public class TextControllerMockTest {
  @Test
  public void testControllerMockGainOrLoss() throws IOException {
    StringBuilder log = new StringBuilder();
    IModel2 mockModel = new MockModelImpl(log);
    Appendable output = new StringBuilder();
    Readable input = new StringReader("GainOrLoss GOOG 2018-11-01 2018-11-30");
    ITextController controller = new TextControllerImpl(input, output);
    controller.control(mockModel);
    assertEquals("GainOrLoss GOOG 2018-11-01 2018-11-30", log.toString());
  }

  @Test
  public void testControllerMockMovingAverage() throws IOException {
    StringBuilder log = new StringBuilder();
    IModel2 mockModel = new MockModelImpl(log);
    Appendable output = new StringBuilder();
    Readable input = new StringReader("MovingAverage GOOG 2020-10-05 12");
    ITextController controller = new TextControllerImpl(input, output);
    controller.control(mockModel);
    assertEquals("MovingAverage GOOG 2020-10-05 12", log.toString());
  }

  @Test
  public void testControllerMockCrossover() throws IOException {
    StringBuilder log = new StringBuilder();
    IModel2 mockModel = new MockModelImpl(log);
    Appendable output = new StringBuilder();
    Readable input = new StringReader("Crossover AMZN 2024-05-19 2024-06-05 30");
    ITextController controller = new TextControllerImpl(input, output);
    controller.control(mockModel);
    assertEquals("Crossover AMZN 2024-05-19 2024-06-05 30", log.toString());
  }

  @Test
  public void testControllerMockCreatePortfolio() throws IOException {
    StringBuilder log = new StringBuilder();
    IModel2 mockModel = new MockModelImpl(log);
    Appendable output = new StringBuilder();
    Readable input = new StringReader("CreatePortfolio Portfolio1");
    ITextController controller = new TextControllerImpl(input, output);
    controller.control(mockModel);
    assertEquals("CreatePortfolio Portfolio1", log.toString());
  }

  @Test
  public void testControllerMockAddPortfolioHolding() throws IOException {
    StringBuilder log = new StringBuilder();
    IModel2 mockModel = new MockModelImpl(log);
    Appendable output = new StringBuilder();
    Readable input = new StringReader("AddPortfolioHolding Portfolio1 GOOG 10");
    ITextController controller = new TextControllerImpl(input, output);
    controller.control(mockModel);
    assertEquals("AddPortfolioHolding Portfolio1 GOOG 10.0", log.toString());
  }

  @Test
  public void testControllerMockBuyPortfolioHolding() throws IOException {
    StringBuilder log = new StringBuilder();
    IModel2 mockModel = new MockModelImpl(log);
    Appendable output = new StringBuilder();
    Readable input = new StringReader("BuyPortfolioHolding Portfolio1 GOOG 10 2024-06-05");
    ITextController controller = new TextControllerImpl2(input, output);
    controller.control(mockModel);
    assertEquals("BuyPortfolioHolding Portfolio1 GOOG 10 2024-06-05", log.toString());
  }

  @Test
  public void testControllerMockSellPortfolioHolding() throws IOException {
    StringBuilder log = new StringBuilder();
    IModel2 mockModel = new MockModelImpl(log);
    Appendable output = new StringBuilder();
    Readable input = new StringReader("SellPortfolioHolding Portfolio1 GOOG 10 2024-06-05");
    ITextController controller = new TextControllerImpl2(input, output);
    controller.control(mockModel);
    assertEquals("SellPortfolioHolding Portfolio1 GOOG 10.0 2024-06-05", log.toString());
  }

  @Test
  public void testControllerMockGetPortfolioComposition() throws IOException {
    StringBuilder log = new StringBuilder();
    IModel2 mockModel = new MockModelImpl(log);
    Appendable output = new StringBuilder();
    Readable input = new StringReader("GetPortfolioComposition Portfolio1 2024-06-05");
    ITextController controller = new TextControllerImpl2(input, output);
    controller.control(mockModel);
    assertEquals("GetPortfolioComposition Portfolio1 2024-06-05", log.toString());
  }

  @Test
  public void testControllerMockGetPortfolioDistribution() throws IOException {
    StringBuilder log = new StringBuilder();
    IModel2 mockModel = new MockModelImpl(log);
    Appendable output = new StringBuilder();
    Readable input = new StringReader("GetPortfolioDistribution Portfolio1 2024-06-05");
    ITextController controller = new TextControllerImpl2(input, output);
    controller.control(mockModel);
    assertEquals("GetPortfolioDistribution Portfolio1 2024-06-05", log.toString());
  }

  @Test
  public void testControllerMockLoadPortfolio() throws IOException {
    StringBuilder log = new StringBuilder();
    IModel2 mockModel = new MockModelImpl(log);
    Appendable output = new StringBuilder();
    Readable input = new StringReader("LoadPortfolio Portfolio1");
    ITextController controller = new TextControllerImpl2(input, output);
    controller.control(mockModel);
    assertEquals("LoadPortfolio Portfolio1", log.toString());
  }

  @Test
  public void testControllerMockRebalancePortfolio() throws IOException {
    StringBuilder log = new StringBuilder();
    IModel2 mockModel = new MockModelImpl(log);
    Appendable output = new StringBuilder();
    Readable input = new StringReader("RebalancePortfolio Portfolio1 2024-06-05");
    ITextController controller = new TextControllerImpl2(input, output);
    controller.control(mockModel);
    assertEquals("RebalancePortfolio Portfolio1 2024-06-05", log.toString());
  }

  @Test
  public void testControllerMockPerformanceOverTime() throws IOException {
    StringBuilder log = new StringBuilder();
    IModel2 mockModel = new MockModelImpl(log);
    Appendable output = new StringBuilder();
    Readable input = new StringReader("GetPerformanceOverTime Portfolio1 2024-06-05 2024-06-12");
    ITextController controller = new TextControllerImpl2(input, output);
    controller.control(mockModel);
    assertEquals("GetPerformanceOverTime Portfolio1 2024-06-05 2024-06-12", log.toString());
  }

  @Test
  public void testControllerMockGetPortfolioValue() throws IOException {
    StringBuilder log = new StringBuilder();
    IModel2 mockModel = new MockModelImpl(log);
    Appendable output = new StringBuilder();
    Readable input = new StringReader("GetPortfolioValue TestPortfolio 2024-06-05");
    ITextController controller = new TextControllerImpl2(input, output);
    controller.control(mockModel);
    assertEquals("GetPortfolioValue TestPortfolio 2024-06-05", log.toString());
  }
}
