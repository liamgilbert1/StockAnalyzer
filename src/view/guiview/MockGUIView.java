package view.guiview;

public class MockGUIView extends GUIView {
  private final String command;
  private final Appendable output;

  public MockGUIView(String command, Appendable output) {
    super();
    this.command = command;
    this.output = output;
    setVisible(false);
  }

  @Override
  public String getData() {
    return command;
  }

  @Override
  public Appendable append(CharSequence data) {
    try {
      output.append(data);
    } catch (Exception e) {
      throw new IllegalArgumentException("Failed to append data.");
    }
    return output;
  }

  @Override
  public Appendable append(CharSequence data, int start, int end) {
    try {
      output.append(data, start, end);
    } catch (Exception e) {
      throw new IllegalArgumentException("Failed to append data.");
    }
    return output;
  }

  @Override
  public Appendable append(char c) {
    try {
      output.append(c);
    } catch (Exception e) {
      throw new IllegalArgumentException("Failed to append data.");
    }
    return output;
  }
}
