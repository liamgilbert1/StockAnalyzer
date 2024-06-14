# How to Run Program

Run the Jar
1. Download the jar file from the release page
2. Open a command-prompt/terminal and navigate to that folder
3. Now type java -jar NameOfJARFile.jar and press ENTER or RETURN

## How to Create Portfolio with 3 stocks and then query the value of the portfolio on two specific
   dates
1. Input the command CreatePortfolio followed by the name of the portfolio you want to create,
   separated by a space.
2. To add a stock to the portfolio, input the command BuyPortfolioHoldingCommand followed by the
   name of the portfolio, the stock symbol, and the number of shares you want to add, and the date
   that you made the transaction, separated by spaces.
3. Repeat step 2 for the other 2 stocks
4. To get the value of the portfolio on a specific date, input the command GetPortfolioValue
   followed by the name of the portfolio and the date you want to get the value of, separated by a
   space.
5. Repeat step 4 for the other date.

## Supported Stocks
Our program supports any stock that is available on the Alpha Vantage API. The value of the
portfolio can be determined on any date that the stock is available on the Alpha Vantage API.

## Additional Notes: The program will be storing your portfolios in a folder called portfolios, and
the stock data in a folder called stockData. If you want to load in a portfolio, you can do so by
inputting the command LoadPortfolio followed by the name of the portfolio you want to load in, after
having formatted a valid portfolio file in the "portfolios" folder. The "portfolios" folder will be
inside the same directory as the jar. If the folder does not exist yet, and you would like to load a
portfolio in, you may create the folder yourself but it must be named "portfolios". The program will
then load in the portfolio, and you can query the value of the portfolio on any date that the stock
data is available on the Alpha Vantage API.

## Formatting of Portfolio Files
The txt file should be formatted by having the first line be the name of the portfolio, and the
transactions below it. The transactions are formatted by have the date of the transaction
yyyy-mm-dd one line, and all the transactions which occurred on that date below it, each on a new
line. The transactions are formatted by having the action (buy or sell), the number of shares,
"shares", and the ticker symbol of the stock, each separated by a space. For example:
PortfolioName
2020-01-01
buy 10 shares AAPL
sell 5 shares AAPL
2020-01-02
buy 5 shares AAPL
sell 2 shares AAPL
The transactions and dates may be in any order, but the transactions for a given date must be
grouped together.
