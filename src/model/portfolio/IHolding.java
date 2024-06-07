package model.portfolio;

import java.time.LocalDate;
import model.stock.IStock;

/**
 * Represents a holding in a portfolio. A holding is a stock and the quantity of that stock that is
 * held in a portfolio. A holding can be used to calculate the value of the stock in the portfolio.
 */
public interface IHolding {

  /**
   * Gets the stock that is held in the portfolio.
   * @return the stock that is held in the portfolio.
   */
  IStock getStock();

  /**
   * Gets the quantity of the stock that is held in the portfolio.
   * @return the quantity of the stock that is held in the portfolio.
   */
  double getQuantity();

  /**
   * Gets the value of the stock in the portfolio on the given date.
   * @param date the date to get the value of the stock on.
   * @return the value of the stock in the portfolio on the given date.
   */
  double getValue(LocalDate date);

  /**
   * Adds the given quantity to the quantity of the stock that is held in the portfolio.
   * @param quantity the quantity to add.
   * @return the holding with the given quantity added to the quantity of the stock that is held in
   *         the portfolio.
   */
  IHolding addQuantity(double quantity);
}
