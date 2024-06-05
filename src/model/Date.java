package model;

public class Date implements IDate {
  private final int year;
  private final int month;
  private final int day;

  public Date(int year, int month, int day) {
    this.year = year;
    this.month = month;
    this.day = day;
  }

  public int getYear() {
    return year;
  }

  public int getMonth() {
    return month;
  }

  public int getDay() {
    return day;
  }

  @Override
  public String dateToString() {
    return month + "/" + day + "/" + year;
  }
}
