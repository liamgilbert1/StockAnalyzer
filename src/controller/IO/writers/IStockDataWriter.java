package controller.IO.writers;

/**
 * This interface represents a writer for the stock market simulator.
 * The writer writes the data to a file.
 * The writer can be used to write data to a file.
 */
public interface IStockDataWriter {
  /**
   * Writes the data to a file.
   * @param file the file to write to
   * @param readable the data to write
   */
  void write(String file, Readable readable);
}
