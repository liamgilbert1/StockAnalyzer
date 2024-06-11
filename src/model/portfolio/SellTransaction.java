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
  public double getValue() {
    return this.getStock().getClosePrice(this.getDate()) * this.quantity;
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
}
