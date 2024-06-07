package model.portfolio;

import java.time.LocalDate;
import java.util.Objects;

import model.stock.IStock;

public class Holding implements IHolding {
  private final IStock stock;
  private final double quantity;

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
