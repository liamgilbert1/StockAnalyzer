package controller.io.readers;

import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static model.stock.StockDataPoint.OPEN;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 * Test cases for CSVReader.
 */
public class CSVReaderTest {
  CSVReader reader;

  @Before
  public void setUp() {
    reader = new CSVReader("TEST");
  }

  @Test
  public void getReadable() {
    Readable readable;
    try {
      readable = reader.getReadable();
      assertNotNull(readable);
    } catch (Exception e) {
      fail();
    }
  }

  @Test
  public void checkContainsDates() {
    assertTrue(reader.checkContainsDates(LocalDate.of(2024, 6, 1), 1));
    try {
      reader.checkContainsDates(LocalDate.of(2023, 1, 1), 2);
      fail();
    } catch (Exception e) {
      assertEquals("Most recent date not found", e.getMessage());
    }
  }

  @Test
  public void checkContainsDateRange() {
    assertTrue(reader.checkContainsDateRange(LocalDate.of(2024, 6, 1),
            LocalDate.of(2024, 6, 5)));
    try {
      reader.checkContainsDateRange(LocalDate.of(2023, 1, 1),
              LocalDate.of(2023, 1, 5));
      fail();
    } catch (Exception e) {
      assertEquals("Most recent date not found", e.getMessage());
    }
  }

  @Test
  public void getMostRecentDate() {
    assertEquals(LocalDate.of(2024, 6, 5), reader.getMostRecentDate());
  }

  @Test
  public void getStockData() {
    assertEquals(416.75,
            reader.getStockData(LocalDate.of(2024, 6, 1), OPEN), .01);
  }

  @Test
  public void getDataAcrossDays() {
    List<String> expected = new ArrayList<>(List.of("417.8100", "412.4300", "415.5250"));
    assertEquals(expected,
            reader.getDataAcrossDays(LocalDate.of(2024, 6, 1),
                    LocalDate.of(2024, 6, 5), OPEN));
  }

  @Test
  public void testGetDataAcrossDays() {
    List<String> expected = new ArrayList<>(List.of("417.8100", "412.4300", "415.5250"));
    assertEquals(expected,
            reader.getDataAcrossDays(LocalDate.of(2024, 6, 5),
                    3, OPEN));
  }
}