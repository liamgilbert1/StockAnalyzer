package model;

public class Holding implements IHolding {
  private final IStock stock;
  private final int quantity;

  public Holding(IStock stock, int quantity) {
    this.stock = stock;
    this.quantity = quantity;
  }

  public IStock getStock() {
    return stock;
  }

  public int getQuantity() {
    return quantity;
  }

  public double getValue(IDate date) {
    return stock.getPrice(date) * quantity;
  }

  public IHolding addQuantity(int quantity) {
    return new Holding(stock, this.quantity + quantity);
  }
}
