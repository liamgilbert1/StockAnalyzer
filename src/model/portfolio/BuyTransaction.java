package model.portfolio;

import java.time.LocalDate;

import model.stock.IStock;

public class BuyTransaction extends ATransaction {
  public BuyTransaction(IStock stock, double quantity, LocalDate date) {
    super(stock, date, quantity);
  }

  @Override
  public double realQuantity() {
    return this.quantity;
  }

  @Override
  public String action() {
    return "buy";
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof BuyTransaction)) {
      return false;
    }
    BuyTransaction that = (BuyTransaction) o;
    return this.getStock().equals(that.getStock()) && this.getDate().equals(that.getDate())
            && this.quantity == that.quantity;
  }
}
