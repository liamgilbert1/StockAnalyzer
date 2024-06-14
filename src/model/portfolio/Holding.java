package model.portfolio;

import java.time.LocalDate;
import java.util.Objects;

import model.stock.IStock;

/**
 * Represents a holding in a portfolio. A holding is a stock and the quantity of that stock that is
 * held in a portfolio. A holding can be used to calculate the value of the stock in the portfolio.
 */
public class Holding implements IHolding {
  private final IStock stock;
  private final double quantity;

  /**
   * Constructs a holding with the given stock and quantity.
   * @param stock the stock that is held in the portfolio.
   * @param quantity the quantity of the stock that is held in the portfolio.
   */
  public Holding(IStock stock, double quantity) {
    this.stock = stock;
    this.quantity = quantity;
  }

  /**
   * Gets the stock of the holding for the user.
   * @return the stock of the holding.
   */
  @Override
  public IStock getStock() {
    return stock;
  }

  /**
   * Gets the quantity of the holding for the user.
   * @return the quantity of the holding.
   */
  @Override
  public double getQuantity() {
    return quantity;
  }

  /**
   * Gets the value of the holding on the given date.
   * @param date the date to get the value of the holding on.
   * @return the value of the holding on the given date.
   */
  @Override
  public double getValue(LocalDate date) {
    return stock.getClosePrice(date) * quantity;
  }

  /**
   * Adds the given quantity to the holding.
   * @param quantity the quantity to add.
   * @return the holding with the added quantity.
   */
  @Override
  public IHolding addQuantity(double quantity) {
    return new Holding(stock,
            this.quantity + quantity);
  }

  /**
   * Determines if this holding is equal to the given object.
   * @param obj the object to compare.
   * @return true if the holding is equal to the given object, false otherwise.
   */
  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null || getClass() != obj.getClass()) {
      return false;
    }
    Holding holding = (Holding) obj;
    return quantity == holding.quantity && stock.equals(holding.stock);
  }

  /**
   * Gets the hashcode of the holding.
   * @return the hashcode of the holding.
   */
  @Override
  public int hashCode() {
    return Objects.hash(stock, quantity);
  }
}
