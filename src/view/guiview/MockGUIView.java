package view.guiview;

/**
 * This class represents a mock GUI view for the stock market simulator.
 * The mock GUI view takes in a command and sends it to the model to execute commands.
 */
public class MockGUIView extends GUIView {
  private final String command;

  /**
   * Constructs a mock GUI view object.
   * @param command the command to be used
   */
  public MockGUIView(String command) {
    super();
    this.command = command;
    setVisible(false);
  }

  /**
   * This method is used to start the GUI view.
   * @return the data from the GUI view
   */
  @Override
  public String getData() {
    return command;
  }
}
