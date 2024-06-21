package view.guiview;

import view.IView;
import view.IViewListener;

/**
 * This interface represents a GUI view for the stock market simulator.
 * The GUI view takes in user input and sends it to the model to execute commands.
 */
public interface IGUIView extends IView {
  /**
   * This method is used to start the GUI view.
   * @return the data from the GUI view
   */
  String getData();

  /**
   * This method is used to set the data in the GUI view.
   * @param data the data to be set
   */
  void setData(String data);

  /**
   * This method is used to add a view listener to the GUI view.
   * @param viewListener the view listener to be added
   */
  void addViewListener(IViewListener viewListener);

  /**
   * This method is used to request focus in the GUI view.
   */
  void requestFocus();

  /**
   * This method is used to set the visibility of the GUI view.
   * @param b the visibility to be set
   */
  void setVisible(boolean b);
}
