package model;

import java.util.HashMap;
import java.util.Map;

import model.stock.IStock;

public class ModelImpl implements IModel {
  private Map<String, IStock> stocks;

  public ModelImpl() {
    this.stocks = new HashMap<>();
  }

  @Override
  public void populate(Readable readable) {

  }

  /**
   * Calculate the gain or loss of the stock from the start date to the end date.
   * @param startDate the start date of the stock
   * @param endDate the end date of the stock
   * @return
   */
  @Override
  public double calculateGainOrLoss(String startDate, String endDate) {
    return 0;
  }

  @Override
  public double movingAverage(String ticker, int days) {
    return 0;
  }

  @Override
  public boolean crossOver(String ticker, int days) {
    return false;
  }
}
