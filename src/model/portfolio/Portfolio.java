package model.portfolio;

import java.util.ArrayList;
import java.util.List;

import model.IDate;

public class Portfolio implements IPortfolio {
  private final String name;
  private final List<IHolding> holdings;

  public Portfolio(String name) {
    this.name = name;
    holdings = new ArrayList<>();
  }

  private Portfolio(String name, List<IHolding> holdings) {
    this.name = name;
    this.holdings = holdings;
  }

  /**
   * Add a holding to the portfolio. If a holding for the same stock already exists, the quantity of
   * the existing holding is increased by the quantity of the new holding.
   * @param holding the holding to add.
   */
  public IPortfolio addHolding(IHolding holding) {
    List<IHolding> newHoldings = new ArrayList<>(holdings);
    String newHoldingTicker = holding.getStock().getTicker();
    for (int i = 0; i < holdings.size(); i++) {
      String holdingTicker = holdings.get(i).getStock().getTicker();
      if (newHoldingTicker.equals(holdingTicker)) {
        newHoldings.set(i, holdings.get(i).addQuantity(holding.getQuantity()));
        return new Portfolio(name, newHoldings);
      }
    }
    newHoldings.add(holding);
    return new Portfolio(name, newHoldings);
  }

  public double getValue(IDate date) {
    int value = 0;
    for (IHolding holding : holdings) {
      value += holding.getValue(date);
    }
    return value;
  }
}
