package model;

import java.time.LocalDate;
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
  public double calculateGainOrLoss(String ticker, String startDate, String endDate) {
    LocalDate start = LocalDate.parse(startDate);
    LocalDate end = LocalDate.parse(endDate);

    return this.stocks.get(ticker).getPrice(start) - this.stocks.get(ticker).getPrice(end);

  }

  /**
   * Calculate the moving average of the stock for the given number of days.
   * @param ticker the date of the stock
   * @param date the date of the stock
   * @param days the number of days to calculate the moving average
   * @return the moving average of the stock
   */
  @Override
  public double movingAverage(String ticker, String date, int days) {
    LocalDate startDate = LocalDate.parse(date);
    double total = 0.0;

    for (int i = 0; i < days; i++) {
      total += this.stocks.get(ticker).getPrice(startDate.minusDays(i));
    }

    return total / days;
  }

  /**
   * Determine if the stock has crossed over for the given number of days.
   * @param ticker the ticker of the stock
   * @param days the number of days to determine if the stock has crossed over
   * @return true if the stock has crossed over, false otherwise
   */
  @Override
  public boolean crossOver(String ticker, String date, int days) {
    return this.stocks.get(ticker).getPrice(LocalDate.parse(date))
            > this.movingAverage(ticker, date, days);
  }
}
