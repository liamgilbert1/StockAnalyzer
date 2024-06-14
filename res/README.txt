# Stocks
## Stock Gain or Loss Examination
Description: Allows a user to examine the gain or loss of a stock over a specified period.
How it Works: The user enters the ticker symbol of the stock, the start date, and the end date.
The program gets the stock prices for the given dates and calculates the gain or loss.

Status: Complete and functional.


## X-Day Moving Average Calculation
Description: Allows a user to examine the x-day moving average of a stock for a specified date and a
specified value of x.

How it Works: The user enters the ticker symbol of the stock, the date, and the value of x.
The program gets the stock prices for the given date and calculates the x-day moving average.

Status: Complete and functional.


## X-Day Crossover Detection
Description: Allows a user to determine which days are x-day crossovers for a specified stock over
a specified date range and a specified value of x.

How it Works: The user enters the ticker symbol of the stock, the start date, the end date, and the
value of x. The program finds the days where the closing price is greater than the x-day moving
average, signaling a "buy" opportunity.

Status: Complete and functional.


## Portfolio Creation and Valuation
Description: Allows a user to create one or more portfolios with shares of one or more stock, and
find the value of that portfolio on a specific date. You can assume that all the stocks and their
quantities will exist on that date.

How it Works: The user creates portfolios by first creating the portfolio, and then
inputting the stocks and their quantities.
The program gets the stock prices for the specified date and calculates the total value of the
portfolio.

Status: Complete and functional.


## User Interface and Interactivity
Description: A text-based interactive interface that allows users to use all the features mentioned
above. This includes a formal view which implements the IView interface.

How it Works: The program provides a menu-driven interface where users can select options,
input data, and receive results interactively.

Status: Complete and functional. The interface is designed to handle user inputs gracefully,
including error handling for invalid inputs.


## Data Retrieval from Alpha Vantage API
Description: Integration with the Alpha Vantage API to fetch real-time and historical stock data.

How it Works: The program uses the API to fetch stock prices and process the data for the
calculations needed for various features.
S
Status: Complete and functional. Includes handling API rate limits and caching data to minimize
API calls.

## Purchase specific number of shares of a specific stock on a specified date, and add
them to a portfolio.
Description: Allows a user to purchase a specific number of shares of a specific stock on a
specified date, and add them to a portfolio.

How it Works: The user enters the ticker symbol of the stock, the date, the number of shares, and
the portfolio to add the shares to. The program adds the transaction to the portfolio.

Status: Complete and functional.


## Sell specific number of shares of a specific stock on a specified date from a portfolio.
Description: Allows a user to sell a specific number of shares of a specific stock on a specified
date.

How it Works: The user enters the ticker symbol of the stock, the date, the number of shares, and
the portfolio to sell the shares from. The program removes the shares from the portfolio after
checking if the portfolio has enough shares to sell.

Status: Complete and functional.


## Determine the composition of a portfolio at a specific date.
Description: Allows a user to determine the composition of a portfolio at a specific date. The
composition includes the stocks in the portfolio and the number of shares of each stock.

How it Works: The user enters the date and the portfolio to examine. The program gets the
composition of the portfolio on the specified date.

Status: Complete and functional.


## Determine the total value of a portfolio at a specific date.
Description: Allows a user to determine the total value of a portfolio at a specific date. The total
value includes the value of each stock in the portfolio and the total value of the portfolio.

How it Works: The user enters the date and the portfolio to examine. The program gets the stock
prices for the specified date and calculates the total value of the portfolio.

Status: Complete and functional.


## Get the distribution of value of a portfolio by stock at a specific date.
Description: Allows a user to get the distribution of value of a portfolio by stock at a specific
date. The distribution includes the value of each stock in the portfolio.

How it Works: The user enters the date and the portfolio to examine. The program gets the stock
prices for the specified date and calculates the distribution of value of the portfolio by stock.

Status: Complete and functional.


## Persist a portfolio so that it can be saved and loaded (retrieved from files).
Description: Allows a user to save a portfolio to a file and load it back later.

How it Works: The program saves the portfolio to a file in a specific format, relative to the
current directory. The program can also load the portfolio back from the file. If a user
wants to load a file in, they can do so by entering the file name after the LoadPortfolio
command.

Status: Complete and functional.


## Rebalance a portfolio to a specified composition.
Description: Allows a user to rebalance a portfolio to a specified composition. The composition
includes the stocks in the portfolio and the number of shares of each stock.

How it Works: The user enters the composition of the portfolio. The program rebalances the
portfolio to the specified composition.

Status: Complete and functional.


## Performance Over Time.
Description: Allows a user to see the performance of a portfolio over a specified period by
displaying a bar chart.

How it Works: The user enters the start date, the end date, and the portfolio to examine. The
program gets the total value of the portfolio for each day in the specified period and displays a
bar chart showing the performance over time.

Status: Complete and functional.


## Testing

Description: Testing of all parts of the program to ensure reliability and correctness.

How it Works: Many Junit tests covering all features.

Status: Complete and functional. All tests pass successfully.



## Documentation
Description: Detailed documentation for all code, explaining the design and functionality.

How it Works: Documentation is provided in the form of comments within the code, as well as the
README files, and the class diagram.

Status: Complete and functional.
