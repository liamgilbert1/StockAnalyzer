package controller;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.Scanner;

public class CSVWriter implements IWriter {
  public void write(String ticker, Readable readable) {
    String file = ticker + ".csv";
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
