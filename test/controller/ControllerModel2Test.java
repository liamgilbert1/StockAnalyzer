package controller;

import org.junit.Test;

import java.io.StringReader;

import controller.ControllerImpl;
import controller.ControllerImpl2;
import controller.IController;
import model.ModelImpl2;

import static org.junit.Assert.assertEquals;

public class ControllerModel2Test {

  @Test
  public void testControllerModelBuyPortfolioHolding() {
    Appendable output = new StringBuilder();
    Readable input = new StringReader("CreatePortfolio TestPortfolio\n" +
            "BuyPortfolioHolding TestPortfolio GOOG 10 2024-06-05\n");
    IController controller = new ControllerImpl2(input, output);
    controller.control(new ModelImpl2());
    assertEquals("", output.toString());

  }

  @Test
  public void testControllerModelSellPortfolioHolding() {
    Appendable output = new StringBuilder();
    Readable input = new StringReader("CreatePortfolio TestPortfolio\n" +
            "BuyPortfolioHolding TestPortfolio GOOG 5 2024-06-05\n" +
            "SellPortfolioHolding TestPortfolio GOOG 3 2024-06-05\n" +
            "GetPortfolioValue TestPortfolio 2024-06-05\n");
    IController controller = new ControllerImpl2(input, output);
    controller.control(new ModelImpl2());
  }

  @Test
  public void testControllerModelGetPortfolioValue() {
    Appendable output = new StringBuilder();
    Readable input = new StringReader("CreatePortfolio TestPortfolio\n" +
            "BuyPortfolioHolding TestPortfolio GOOG 50 2020-01-01\n" +
            "GetPortfolioValue TestPortfolio 2020-01-01\n");
    IController controller = new ControllerImpl2(input, output);
    controller.control(new ModelImpl2());
  }

  @Test
  public void testControllerModelGetPortfolioComposition() {
    Appendable output = new StringBuilder();
    Readable input = new StringReader("CreatePortfolio TestPortfolio\n" +
            "BuyPortfolioHolding TestPortfolio GOOG 50 2020-01-01\n" +
            "BuyPortfolioHolding TestPortfolio AMZN 10 2020-01-01\n" +
            "GetPortfolioComposition TestPortfolio 2020-01-01\n");
    IController controller = new ControllerImpl2(input, output);
    controller.control(new ModelImpl2());
  }

  @Test
  public void testControllerModelGetPerformanceOverTime() {
    Appendable output = new StringBuilder();
    Readable input = new StringReader("CreatePortfolio TestPortfolio\n" +
            "BuyPortfolioHolding TestPortfolio GOOG 50 2020-01-01\n" +
            "BuyPortfolioHolding TestPortfolio AMZN 10 2020-05-01\n" +
            "GetPerformanceOverTime TestPortfolio 2020-01-01 2020-01-30\n");
    IController controller = new ControllerImpl2(input, output);
    controller.control(new ModelImpl2());
  }
}

