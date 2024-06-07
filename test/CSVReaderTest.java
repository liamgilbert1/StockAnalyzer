import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;

import controller.readers.CSVReader;

import static model.stock.StockDataPoint.OPEN;
import static model.stock.StockDataPoint.VOLUME;
import static org.junit.Assert.*;

public class CSVReaderTest {

  CSVReader reader;
  @Before
  public void setUp() {
    reader = new CSVReader("TEST");
  }

  @Test
  public void getReadable() {
  }

  @Test
  public void checkContainsDates() {
  }

  @Test
  public void checkContainsDateRange() {
  }

  @Test
  public void getMostRecentDate() {
  }

  @Test
  public void getStockData() {
    System.out.println(reader.getStockData(LocalDate.of(2024, 6, 1), VOLUME));
  }

  @Test
  public void getDataAcrossDays() {
  }

  @Test
  public void testGetDataAcrossDays() {
  }
}