package model;

import java.util.Objects;

public class MockModelImpl implements IModel {
  private final StringBuilder log;

  public MockModelImpl(StringBuilder log) {
    this.log = Objects.requireNonNull(log);
  }

  @Override
  public void populate(Readable readable) {

  }

  @Override
  public double calculateGainOrLoss(String startDate, String endDate) {
    log.append("GainOrLoss " + startDate + " " + endDate);
    return 0;
  }

  @Override
  public double movingAverage(String ticker, int days) {
    log.append("MovingAverage " + ticker + " " + days);
    return 0;
  }

  @Override
  public boolean crossOver(String ticker, int days) {
    log.append("Crossover " + ticker + " " + days);
    return false;
  }
}
