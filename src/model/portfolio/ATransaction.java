package model.portfolio;

import java.time.LocalDate;
import java.util.Objects;

import model.stock.IStock;

public abstract class ATransaction implements ITransaction {
  private final IStock stock;
  private final LocalDate date;

  protected final double quantity;

  public ATransaction(IStock stock, LocalDate date, double quantity) {
    this.stock = Objects.requireNonNull(stock);
    this.date = Objects.requireNonNull(date);
    checkQuantity(quantity);
    this.quantity = quantity;
  }

  protected void checkQuantity(double quantity) {
    if (quantity <= 0) {
      throw new IllegalArgumentException("Transaction quantity must be greater than 0.");
    }
  }

  @Override
  public double getQuantity() {
    return this.quantity;
  }

  @Override
  public IStock getStock() {
    return this.stock;
  }

  @Override
  public LocalDate getDate() {
    return this.date;
  }

  @Override
  public double getValue() {
    return this.getStock().getClosePrice(this.getDate()) * this.realQuantity();
  }

  @Override
  public abstract double realQuantity();

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof ATransaction)) {
      return false;
    }
    ATransaction that = (ATransaction) o;
    return this.getStock().equals(that.getStock()) && this.getDate().equals(that.getDate());
  }
}
