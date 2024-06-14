package controller.IO;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class IOUtilsTest {

  @Test
  public void testGetFile() {
    String path = "test/resources/testFile";
    IOUtils.getFile(path, ".txt", "test");
    assertEquals("/Users/liamgilbert/Desktop/OOD/StocksHW/out/production/test/test" +
            "/resources/testFile.txt", IOUtils.getFile(path, ".txt",
            "test").getPath());

  }
}
