package model.portfolio;

import model.IDate;
import model.stock.IStock;

public interface IHolding {
  IStock getStock();
  int getQuantity();
  double getValue(IDate date);

  IHolding addQuantity(int quantity);
}
