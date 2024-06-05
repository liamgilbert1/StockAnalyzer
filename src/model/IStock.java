package model;

public interface IStock {
  double getPrice(IDate date);

  String getTicker();
}
