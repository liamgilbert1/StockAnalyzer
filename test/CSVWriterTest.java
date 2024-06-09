import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.StringReader;

import controller.writers.CSVWriter;
import controller.writers.IWriter;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Test cases for CSVWriter.
 */
public class CSVWriterTest {
  IWriter writer;
  Readable readable;

  @Before
  public void setUp() {
    writer = new CSVWriter();
    readable = new StringReader("readable");
  }

  @Test
  public void write() {
    File file = new File("TESTERFILE.csv");
    writer.write("TESTERFILE", readable);
    assertTrue(file.exists());
    boolean deleted = file.delete();
    assertTrue(deleted);
    assertFalse(file.exists());
  }
}