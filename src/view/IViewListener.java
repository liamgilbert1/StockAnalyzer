package view;

import java.io.IOException;

public interface IViewListener {
  void handleGetData();
  void handleSetData() throws IOException;
}
