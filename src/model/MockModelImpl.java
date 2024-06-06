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
  public double calculateGainOrLoss(String ticker, String startDate, String endDate) {
    log.append("GainOrLoss " + ticker + " " + startDate + " " + endDate);
    return 0;
  }

  @Override
  public double movingAverage(String ticker, String date, int days) {
    log.append("MovingAverage " + ticker + " " + date + " " + days);
    return 0;
  }

  @Override
  public boolean crossOver(String ticker, String date, int days) {
    log.append("Crossover " + ticker + " " + date + " " + days);
    return false;
  }
}
