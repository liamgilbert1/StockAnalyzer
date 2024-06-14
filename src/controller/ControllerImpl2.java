package controller;

import java.util.List;

import controller.commands.newCommands.BuyPortfolioHoldingCommand;
import controller.commands.newCommands.GetPortfolioDistributionCommand;
import controller.commands.newCommands.GetPortfolioCompositionCommand;
import controller.commands.newCommands.LoadPortfolioCommand;
import controller.commands.newCommands.PerformanceOverTimeCommand;
import controller.commands.newCommands.RebalancePortfolioCommand;
import controller.commands.newCommands.SellPortfolioHoldingCommand;

/**
 * This class extends the controller for the stock market simulator to support new commands.
 * It implements the IController interface.
 * The controller is responsible for taking in user input and executing the appropriate commands.
 * The controller has a map of commands that maps a command name to a Supplier of that command.
 * The controller has a Readable object that takes in user input.
 * The controller has an Appendable object that outputs to the user.
 * The controller has a list of ordered commands that are executed in order.
 */
public class ControllerImpl2 extends ControllerImpl {

  /**
   * Constructs a ControllerImpl2 object with the given input and output.
   * @param input the input to be used
   * @param output the output to be used
   */
  public ControllerImpl2(Readable input, Appendable output) {
    super(input, output);
    commandMap.remove("AddPortfolioHolding");
    commandMap.put("BuyPortfolioHolding", () -> new BuyPortfolioHoldingCommand(output));
    commandMap.put("SellPortfolioHolding", () -> new SellPortfolioHoldingCommand(output));
    commandMap.put("GetPortfolioComposition" , () -> new GetPortfolioCompositionCommand(output));
    commandMap.put("GetPortfolioDistribution",
            () -> new GetPortfolioDistributionCommand(output));
    commandMap.put("RebalancePortfolio" , () -> new RebalancePortfolioCommand(output));
    commandMap.put("GetPerformanceOverTime",
            () -> new PerformanceOverTimeCommand(output));
    commandMap.put("LoadPortfolio", () -> new LoadPortfolioCommand(output));
    this.orderedCommands = List.of("GainOrLoss", "MovingAverage", "Crossover", "CreatePortfolio",
            "BuyPortfolioHolding", "SellPortfolioHolding", "GetPortfolioValue",
            "GetPortfolioComposition", "GetPortfolioDistribution", "RebalancePortfolio",
            "GetPerformanceOverTime", "LoadPortfolio");
  }
}
