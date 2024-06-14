package model.portfolio;

import java.time.LocalDate;
import java.util.Objects;

import model.stock.IStock;

/**
 * This class represents a BuyTransaction that extends the ATransaction class. This class contains
 * the stock, date, and quantity of the transaction.
 */
public class BuyTransaction extends ATransaction {

  /**
   * Constructs a BuyTransaction object with the given stock, quantity, and date.
   * @param stock the stock to be used
   * @param quantity the quantity to be used
   * @param date the date to be used
   */
  public BuyTransaction(IStock stock, double quantity, LocalDate date) {
    super(stock, date, quantity);
  }

  /**
   * Gets the real quantity of the transaction for the user.
   * @return the real quantity of the transaction
   */
  @Override
  public double realQuantity() {
    return this.quantity;
  }

  /**
   * Gets the action of the transaction for the user.
   * @return the action of the transaction
   */
  @Override
  public String action() {
    return "buy";
  }

  /**
   * Determines if this BuyTransaction is equal to the given object.
   * @param o the object to compare
   * @return true if the BuyTransaction is equal to the given object, false otherwise
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof BuyTransaction)) {
      return false;
    }
    BuyTransaction that = (BuyTransaction) o;
    return this.getStock().equals(that.getStock()) && this.getDate().equals(that.getDate())
            && this.quantity == that.quantity;
  }

  @Override
  public ITransaction getCopy() {
    return new BuyTransaction(this.getStock().getCopy(), this.getQuantity(),
            LocalDate.of(this.getDate().getYear(), this.getDate().getMonth(),
                    this.getDate().getDayOfMonth()));
  }

  /**
   * Gets the hashcode of the BuyTransaction.
   * @return the hashcode of the BuyTransaction
   */
  @Override
  public int hashCode() {
    return Objects.hash(this.getStock(), this.getDate(), this.quantity);
  }
}
