package model.portfolio;

import java.time.LocalDate;
import model.stock.IStock;

public interface IHolding {
  IStock getStock();
  int getQuantity();
  double getValue(LocalDate date);
  IHolding addQuantity(int quantity);
}
