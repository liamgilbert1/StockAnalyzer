package controller.guicontroller;

import org.junit.Test;

import java.io.IOException;
import java.io.StringReader;
import java.time.LocalDate;

import controller.textcontroller.ITextController;
import controller.textcontroller.TextControllerImpl2;
import model.IModel2;
import model.ModelImpl2;
import view.guiview.IGUIView;
import view.guiview.MockGUIView;

import static org.junit.Assert.assertEquals;

/**
 * This class tests the GUIController class.
 */
public class GUIControllerModelTest {
  @Test
  public void LoadPortfolioTest() throws IOException {
    IModel2 model = new ModelImpl2();
    model.createPortfolio("TestPortfolio");
    model.buyPortfolioHolding("TestPortfolio", "GOOG", 10,
            LocalDate.of(2024, 6, 4));
    model.buyPortfolioHolding("TestPortfolio", "MSFT", 20,
            LocalDate.of(2024, 6, 4));
    model.buyPortfolioHolding("TestPortfolio", "AMZN", 30,
            LocalDate.of(2024, 6, 4));
    IGUIView view = new MockGUIView("LoadPortfolio TestPortfolio",
            new StringBuilder());
    IGUIController controller = new GUIController(model, view);
    controller.handleSetData();

    assertEquals("GOOG: 10.00\n" +
            "MSFT: 20.00\n" +
            "AMZN: 30.00\n", model.getPortfolioComposition("TestPortfolio",
            LocalDate.of(2024, 6, 4)));
  }

  @Test
  public void testControllerModelGetPortfolioComposition() throws IOException {
    IModel2 model = new ModelImpl2();
    Readable input = new StringReader("CreatePortfolio TestPortfolio\n" +
            "BuyPortfolioHolding TestPortfolio GOOG 50 2020-01-01\n" +
            "BuyPortfolioHolding TestPortfolio AMZN 10 2020-01-01\n");
    ITextController controller = new TextControllerImpl2(input, new StringBuilder());
    controller.control(model);
    Appendable output = new StringBuilder();
    IGUIView view = new MockGUIView("GetPortfolioComposition TestPortfolio 2020-01-01",
            output);
    IGUIController guiController = new GUIController(model, view);
    guiController.handleSetData();
    assertEquals("GOOG: 50.00\n" +
            "AMZN: 10.00\n", output.toString());
  }

  @Test
  public void testControllerModelGetPortfolioValue() throws IOException {
    IModel2 model = new ModelImpl2();
    Appendable output = new StringBuilder();
    Readable input = new StringReader("CreatePortfolio TestPortfolio\n" +
            "BuyPortfolioHolding TestPortfolio GOOG 5 2024-06-05\n");
    ITextController controller = new TextControllerImpl2(input, new StringBuilder());
    controller.control(model);
    IGUIView view = new MockGUIView("GetPortfolioValue TestPortfolio 2024-06-05",
            output);
    IGUIController guiController = new GUIController(model, view);
    guiController.handleSetData();

    assertEquals("Portfolio value is: $885.35", output.toString());
  }

  @Test
  public void testControllerModelBuyPortfolioHolding() throws IOException {
    IModel2 model = new ModelImpl2();
    Appendable output = new StringBuilder();
    Readable input = new StringReader("CreatePortfolio TestPortfolio\n");
    ITextController controller = new TextControllerImpl2(input, new StringBuilder());
    controller.control(model);
    IGUIView view = new MockGUIView("BuyPortfolioHolding TestPortfolio GOOG 5 2024-06-05",
            output);
    IGUIController guiController = new GUIController(model, view);
    guiController.handleSetData();

    assertEquals("Portfolio shares have been purchased\n", output.toString());
  }

  @Test
  public void testControllerModelSellPortfolioHolding() throws IOException {
    IModel2 model = new ModelImpl2();
    Appendable output = new StringBuilder();
    Readable input = new StringReader("CreatePortfolio TestPortfolio\n" +
            "BuyPortfolioHolding TestPortfolio GOOG 5 2024-06-05\n");
    ITextController controller = new TextControllerImpl2(input, new StringBuilder());
    controller.control(model);
    IGUIView view = new MockGUIView("SellPortfolioHolding TestPortfolio GOOG 5 2024-06-05",
            output);
    IGUIController guiController = new GUIController(model, view);
    guiController.handleSetData();
    assertEquals("Portfolio shares have been sold.\n", output.toString());
  }

  @Test
  public void testControllerModelCreatePortfolioHolding() throws IOException {
    IModel2 model = new ModelImpl2();
    Appendable output = new StringBuilder();
    IGUIView view = new MockGUIView("CreatePortfolio TestPortfolio",
            output);
    IGUIController guiController = new GUIController(model, view);
    guiController.handleSetData();
    assertEquals("Portfolio Created: TestPortfolio\n", output.toString());
  }
}
