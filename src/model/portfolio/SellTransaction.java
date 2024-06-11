package model.portfolio;

import java.time.LocalDate;

import model.stock.IStock;

public class SellTransaction extends ATransaction {
  public SellTransaction(IStock stock, double quantity, LocalDate date) {
    super(stock, quantity, date);
  }
}
