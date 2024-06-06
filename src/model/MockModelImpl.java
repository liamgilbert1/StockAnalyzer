package model;

import java.time.LocalDate;
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
  public double calculateGainOrLoss(String ticker, LocalDate startDate, LocalDate endDate) {
    log.append("GainOrLoss " + ticker + " " + startDate.toString() + " " + endDate.toString());
    return 0;
  }

  @Override
  public double movingAverage(String ticker, LocalDate date, int days) {
    log.append("MovingAverage " + ticker + " " + date.toString() + " " + days);
    return 0;
  }

  @Override
  public boolean crossOver(String ticker, LocalDate date, int days) {
    log.append("Crossover " + ticker + " " + date.toString() + " " + days);
    return false;
  }
}
