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
### Create Portfolio with 3 stocks
1. Input the command CreatePortfolio followed by the name of the portfolio you want to create,
   separated by a space.
2. Input the name you want your portfolio to be called
3. Input the command PortfolioHoldingCommand
4. Input your portfolio name
5. Input the stock symbol
6. Input the number of shares
7. Repeat steps 3-6 for the other 2 stocks

## How to Create Another Portfolio with 2 different stocks
1. Input the command CreatePortfolio
2. Input the name you want your portfolio to be called
3. Input the command AddPortfolioHoldingCommand
4. Input your new portfolio name
5. Input the stock symbol
6. Input the number of shares
7. Repeat steps 3-6 for the other stock

## How to Get Portfolio Value
1. Input the command GetPortfolioValue
2. Input the name of the portfolio you want to get the value of
3. Input the date you want to get the value of the portfolio for
