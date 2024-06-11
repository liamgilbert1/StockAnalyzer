package model.portfolio;

import java.time.LocalDate;
import java.util.Objects;

import model.stock.IStock;

public abstract class ATransaction implements ITransaction{
    private final IStock stock;
    private final double quantity;
    private final LocalDate date;

    public ATransaction(IStock stock, double quantity, LocalDate date) {
      if (quantity < 0) {
        throw new IllegalArgumentException("Amount of shares can't be negative");
      }
      this.stock = Objects.requireNonNull(stock);
      this.quantity = quantity;
      this.date = Objects.requireNonNull(date);
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
