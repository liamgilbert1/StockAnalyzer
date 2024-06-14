package model.portfolio;

import java.time.LocalDate;

import model.stock.IStock;

public class SellTransaction extends ATransaction {
  private final double quantity;
  public SellTransaction(IStock stock, double quantity, LocalDate date) {
    super(stock, date);
    checkQuantity(quantity);
    this.quantity = quantity;
  }

  @Override
  public double getQuantity() {
    return this.quantity;
  }

  @Override
  public double realQuantity() {
    return -this.quantity;
  }

  @Override
  public ITransaction getCopy() {
    return new SellTransaction(this.getStock().getCopy(), this.getQuantity(),
            LocalDate.of(this.getDate().getYear(), this.getDate().getMonth(),
                    this.getDate().getDayOfMonth()));
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
}
