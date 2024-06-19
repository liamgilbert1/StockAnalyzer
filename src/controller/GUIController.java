package controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.function.Supplier;

import controller.commands.CreatePortfolioCommand;
import controller.commands.GetPortfolioValueCommand;
import controller.commands.ICommand;
import controller.commands.newcommands.BuyPortfolioHoldingCommand;
import controller.commands.newcommands.GetPortfolioCompositionCommand;
import controller.commands.newcommands.LoadPortfolioCommand;
import controller.commands.newcommands.SellPortfolioHoldingCommand;
import model.IModel2;
import view.IGUIView;
import view.IViewListener;

public class GUIController implements IGUIController, IViewListener {
  private final IModel2 model;
  private final IGUIView view;

  private final Map<String, Supplier<ICommand>> commandMap;
  public GUIController(IModel2 model, IGUIView view) {
    this.model = model;
    this.view = view;
    view.addViewListener(this);
    commandMap = new HashMap<>();
    commandMap.put("CreatePortfolio", () -> new CreatePortfolioCommand(view));
    commandMap.put("BuyPortfolioHolding", () -> new BuyPortfolioHoldingCommand(view));
    commandMap.put("SellPortfolioHolding", () -> new SellPortfolioHoldingCommand(view));
    commandMap.put("GetPortfolioValue", () -> new GetPortfolioValueCommand(view));
    commandMap.put("GetPortfolioComposition", () -> new GetPortfolioCompositionCommand(view));
    commandMap.put("LoadPortfolio", () -> new LoadPortfolioCommand(view));
  }

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

  @Override
  public void handleSetData() {
    Scanner scanner = new Scanner(view.getData());
    while (scanner.hasNext()) {
      String command = scanner.next();
      if (command != null) {
        try {
          ICommand commandToRun = this.commandMap.get(command).get();
          commandToRun.execute(model, scanner);
        } catch (Exception e) {
          System.out.print(command);
          throw new IllegalArgumentException("Failed to process command.");
        }
      }
    }
    view.requestFocus();
  }
}
