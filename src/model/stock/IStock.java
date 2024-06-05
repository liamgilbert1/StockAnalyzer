package model.stock;

import model.IDate;

public interface IStock {
  double getPrice(IDate date);

  String getTicker();
}
