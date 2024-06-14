package controller.IO.writers;

import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.StringReader;

import controller.IO.writers.CSVWriter;
import controller.IO.writers.IStockDataWriter;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Test cases for CSVWriter.
 */
public class CSVWriterTest {
  IStockDataWriter writer;
  Readable readable;

  @Before
  public void setUp() {
    writer = new CSVWriter();
    readable = new StringReader("readable");
  }

  @Test
  public void write() {
    writer.write("TICKER", readable);
    File file = new File("out/production/stockData/TICKER.csv");
    assertTrue(file.exists());
    boolean deleted = file.delete();
    assertTrue(deleted);
    assertFalse(file.exists());
  }
}