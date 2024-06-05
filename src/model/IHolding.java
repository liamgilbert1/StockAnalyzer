package model;

public interface IHolding {
  IStock getStock();
  int getQuantity();
  double getValue(IDate date);

  IHolding addQuantity(int quantity);
}
