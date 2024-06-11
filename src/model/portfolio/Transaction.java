package model.portfolio;

import java.time.LocalDate;
import java.util.Objects;

import model.stock.IStock;

public class Transaction implements ITransaction{
    private final IStock stock;
    private final double quantity;
    private final LocalDate date;

    public Transaction(IStock stock, double quantity, LocalDate date) {
      if (quantity < 0) {
        throw new IllegalArgumentException("Amount of shares can't be negative");
      }
      this.stock = Objects.requireNonNull(stock);
      this.quantity = quantity;
      this.date = Objects.requireNonNull(date);
    }

    @Override
    public ITransaction addQuantity(double quantity) {
      return new Transaction(this.stock, this.quantity + quantity, this.date);
    }

    @Override
    public ITransaction removeQuantity(double quantity) {
      if (quantity > this.quantity) {
        throw new IllegalArgumentException("Not enough shares to sell");
      }
      return new Transaction(this.stock, this.quantity - quantity, this.date);
    }

    @Override
    public IStock getStock() {
      return this.stock;
    }

    @Override
    public double getQuantity() {
      return this.quantity;
    }

    @Override
    public double getValue() {
      return this.stock.getClosePrice(this.date) * this.quantity;
    }

    @Override
    public LocalDate getDate() {
      return this.date;
    }
}
