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

  @Override
  public IStock getStock() {
    return stock;
  }

  @Override
  public double getQuantity() {
    return quantity;
  }

  @Override
  public double getValue(LocalDate date) {
    return stock.getClosePrice(date) * quantity;
  }

  @Override
  public IHolding addQuantity(double quantity) {
    return new Holding(stock,
            this.quantity + quantity);
  }

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

  @Override
  public int hashCode() {
    return Objects.hash(stock, quantity);
  }
}
