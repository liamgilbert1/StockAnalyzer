package model.portfolio;

import java.time.LocalDate;

import model.stock.IStock;

public class BuyTransaction extends ATransaction {
  private final int quantity;
  public BuyTransaction(IStock stock, int quantity, LocalDate date) {
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
    return this.quantity;
  }

  @Override
  public ITransaction getCopy() {
    return new BuyTransaction(this.getStock().getCopy(), quantity,
            LocalDate.of(this.getDate().getYear(), this.getDate().getMonth(),
                    this.getDate().getDayOfMonth()));
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
