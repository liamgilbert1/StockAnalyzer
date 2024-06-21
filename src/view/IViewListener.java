package view;

import java.io.IOException;

/**
 * Interface represents a view that can be used to display information to the user.
 * A view can be used to display information to the user.
 */
public interface IViewListener {
  /**
   * This method is used to handle getting data from the view.
   */
  void handleGetData();

  /**
   * This method is used to handle setting data in the view.
   * @throws IOException if an I/O error occurs.
   */
  void handleSetData() throws IOException;
}
