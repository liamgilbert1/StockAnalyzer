package controller.guicontroller;

import org.junit.Test;

import java.io.IOException;

import model.IModel2;
import model.MockModelImpl;
import view.guiview.IGUIView;
import view.guiview.MockGUIView;

import static org.junit.Assert.assertEquals;

/**
 * This class tests the GUIController class.
 */
public class GUIControllerMockTest {
  @Test
  public void testControllerMockLoadPortfolio() throws IOException {
    StringBuilder log = new StringBuilder();
    IModel2 mockModel = new MockModelImpl(log);
    IGUIView view = new MockGUIView("LoadPortfolio Portfolio1", new StringBuilder());
    IGUIController controller = new GUIController(mockModel, view);
    controller.handleSetData();
    assertEquals("LoadPortfolio Portfolio1", log.toString());
  }

  @Test
  public void testControllerMockGetPortfolioComposition() throws IOException {
     StringBuilder log = new StringBuilder();
      IModel2 mockModel = new MockModelImpl(log);
      IGUIView view = new MockGUIView("GetPortfolioComposition Portfolio1 2024-06-05",
              new StringBuilder());
      IGUIController controller = new GUIController(mockModel, view);
      controller.handleSetData();
      assertEquals("GetPortfolioComposition Portfolio1 2024-06-05", log.toString());
  }

  @Test
  public void testControllerMockSellPortfolioHolding() throws IOException {
    StringBuilder log = new StringBuilder();
    IModel2 mockModel = new MockModelImpl(log);
    IGUIView view = new MockGUIView("SellPortfolioHolding Portfolio1 GOOG 10 2024-06-05",
            new StringBuilder());
    IGUIController controller = new GUIController(mockModel, view);
    controller.handleSetData();
    assertEquals("SellPortfolioHolding Portfolio1 GOOG 10.0 2024-06-05", log.toString());
  }

  @Test
  public void testControllerMockBuyPortfolioHolding() throws IOException {
    StringBuilder log = new StringBuilder();
    IModel2 mockModel = new MockModelImpl(log);
    IGUIView view = new MockGUIView("BuyPortfolioHolding Portfolio1 GOOG 10 2024-06-05",
            new StringBuilder());
    IGUIController controller = new GUIController(mockModel, view);
    controller.handleSetData();
    assertEquals("BuyPortfolioHolding Portfolio1 GOOG 10 2024-06-05", log.toString());
  }

  @Test
  public void testControllerMockCreatePortfolio() throws IOException {
    StringBuilder log = new StringBuilder();
    IModel2 mockModel = new MockModelImpl(log);
    IGUIView view = new MockGUIView("CreatePortfolio Portfolio1",
            new StringBuilder());
    IGUIController controller = new GUIController(mockModel, view);
    controller.handleSetData();
    assertEquals("CreatePortfolio Portfolio1", log.toString());
  }

  @Test
  public void testControllerMockGetPortfolioValue() throws IOException {
    StringBuilder log = new StringBuilder();
    IModel2 mockModel = new MockModelImpl(log);
    IGUIView view = new MockGUIView("GetPortfolioValue Portfolio1 2024-06-05",
            new StringBuilder());
    IGUIController controller = new GUIController(mockModel, view);
    controller.handleSetData();
    assertEquals("GetPortfolioValue Portfolio1 2024-06-05", log.toString());
  }
}
