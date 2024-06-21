package controller.guicontroller;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import controller.AController;
import controller.commands.CreatePortfolioCommand;
import controller.commands.GetPortfolioValueCommand;
import controller.commands.newcommands.BuyPortfolioHoldingCommand;
import controller.commands.newcommands.GetPortfolioCompositionCommand;
import controller.commands.newcommands.LoadPortfolioCommand;
import controller.commands.newcommands.SellPortfolioHoldingCommand;
import model.IModel2;
import view.guiview.IGUIView;

/**
 * Represents a controller that can be used to interact with a GUI view. This controller can be used
 * to create a portfolio, load a portfolio, purchase a holding to a portfolio, sell a holding in a
 * portfolio, get the value of a portfolio, and get the composition of a portfolio.
 */
public class GUIController extends AController implements IGUIController {
  private final IModel2 model;
  private final IGUIView view;

  /**
   * Constructs a new GUIController object.
   * @param model the model to perform commands on
   * @param view the view to interact with
   */
  public GUIController(IModel2 model, IGUIView view) {
    super();
    this.model = model;
    this.view = view;
    view.addViewListener(this);
    addCommand("CreatePortfolio", () -> new CreatePortfolioCommand(view));
    addCommand("BuyPortfolioHolding", () -> new BuyPortfolioHoldingCommand(view));
    addCommand("SellPortfolioHolding", () -> new SellPortfolioHoldingCommand(view));
    addCommand("GetPortfolioValue", () -> new GetPortfolioValueCommand(view));
    addCommand("GetPortfolioComposition",
            () -> new GetPortfolioCompositionCommand(view));
    addCommand("LoadPortfolio", () -> new LoadPortfolioCommand(view));
  }

  /**
   * Handles the get data command from the view.
   * This will get the portfolio names from the model and send them to the view.
   */
  @Override
  public void handleGetData() {
    List<String> portfolioNames = model.getPortfolioNames();
    StringBuilder portfolioNamesString = new StringBuilder();
    for (String name : portfolioNames) {
      portfolioNamesString.append(name).append(" ");
    }
    this.view.setData(portfolioNamesString.toString());
    this.view.requestFocus();
  }

  /**
   * Handles the set data command from the view.
   * This will get the data from the view and send it to the model to execute the command.
   * @throws IOException if the command fails to execute
   */
  @Override
  public void handleSetData() throws IOException {
    view.requestFocus();
    String data = view.getData();
    Scanner scanner = new Scanner(data);
    if (scanner.hasNext()) {
      String command = scanner.next();
      runCommand(command, model, scanner, view);
    }
  }
}
