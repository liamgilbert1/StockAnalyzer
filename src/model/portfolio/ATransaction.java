package model.portfolio;

import java.time.LocalDate;
import java.util.Objects;

import model.stock.IStock;

/**
 * This class represents an abstract transaction that implements the ITransaction interface. This
 * class contains the stock, date, and quantity of the transaction.
 */
public abstract class ATransaction implements ITransaction {
  private final IStock stock;
  private final LocalDate date;
  protected final double quantity;

  /**
   * Constructs an ATransaction object with the given stock, date, and quantity.
   * @param stock the stock to be used
   * @param date the date to be used
   * @param quantity the quantity to be used
   */
  public ATransaction(IStock stock, LocalDate date, double quantity) {
    this.stock = Objects.requireNonNull(stock);
    this.date = Objects.requireNonNull(date);
    checkQuantity(quantity);
    this.quantity = quantity;
  }

  /**
   * Checks if the quantity is valid.
   * @param quantity the quantity to check
   */
  protected void checkQuantity(double quantity) {
    if (quantity <= 0) {
      throw new IllegalArgumentException("Transaction quantity must be greater than 0.");
    }
  }

  /**
   * Gets the quantity of the transaction for the user.
   * @return the quantity of the transaction
   */
  @Override
  public double getQuantity() {
    return this.quantity;
  }

  /**
   * Gets the stock of the transaction for the user.
   * @return the stock of the transaction
   */
  @Override
  public IStock getStock() {
    return this.stock;
  }

  /**
   * Gets the date of the transaction for the user.
   * @return the date of the transaction
   */
  @Override
  public LocalDate getDate() {
    return this.date;
  }

  /**
   * Gets the value of the transaction for the user.
   * @return the value of the transaction
   */
  @Override
  public double getValue() {
    return this.getStock().getClosePrice(this.getDate()) * this.realQuantity();
  }


  /**
   * Gets the real quantity of the transaction for the user.
   * @return the real quantity of the transaction
   */
  @Override
  public abstract double realQuantity();

  /**
   * Overrides equals method to check if two transactions are equal.
   * @param o the object to compare
   * @return true if the transactions are equal, false otherwise
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof ATransaction)) {
      return false;
    }
    ATransaction that = (ATransaction) o;
    return this.getStock().equals(that.getStock()) && this.getDate().equals(that.getDate());
  }

  /**
   * Gets the hashcode of the ATransaction.
   * @return the hashcode of the ATransaction
   */
  @Override
  public int hashCode() {
    return Objects.hash(this.getStock(), this.getDate());
  }
}
