package model.portfolio;

import java.time.LocalDate;

import model.stock.IStock;

public class BuyTransaction extends ATransaction {
  public BuyTransaction(IStock stock, int quantity, LocalDate date) {
    super(stock, quantity, date);
  }
}
