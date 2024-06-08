This project was designed using a model-view-controller architecture.
The controller implements the IController interface, which is responsible for handling user input
and updating the model.
The model implements the IModel interface, which is responsible for storing the state of the
application.
The view is currently text-based, but could be replaced with a graphical interface by
building the view based on an Appendable.
The controller uses the command pattern to handle user
input. The controller stores ICommands in a map, which is easily extendable to add new commands.
The commands extend abstract classes depending on whether they may have to write stock data to
a file.
The AWriterCommand abstract class extends the ACommand abstract class which implements
the ICommand interface.
By default, the AWriterCommands write to a CSV file when new data is needed,
but this can be easily extended and overridden to write to a different file type.
Similarly, the
IReader interface is implemented by the AlphaVantageReader class, which reads stock data from
the API and returns it as a readable which can be written to a file by and IWriter, such as
the CSVWriter.
The CSV reader can read stock data from a csv file. Data is never queried from the
CSVReader directly, but rather always through an IStock.
The Stock class queries through its
getReader method which can be easily extended and overridden to read from other file types.
Portfolios are stored in a list in the model.
Portfolios contain a list of holdings and have names;
Holdings contain a ticker and a number of shares.
The commands in the controller call methods in
the model to update the state of the application for the user.
The Main class is the entry point for the application and is responsible for creating the model,
view, and controller and running the application.