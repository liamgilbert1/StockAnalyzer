package controller.IO.writers;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.Scanner;
import controller.IO.IOUtils;

/**
 * This class represents a CSVWriter object. It writes the data to a CSV file.
 * The data is written in the format of a CSV file.
 * The data is written to a file with the ticker as the name of the file.
 */
public class CSVWriter implements IStockDataWriter {
  /**
   * Writes the data to a CSV file.
   * @param ticker the ticker of the stock
   * @param readable the data to write
   */
  public void write(String ticker, Readable readable) {
    File file = IOUtils.getFile(ticker, ".csv", "stockData");

    try (Writer writer = new FileWriter(file)) {
      Scanner scanner = new Scanner(readable);
      while (scanner.hasNext()) {
        writer.append(scanner.next());
        writer.append("\n");
      }
    } catch (IOException e) {
      throw new IllegalStateException("Could not write to file. Please check if the ticker was " +
              "valid.");
    }
  }
}
