package view.guiview;

import view.IView;
import view.IViewListener;

public interface IGUIView extends IView {
  String getData();
  void setData(String data);
  void addViewListener(IViewListener viewListener);
  void requestFocus();
  void setVisible(boolean b);
}
