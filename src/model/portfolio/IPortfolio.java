package model.portfolio;

import java.time.LocalDate;
import java.util.List;

public interface IPortfolio {
  /**
   * Gets the value of the portfolio on the given date.
   * @param date the date to get the value of the portfolio on.
   * @return the value of the portfolio on the given date.
   */
  double getValue(LocalDate date);

  /**
   * Gets the name of the portfolio.
   * @return the name of the portfolio.
   */
  String getName();

  /**
   * Gets the stocks in the portfolio.
   * @return the stocks in the portfolio.
   */
  List<String> getStocks();

}
