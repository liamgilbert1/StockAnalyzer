package controller;

import controller.commands.newCommands.GetDistributionOfPortfolioCommand;
import controller.commands.newCommands.GetPortfolioCompositionCommand;
import controller.commands.newCommands.GetPortfolioValueCommand2;

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
