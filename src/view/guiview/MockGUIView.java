package view.guiview;

public class MockGUIView extends GUIView {
  private final String command;

  public MockGUIView(String command) {
    super();
    this.command = command;
    setVisible(false);
  }

  @Override
  public String getData() {
    return command;
  }
}
