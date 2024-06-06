package model.portfolio;

import java.time.LocalDate;

import model.stock.IStock;

public class Holding implements IHolding {
  private final IStock stock;
  private final int quantity;

  public Holding(IStock stock, int quantity) {
    this.stock = stock;
    this.quantity = quantity;
  }

  @Override
  public IStock getStock() {
    return stock;
  }

  @Override
  public int getQuantity() {
    return quantity;
  }

  @Override
  public double getValue(LocalDate date) {
    return stock.getPrice(date) * quantity;
  }

  @Override
  public IHolding addQuantity(int quantity) {
    return new Holding(stock, this.quantity + quantity);
  }
}
