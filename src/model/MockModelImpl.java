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
    log.append("Calculate gain or loss with start date: " + startDate + " and endDate: "
            + endDate + "\n");
    return 0;
  }

  @Override
  public double movingAverage(String ticker, int days) {
    log.append("MovingAverage " + ticker + " " + days);
    return 0;
  }

  @Override
  public boolean crossOver(String ticker, int days) {
    log.append("Does stock " + ticker + " crossover over " + days + " days\n");
    return false;
  }
}
