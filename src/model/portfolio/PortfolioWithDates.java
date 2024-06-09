package model.portfolio;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PortfolioWithDates extends Portfolio {
  private final LocalDate date;

  public PortfolioWithDates(String name, LocalDate date) {
    super(name);
    this.date = date;
  }

  protected PortfolioWithDates(String name, LocalDate date, List<IHolding> holdings) {
    super(name, holdings);
    this.date = date;
  }

  @Override
  public IPortfolio addHolding(IHolding holding) {
    List<IHolding> newHoldings = new ArrayList<>(holdings);
    String newHoldingTicker = holding.getStock().getTicker();
    for (int i = 0; i < holdings.size(); i++) {
      String holdingTicker = holdings.get(i).getStock().getTicker();
      if (newHoldingTicker.equals(holdingTicker)) {
        newHoldings.set(i, holdings.get(i).addQuantity(holding.getQuantity()));
        return new PortfolioWithDates(name, date, newHoldings);
      }
    }
    newHoldings.add(holding);
    return new PortfolioWithDates(name, date, newHoldings);
  }


}
