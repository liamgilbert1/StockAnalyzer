package model.portfolio;

import java.time.LocalDate;
import java.util.Objects;

import model.stock.IStock;

public class SellTransaction extends ATransaction {
  public SellTransaction(IStock stock, double quantity, LocalDate date) {
    super(stock, date, quantity);
  }

  @Override
  public double realQuantity() {
    return -this.quantity;
  }

  @Override
  public String action() {
    return "sell";
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof SellTransaction)) {
      return false;
    }
    SellTransaction that = (SellTransaction) o;
    return this.getStock().equals(that.getStock()) && this.getDate().equals(that.getDate())
            && this.quantity == that.quantity;
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.getStock(), this.getDate(), this.quantity);
  }
}
