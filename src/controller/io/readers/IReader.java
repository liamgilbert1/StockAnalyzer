package controller.io.readers;

/**
 * Represents a reader that can read data from a source, and return it as a Readable object.
 * This is useful for reading data from a file, or from a web service.
 */
public interface IReader {
  /**
   * Returns a Readable object that contains data. The Readable object can be used to
   * read the data in a format that is convenient for the user.
   * @return a Readable object that contains the data.
   */
  Readable getReadable();
}
