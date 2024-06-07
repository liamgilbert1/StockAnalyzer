import org.junit.Before;
import org.junit.Test;

import controller.readers.AlphaVantageStreamReader;

import static org.junit.Assert.*;

public class AlphaVantageStreamReaderTest {

  @Test
  public void testGetReadable() {
    AlphaVantageStreamReader reader = new AlphaVantageStreamReader("GOOG");
    assertNotNull(reader.getReadable());
  }
}