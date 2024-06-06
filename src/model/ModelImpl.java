package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import controller.CSVReader;
import controller.ICSVReader;
import controller.IReader;
import model.portfolio.IPortfolio;
import model.stock.IStock;
import model.stock.Stock;
import model.stock.StockData;

public class ModelImpl implements IModel {
  private List<IPortfolio> portfolios;

  public ModelImpl() {
    this.portfolios = new ArrayList<>();
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
  public double calculateGainOrLoss(String ticker, LocalDate startDate, LocalDate endDate) {
    ICSVReader reader = new CSVReader(ticker);
    return reader.getPrice(endDate) - reader.getPrice(startDate);
  }

  /**
   * Calculate the moving average of the stock for the given number of days.
   * @param ticker the date of the stock
   * @param date the date of the stock
   * @param days the number of days to calculate the moving average
   * @return the moving average of the stock
   */
  @Override
  public double movingAverage(String ticker, LocalDate date, int days) {
    ICSVReader reader = new CSVReader(ticker);
    List<Double> prices = reader.getPricesAcrossDays(date, days);
    double total = 0;
    for (double price : prices) {
      total += price;
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
  public boolean crossOver(String ticker, LocalDate date, int days) {
    ICSVReader reader = new CSVReader(ticker);
    return reader.getPrice(date) > movingAverage(ticker, date, days);
  }
}
