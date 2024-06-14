This project was designed using a model-view-controller architecture.

CHANGES FROM PREVIOUS SUBMISSION

Signature of control in IController: Changed original method signature to take in an IModel2 instead
of an IModel. Did this so the controller can work with the new commands as well as the old one.
We did not feel it was necessary to implement a whole new controller with similar functionality, so
we changed our control methods signature.

Changed signature of the execute method in ICommand for all the old commands to take in a IModel2
instead of an IModel. Did this so the all the old commands can now work with the controller, as well
as the new commands. Did not feel it was necessary to write new command classes for the old commands
since the functionality would be almost identical.

Changed MockModelImpl to implement IModel2 instead of IModel and added our new methods from
ModelImpl2 to MockModelIpl. MockModelImpl is only used for testing purposes, so we did not feel it
was needed to write a new mock model.

We added a higher level portfolio interface to encapsulate shared behavior over multiple portfolios.
Since the new portfolio implementation held transactions rather than holdings, they had some
differences, but the similarities were enough to warrant a higher level interface.

We added some getCopy methods in the model to return copies of the objects rather than the objects
themselves. This is more secure and prevents clients from modifying the objects directly.

We made changes to the readers and writers so the program works in a jar. Before, the readers and
writers were working only within the ide, but now they work relative to the location of the jar and
read and write files within the user’s file system. These changes were necessary to make the program
work in a jar.

New API key added. High volume api key put into AlphaVantageStreamReader. This is to prevent the
program from being rate limited by the api.

We had to fix bugs in the tryWrite method in the AWriterCommand class. The method was not writing
when it was supposed to due to a bug in the method. We fixed the bug and now the method writes when
it is supposed to. This was a critical bug that needed to be fixed.

We added a getReader method in the AWriterCommand class. This method returns the reader that the
command is using. This is useful for extensibility and allows the command to be easily extended to
use a different reader in the future.


OVERVIEW OF THE ARCHITECTURE

Controller:
     The controller is responsible for handling user input and updating the model. The controller
        uses the command pattern to handle user input. The controller stores ICommands in a map,
        which is easily extendable to add new commands.

     Commands:
        The commands extend abstract classes depending on whether they may have to write stock data
        to a file. The AWriterCommand abstract class extends the ACommand abstract class which
        implements the ICommand interface. By default, the AWriterCommands write to a CSV file when
        new data is needed, but this can be easily extended and overridden to write to a different
        file type. The commands in the controller call methods in the model to update the state of
        the model and append to the view to update the user interface.

     IO:
        The controller uses the IReader interface to read stock data from the API and the IWriter
        interface to write stock data to a file. The AlphaVantageReader class implements the IReader
        interface and reads stock data from the Alpha Vantage API. The CSVWriter class implements
        the IWriter interface and writes stock data to a CSV file. The CSVReader class reads stock
        data from a CSV file. Data is never queried from the AlphaVantageStreamReader directly, but
        rather always through an IStock using a reader such as CSVReader.

        The IPortfolioWithTransactionReader interface is implemented by the TxtPortfolioReader class
        which reads a portfolio from a txt file. This allows the user to load a portfolio from a txt
        file. The TxtPortfolioWriter class implements the IPortfolioWriter interface and writes a
        portfolio to a txt file. This allows the user to save a portfolio to a txt file, which is
        done automatically whenever a portfolio is updated in the model.

Model:
     The model is responsible for storing the state of the application. The model contains a list
        of portfolios, which contain a list of transactions. The model contains methods to
        update the state of the application, such as adding a portfolio, adding a holding to a
        portfolio, buying a stock, selling a stock. The model also contains methods to query the
        state of the application, such as getting the value of a portfolio, getting the composition
        of a portfolio, etc. The model implementation implements the IModel2 interface.

     Portfolio:
        The PortfolioWithTransactions class contains a list of ITransaction and has a name. The
        Portfolio class has methods to alter the portfolio and also get information about the
        portfolio. The ITransaction interface is implemented by BuyTransaction and SellTransaction
        classes.
        IPortfolio is extended by IPortfolioWithTransactions and IPortfolioWithHoldings.
        IPortfolioWithTransactions is implemented by PortfolioWithTransactions and
        IPortfolioWithHoldings is implemented by PortfolioWithHoldings.
        A portfolio with transactions is date sensitive, meaning that the value of the portfolio
        changes depending on the date of the query. A portfolio of holdings on the other hand, is
        not date sensitive, meaning that the value of the portfolio is the same regardless of the
        date of the query.

     Stock:
        The Stock class queries through its getReader method which can be easily extended and
        overridden to read from other file types. The Stock class contains a ticker and a list of
        IStockData. The IStockData interface is implemented by StockData class. The Stock class
        contains methods to get the price of the stock on a given date, get the price of the stock
        on the most recent date, get the price of the stock on the date of the most recent
        transaction, etc.
        The StockDataPoint enum is used to specify the type of data that is being queried from the
        stock. The StockDataPoint enum contains the following values: DATE, OPEN, HIGH, LOW, CLOSE,
        and VOLUME.

View:
     IView extends Appendable and is implemented by the ViewImpl class. The view is currently
        text-based, but could be replaced with a graphical interface by building the view based on
        an Appendable. The view contains methods to display the state of the application to the
        user. The view is updated by the controller when the state of the application changes. The
        view simply outputs what is appended to it right now, but could be extended to have more
        complex functionality.

Main:
     The main method creates a controller, model, and view. The main method creates commands and
        adds them to the controller. The main method then runs the controller, which starts the
        application. The main method is responsible for creating the controller, model, and
        view, and starting the application. This is the entry point of the application.