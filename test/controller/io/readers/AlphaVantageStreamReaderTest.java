package controller.io.readers;

import org.junit.Test;

import static org.junit.Assert.assertNotNull;

/**
 * Tests for AlphaVantageStreamReader.
 */
public class AlphaVantageStreamReaderTest {
  @Test
  public void testGetReadable() {
    AlphaVantageStreamReader reader = new AlphaVantageStreamReader("GOOG");
    assertNotNull(reader.getReadable());
  }
}