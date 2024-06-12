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
}
