package view;

import java.io.IOException;

public class ViewImpl implements IView {
  Appendable output;

  public ViewImpl(Appendable output) {
    this.output = output;
  }
  @Override
  public Appendable append(CharSequence csq) throws IOException {
    output.append(csq);
    return output;
  }

  @Override
  public Appendable append(CharSequence csq, int start, int end) throws IOException {
    output.append(csq.subSequence(start, end));
    return output;
  }

  @Override
  public Appendable append(char c) throws IOException {
    output.append(c);
    return output;
  }
}
