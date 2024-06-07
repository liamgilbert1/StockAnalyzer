package model.portfolio;

import java.time.LocalDate;
import java.util.List;

/**
 * Represents a portfolio of holdings. A portfolio is a collection of holdings, and can be used to
 * calculate the value of the holdings in the portfolio. A portfolio can also be used to add a
 * holding to the portfolio.
 */
public interface IPortfolio {

  /**
   * Adds the given holding to the portfolio.
   * @param holding the holding to add.
   * @return the portfolio with the given holding added.
   */
  IPortfolio addHolding(IHolding holding);

  /**
   * Gets the value of the portfolio on the given date.
   * @param date the date to get the value of the portfolio on.
   * @return the value of the portfolio on the given date.
   */
  double getValue(LocalDate date);

  /**
   * Gets the holdings in the portfolio.
   * @return the holdings in the portfolio.
   */
  List<IHolding> getHoldings();

  /**
   * Gets the name of the portfolio.
   * @return the name of the portfolio.
   */
  String getName();

  /**
   * Gets the stocks in the portfolio.
   * @return the stocks in the portfolio.
   */
  List<String> getStocks();
}
