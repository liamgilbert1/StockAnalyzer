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

How it Works: The user creates portfolios by inputting the stocks and their quantities.
The program gets the stock prices for the specified date and calculates the total value of the
portfolio.

Status: Complete and functional.


## User Interface and Interactivity
Description: A text-based interactive interface that allows users to use all the features mentioned
above.

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





## Testing

Description: Testing of all parts of the program to ensure reliability and correctness.

How it Works: Many Junit tests covering all features.

Status: Complete and functional. All tests pass successfully.



## Documentation
Description: Detailed documentation for all code, explaining the design and functionality.

How it Works: Documentation is provided in the form of comments within the code, as well as the
README files, and the class diagram.

Status: Complete and functional.
