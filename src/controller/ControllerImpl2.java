package controller;

import java.util.List;

import controller.commands.newCommands.BuyPortfolioHoldingCommand;
import controller.commands.newCommands.GetPortfolioDistributionCommand;
import controller.commands.newCommands.GetPortfolioCompositionCommand;
import controller.commands.newCommands.GetPortfolioValueCommand2;
import controller.commands.newCommands.PerformanceOverTimeCommand;
import controller.commands.newCommands.SellPortfolioHoldingCommand;

public class ControllerImpl2 extends ControllerImpl {
  public ControllerImpl2(Readable input, Appendable output) {
    super(input, output);
    commandMap.remove("AddPortfolioHolding");
    commandMap.put("BuyPortfolioHolding", () -> new BuyPortfolioHoldingCommand(output));
    commandMap.put("SellPortfolioHolding", () -> new SellPortfolioHoldingCommand(output));
    commandMap.put("GetPortfolioComposition" , () -> new GetPortfolioCompositionCommand(output));
    commandMap.remove("GetPortfolioValue");
    commandMap.put("GetPortfolioValue", () -> new GetPortfolioValueCommand2(output));
    commandMap.put("GetPortfolioDistribution",
            () -> new GetPortfolioDistributionCommand(output));
    commandMap.put("GetPerformanceOverTime",
            () -> new PerformanceOverTimeCommand(output));
    this.orderedCommands = List.of("GainOrLoss", "MovingAverage", "Crossover", "CreatePortfolio",
            "BuyPortfolioHolding", "SellPortfolioHolding", "GetPortfolioValue",
            "GetPortfolioComposition", "GetPortfolioDistribution", "GetPerformanceOverTime");
  }
}
