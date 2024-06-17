package model.portfolio;

import java.time.LocalDate;

import model.stock.IStock;

/**
 * Represents a transaction in a portfolio. A transaction is a stock and the quantity of that stock
 * that is bought or sold in a portfolio. A transaction can be used to calculate the value of the
 * stock in the portfolio.
 */
public interface ITransaction {

  /**
   * Gets the stock of the transaction for the user.
   * @return the stock of the transaction
   */
  IStock getStock();

  /**
   * Gets the quantity of the transaction for the user.
   * @return the quantity of the transaction
   */
  double getQuantity();

  /**
   * Gets the value of the transaction for the user.
   * @return the value of the transaction
   */
  double getValue();

  /**
   * Gets the date of the transaction for the user.
   * @return the date of the transaction
   */
  LocalDate getDate();

  /**
   * Gets a copy of the transaction.
   * @return a copy of the transaction
   */
  ITransaction getCopy();

  /**
   * Gets the action of the transaction for the user.
   * @return the action of the transaction
   */
  String action();

  /**
   * Gets the real quantity of the transaction for the user.
   * @return the real quantity of the transaction
   */
  double realQuantity();

}
