package model.portfolio;

import java.util.ArrayList;
import java.util.List;

import java.time.LocalDate;
import java.util.Objects;

/**
 * Represents a portfolio of holdings. A portfolio is a collection of holdings, and can be used to
 * calculate the value of the holdings in the portfolio. A portfolio can also be used to add a
 * holding to the portfolio.
 */
public class Portfolio implements IPortfolioWithHoldings {
  protected final String name;
  protected final List<IHolding> holdings;

  /**
   * Constructs a portfolio with the given name. The portfolio is initially empty.
   * @param name the name of the portfolio.
   */
  public Portfolio(String name) {
    this.name = name;
    holdings = new ArrayList<>();
  }

  /**
   * Constructs a portfolio with the given name and holdings.
   * @param name the name of the portfolio.
   * @param holdings the holdings in the portfolio.
   */
  protected Portfolio(String name, List<IHolding> holdings) {
    this.name = name;
    this.holdings = holdings;
  }

  /**
   * Adds the given holding to the portfolio. If the holding is already in the portfolio, the
   * @param holding the holding to add.
   * @return the portfolio with the given holding added.
   */
  @Override
  public IPortfolioWithHoldings addHolding(IHolding holding) {
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

  /**
   * Gets the value of the portfolio on the given date.
   * @param date the date to get the value of the portfolio on.
   * @return the value of the portfolio on the given date.
   */
  @Override
  public double getValue(LocalDate date) {
    double value = 0;
    for (IHolding holding : holdings) {
      value += holding.getValue(date);
    }
    return value;
  }

  /**
   * Gets the holdings in the portfolio.
   * @return the holdings in the portfolio.
   */
  @Override
  public List<IHolding> getHoldings() {
    return new ArrayList<>(holdings);
  }

  /**
   * Gets the name of the portfolio.
   * @return the name of the portfolio.
   */
  @Override
  public String getName() {
    return name;
  }

  /**
   * Gets the stocks in the portfolio.
   * @return the stocks in the portfolio.
   */
  @Override
  public List<String> getStocks() {
    List<String> stocks = new ArrayList<>();
    for (IHolding holding : holdings) {
      stocks.add(holding.getStock().getTicker());
    }
    return stocks;
  }

  /**
   * Determines if this portfolio is equal to the given object.
   * @param obj the object to compare.
   * @return true if the portfolio is equal to the given object, false otherwise.
   */
  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null || getClass() != obj.getClass()) {
      return false;
    }
    Portfolio portfolio = (Portfolio) obj;
    return name.equals(portfolio.name) && holdings.equals(portfolio.holdings);
  }

  /**
   * Gets the hash code of the portfolio.
   * @return the hash code of the portfolio.
   */
  @Override
  public int hashCode() {
    return Objects.hash(name, holdings);
  }
}
