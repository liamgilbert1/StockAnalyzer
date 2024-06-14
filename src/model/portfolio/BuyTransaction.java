package model.portfolio;

import java.time.LocalDate;
import java.util.Objects;

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

  @Override
  public ITransaction getCopy() {
    return new BuyTransaction(this.getStock().getCopy(), this.getQuantity(),
            LocalDate.of(this.getDate().getYear(), this.getDate().getMonth(),
                    this.getDate().getDayOfMonth()));
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.getStock(), this.getDate(), this.quantity);
  }
}
