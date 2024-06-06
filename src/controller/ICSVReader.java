package controller;

import java.time.LocalDate;

public interface ICSVReader extends IReader {
  /**
   * This method checks if the file contains the given date and the number of days before that.
   * @param date the date to check.
   * @param days the number of days to check.
   * @return true if the file contains the date and the number of days before that, false otherwise.
   */
  boolean checkContainsDates(LocalDate date, int days);

  LocalDate getMostRecentDate();
}
