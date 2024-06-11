package model.portfolio;

import java.time.LocalDate;
import java.util.List;

/**
 * Represents a portfolio of holdings. A portfolio is a collection of holdings, and can be used to
 * calculate the value of the holdings in the portfolio. A portfolio can also be used to add a
 * holding to the portfolio.
 */
public interface IPortfolioWithHoldings extends IPortfolio {

  /**
   * Adds the given holding to the portfolio.
   * @param holding the holding to add.
   * @return the portfolio with the given holding added.
   */
  IPortfolioWithHoldings addHolding(IHolding holding);

  /**
   * Gets the holdings in the portfolio.
   * @return the holdings in the portfolio.
   */
  List<IHolding> getHoldings();
}
