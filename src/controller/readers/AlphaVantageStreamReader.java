package controller.readers;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Objects;
import java.io.StringReader;

/**
 * Represents a reader that can read stock data from the AlphaVantage API. The API should be
 * formatted such that the first column contains the date, the second column contains the open
 * price, the third column contains the high price, the fourth column contains the low price, the
 * fifth column contains the close price, and the sixth column contains the volume.
 */
public class AlphaVantageStreamReader implements IReader {
  private final String ticker;

  /**
   * Constructs an object of the AlphaVantageStreamReader with the given ticker symbol.
   *
   * @param ticker the ticker symbol of the stock.
   */
  public AlphaVantageStreamReader(String ticker) {
    this.ticker = Objects.requireNonNull(ticker);
  }

  @Override
  public Readable getReadable() {
    String apiKey = "4ZZS6M66PTROODSK";
    String stockSymbol = this.ticker; //ticker symbol for Google
    URL url;

    try {
      url = new URL("https://www.alphavantage"
              + ".co/query?function=TIME_SERIES_DAILY"
              + "&outputsize=full"
              + "&symbol"
              + "=" + stockSymbol + "&apikey=" + apiKey + "&datatype=csv");
    } catch (MalformedURLException e) {
      throw new RuntimeException("the alphavantage API has either changed or "
              + "no longer works");
    }

    Appendable output = new StringBuilder();

    try (InputStream in = url.openStream()) {
      int b;
      while ((b = in.read()) != -1) {
        output.append(((char) b));
      }
    } catch (IOException e) {
      throw new IllegalArgumentException("No price data found for " + stockSymbol);
    }
    return new StringReader(output.toString());
  }
}
