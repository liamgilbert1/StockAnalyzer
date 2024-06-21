package controller.textcontroller;

import java.util.List;

import controller.commands.newcommands.BuyPortfolioHoldingCommand;
import controller.commands.newcommands.GetPortfolioDistributionCommand;
import controller.commands.newcommands.GetPortfolioCompositionCommand;
import controller.commands.newcommands.LoadPortfolioCommand;
import controller.commands.newcommands.PerformanceOverTimeCommand;
import controller.commands.newcommands.RebalancePortfolioCommand;
import controller.commands.newcommands.SellPortfolioHoldingCommand;

/**
 * This class extends the controller for the stock market simulator to support new commands.
 * It implements the IController interface.
 * The controller is responsible for taking in user input and executing the appropriate commands.
 * The controller has a map of commands that maps a command name to a Supplier of that command.
 * The controller has a Readable object that takes in user input.
 * The controller has an Appendable object that outputs to the user.
 * The controller has a list of ordered commands that are executed in order.
 */
public class TextControllerImpl2 extends TextControllerImpl {

  /**
   * Constructs a ControllerImpl2 object with the given input and output.
   *
   * @param input  the input to be used
   * @param output the output to be used
   */
  public TextControllerImpl2(Readable input, Appendable output) {
    super(input, output);
    removeCommand("AddPortfolioHolding");
    addCommand("BuyPortfolioHolding", () -> new BuyPortfolioHoldingCommand(output));
    addCommand("SellPortfolioHolding", () -> new SellPortfolioHoldingCommand(output));
    addCommand("GetPortfolioComposition", () ->
            new GetPortfolioCompositionCommand(output));
    addCommand("GetPortfolioDistribution", () ->
            new GetPortfolioDistributionCommand(output));
    addCommand("RebalancePortfolio", () -> new RebalancePortfolioCommand(output));
    addCommand("GetPerformanceOverTime", () -> new PerformanceOverTimeCommand(output));
    addCommand("LoadPortfolio", () -> new LoadPortfolioCommand(output));
    this.orderedCommands = List.of("GainOrLoss", "MovingAverage", "Crossover",
            "CreatePortfolio", "BuyPortfolioHolding", "SellPortfolioHolding", "GetPortfolioValue",
            "GetPortfolioComposition", "GetPortfolioDistribution", "RebalancePortfolio",
            "GetPerformanceOverTime", "LoadPortfolio");
  }
}
