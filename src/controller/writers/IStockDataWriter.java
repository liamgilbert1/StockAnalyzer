package controller.writers;

/**
 * This interface represents a writer for the stock market simulator.

 */
public interface IStockDataWriter {
  /**
   * Writes the data to a file.
   * @param file the file to write to
   * @param readable the data to write
   */
  void write(String file, Readable readable);
}
