import org.junit.Before;
import org.junit.Test;

import model.Date;

import static org.junit.Assert.*;

public class DateTest {

  Date date1;
  @Before
  public void setUp() {
    date1 = new Date(2019, 1, 15);
  }

  @Test
  public void getYear() {
    assertEquals(2019, date1.getYear());
  }

  @Test
  public void getMonth() {
    assertEquals(1, date1.getMonth());
  }

  @Test
  public void getDay() {
    assertEquals(15, date1.getDay());
  }

  @Test
  public void dateToString() {
    assertEquals("1/15/2019", date1.dateToString());
  }
}