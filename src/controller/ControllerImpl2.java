package controller;

import java.util.List;

import controller.commands.GetDistributionOfPortfolioCommand;
import controller.commands.GetPortfolioCompositionCommand;
import controller.commands.GetPortfolioValueCommand2;
import controller.commands.SellPortfolioHoldingCommand;

public class ControllerImpl2 extends ControllerImpl {
  public ControllerImpl2(Readable input, Appendable output) {
    super(input, output);
    commandMap.remove("AddPortfolioHolding");
    commandMap.put("BuyPortfolioHolding", () -> new BuyPortfolioHoldingCommand(output));
    commandMap.put("SellPortfolioHolding", () -> new SellPortfolioHoldingCommand(output));
    commandMap.put("GetPortfolioComposition" , () -> new GetPortfolioCompositionCommand(output));
    commandMap.remove("GetPortfolioValue");
    commandMap.put("GetPortfolioValue", () -> new GetPortfolioValueCommand2(output));
    commandMap.put("GetDistributionOfPortfolio",
            () -> new GetDistributionOfPortfolioCommand(output));
    orderedCommands.remove("AddPortfolioHolding");
    orderedCommands.remove("GetPortfolioValue");
    orderedCommands.add("BuyPortfolioHolding");
    orderedCommands.add("SellPortfolioHolding");
    orderedCommands.add("GetPortfolioComposition");
    orderedCommands.add("GetPortfolioValue");
    orderedCommands.add("GetDistributionOfPortfolio");
  }
}
