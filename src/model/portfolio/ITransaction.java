package model.portfolio;
import java.time.LocalDate;

import model.stock.IStock;

public interface ITransaction {

  IStock getStock();

  double getQuantity();

  double getValue();

  LocalDate getDate();

  ITransaction getCopy();

  String action();

  double realQuantity();
}
