package model;

import java.util.HashMap;
import java.util.Map;

public class ModelImpl implements IModel {
  private Map<String, IStock> stocks;

  public ModelImpl() {
    this.stocks = new HashMap<>();
  }

  @Override
  public void populate(Readable readable) {

  }

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
