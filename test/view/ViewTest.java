package view;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * Test cases for View.
 */
public class ViewTest {

  @Before
  public void setUp() {
    Appendable output = new StringBuilder();
    IView view = new ViewImpl(output);
  }

  @Test
  public void testAppend() {
    Appendable output = new StringBuilder();
    IView view = new ViewImpl(output);
    try {
      view.append("Hello");
    } catch (Exception e) {
      fail("Append failed");
    }
    assertEquals("Hello", output.toString());
  }

  @Test
  public void testAppend2() {
    Appendable output = new StringBuilder();
    IView view = new ViewImpl(output);
    try {
      view.append("Hello", 1, 4);
    } catch (Exception e) {
      fail("Append failed");
    }
    assertEquals("ell", output.toString());
  }

  @Test
  public void testAppend3() {
    Appendable output = new StringBuilder();
    IView view = new ViewImpl(output);
    try {
      view.append("o");
    } catch (Exception e) {
      fail("Append failed");
    }
    assertEquals("o", output.toString());
  }
}
