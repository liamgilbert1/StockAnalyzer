package model.portfolio;
import java.time.LocalDate;

import model.stock.IStock;

public interface ITransaction {

  ITransaction addQuantity(double quantity);

  ITransaction removeQuantity(double quantity);

  IStock getStock();

  double getQuantity();

  double getValue();

  LocalDate getDate();

}
