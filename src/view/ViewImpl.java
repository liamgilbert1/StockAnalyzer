package view;

import java.io.IOException;

/**
 * Represents a view that can be used to display information to the user.
 * A view can be used to display information to the user.
 * Has a field output that is an Appendable object.
 */
public class ViewImpl implements IView {
  private final Appendable output;

  /**
   * Constructs a new view with the given output.
   * @param output the output to use.
   */
  public ViewImpl(Appendable output) {
    this.output = output;
  }

  @Override
  public Appendable append(CharSequence csq) throws IOException {
    output.append(csq);
    return this;
  }

  /**
   * Appends the specified subsequence of the specified character sequence to this Appendable.
   * @param csq the character sequence from which a subsequence will be appended.
   * @param start the index of the first character in the subsequence.
   * @param end the index of the character following the last character in the subsequence.
   * @return this Appendable.
   * @throws IOException if an I/O error occurs.
   */
  @Override
  public Appendable append(CharSequence csq, int start, int end) throws IOException {
    output.append(csq.subSequence(start, end));
    return this;
  }

  /**
   * Appends the specified character to this Appendable.
   * @param c the character to append.
   * @return this Appendable.
   * @throws IOException if an I/O error occurs.
   */
  @Override
  public Appendable append(char c) throws IOException {
    output.append(c);
    return this;
  }
}
