# How to Run Program

A text description (SETUP-README.txt) in the res folder of how exactly to run your program from the
JAR file. If you require the jar file to be in a specific folder, or require other files with it,
please include these directions here. You should also include detailed instructions on how to run
your program to create a portfolio, purchase stocks of at least 3 different companies in that
portfolio at different dates and then query the value and cost basis of that portfolio on two
specific dates. We will run your program with this data to begin grading. You should also include
a list of stocks that your program supports, along with dates on which its value can be determined
(if there are restrictions in your program about which data is available).



## How to Run Jar
1. Download the jar file from the release page
2. Open a command-prompt/terminal and navigate to that folder
3. Now type java -jar NameOfJARFile.jar and press ENTER

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
having formatted a valid portfolio file in the "portfolios" folder. The program will then load in the
portfolio and you can query the value of the portfolio on any date that the stock data is available
on the Alpha Vantage API.
