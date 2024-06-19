package view;

public interface IGUIView extends ITextView {
  String getData();
  void setData(String data);
  void addViewListener(IViewListener viewListener);
  void requestFocus();
  void setVisible(boolean b);
}
