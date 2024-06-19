package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.*;
import javax.swing.border.Border;

public class GUIView extends JFrame implements ActionListener, IGUIView {
  private final JButton buyButton;
  private final JButton sellButton;
  private final JButton getValueButton;
  private final JButton getCompositionButton;
  private final JButton createPortfolioButton;
  private final JButton loadPortfolioButton;
  private final JButton quitButton;

  private final JLabel createPortfolioTextLabel;
  private final JTextArea createPortfolioTextArea;

  private final JLabel loadPortfolioTextLabel;
  private final JTextArea loadPortfolioTextArea;

  private final JLabel buyTextLabel;
  private final JTextArea buyTextArea;

  private final JLabel sellTextLabel;
  private final JTextArea sellTextArea;

  private final JLabel getValueTextLabel;
  private final JTextArea getValueTextArea;

  private final JLabel getCompositionTextLabel;
  private final JTextArea getCompositionTextArea;

  private final JLabel actionOutput;
  private final JLabel portfolioOutputs;

  private final List<IViewListener> listeners;

  private String command;

  private final Map<String, JTextArea> textAreaMap;

  public GUIView() {
    super();
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setSize(1100, 800);

    setLayout(new BorderLayout());

    JPanel inputPanel = new JPanel();
    inputPanel.setLayout(new GridLayout(0, 1));
    inputPanel.setPreferredSize(new Dimension(600, 800));

    createPortfolioTextLabel = new JLabel("Enter a name to Create your Portfolio:");
    inputPanel.add(createPortfolioTextLabel);

    createPortfolioTextArea = new JTextArea();
    inputPanel.add(createPortfolioTextArea);

    createPortfolioButton = new JButton("Create Portfolio");
    createPortfolioButton.setActionCommand("CreatePortfolio");
    inputPanel.add(createPortfolioButton);

    loadPortfolioTextLabel = new JLabel("Enter portfolio name to Load Portfolio:");
    inputPanel.add(loadPortfolioTextLabel);

    loadPortfolioTextArea = new JTextArea();
    inputPanel.add(loadPortfolioTextArea);

    loadPortfolioButton = new JButton("Load Portfolio");
    loadPortfolioButton.setActionCommand("LoadPortfolio");
    inputPanel.add(loadPortfolioButton);

    buyTextLabel = new JLabel("Enter Portfolio Name, Stock Ticker, Quantity, " +
            "and Date (PortfolioName XXXX # YYYY-MM-DD) to Purchase a Holding:");
    inputPanel.add(buyTextLabel);

    buyTextArea = new JTextArea();
    inputPanel.add(buyTextArea);

    buyButton = new JButton("Buy Holding");
    buyButton.setActionCommand("BuyPortfolioHolding");
    inputPanel.add(buyButton);

    sellTextLabel = new JLabel("Enter Portfolio Name, Stock Ticker, Quantity, " +
            "and Date (PortfolioName XXXX # YYYY-MM-DD) to Sell a Holding:");
    inputPanel.add(sellTextLabel);

    sellTextArea = new JTextArea();
    inputPanel.add(sellTextArea);

    sellButton = new JButton("Sell");
    sellButton.setActionCommand("SellPortfolioHolding");
    inputPanel.add(sellButton);

    getValueTextLabel = new JLabel("Enter Portfolio Name, then Date " +
            "(PortfolioName YYYY-MM-DD) to Get the Value of a Portfolio:");
    inputPanel.add(getValueTextLabel);

    getValueTextArea = new JTextArea();
    inputPanel.add(getValueTextArea);

    getValueButton = new JButton("Get Value");
    getValueButton.setActionCommand("GetPortfolioValue");
    inputPanel.add(getValueButton);

    getCompositionTextLabel = new JLabel("Enter Portfolio Name, then Date " +
            "(PortfolioName YYYY-MM-DD) to Get the Composition of a Portfolio:");
    inputPanel.add(getCompositionTextLabel);

    getCompositionTextArea = new JTextArea();
    inputPanel.add(getCompositionTextArea);

    getCompositionButton = new JButton("Get Composition");
    getCompositionButton.setActionCommand("GetPortfolioComposition");
    inputPanel.add(getCompositionButton);


    quitButton = new JButton("Quit");
    quitButton.setActionCommand("quit");
    inputPanel.add(quitButton);

    textAreaMap = new HashMap<>(Map.of(
            buyButton.getActionCommand(), buyTextArea,
            sellButton.getActionCommand(), sellTextArea,
            getValueButton.getActionCommand(), getValueTextArea,
            getCompositionButton.getActionCommand(), getCompositionTextArea,
            createPortfolioButton.getActionCommand(), createPortfolioTextArea,
            loadPortfolioButton.getActionCommand(), loadPortfolioTextArea
    ));

    add(inputPanel, BorderLayout.CENTER);

    JPanel outputPanel = new JPanel();
    outputPanel.setLayout(new GridLayout(2, 1));
    outputPanel.setPreferredSize(new Dimension(325, 800));

    actionOutput = new JLabel("Your actions will be displayed here");
    Border border = BorderFactory.createLineBorder(Color.BLACK, 1);
    actionOutput.setBorder(border);
    actionOutput.setVerticalAlignment(JLabel.TOP);
    outputPanel.add(actionOutput);

    portfolioOutputs = new JLabel("Your portfolios will be displayed here");
    portfolioOutputs.setBorder(border);
    portfolioOutputs.setVerticalAlignment(JLabel.TOP);
    outputPanel.add(portfolioOutputs);

    add(outputPanel, BorderLayout.EAST);
    listeners = new ArrayList<>();

    getCompositionButton.addActionListener(this);
    createPortfolioButton.addActionListener(this);
    quitButton.addActionListener(this);
    loadPortfolioButton.addActionListener(this);
    buyButton.addActionListener(this);
    sellButton.addActionListener(this);
    getValueButton.addActionListener(this);

    setFocusable(true);
    requestFocus();
    pack();
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    if (e.getActionCommand().equals("quit")) {
      System.exit(0);
    }
    command = e.getActionCommand() + " " + textAreaMap.get(e.getActionCommand()).getText();
    for (IViewListener listener : listeners) {
      listener.handleSetData();
      listener.handleGetData();
    }
  }

  @Override
  public String getData() {
    return command;
  }

  @Override
  public void setData(String data) {
    String[] portfolio = data.split(" ");
    StringBuilder portfoliosString = new StringBuilder("<html>");
    for (String name : portfolio) {
      portfoliosString.append(name).append("<br>");
    }
    portfoliosString.append("</html>");
    portfolioOutputs.setText(portfoliosString.toString());
  }

  @Override
  public void addViewListener(IViewListener viewListener) {
    if (viewListener == null) {
      throw new IllegalArgumentException("Listener cannot be null");
    }
    if (this.listeners.contains(viewListener)) {
      throw new IllegalArgumentException("Listener already exists");
    }
    this.listeners.add(viewListener);
  }

  @Override
  public Appendable append(CharSequence csq) throws IOException {
    actionOutput.setText(csq.toString());
    Appendable appendable = new StringBuilder();
    appendable.append(actionOutput.getText());
    return appendable;
  }

  @Override
  public Appendable append(CharSequence csq, int start, int end) throws IOException {
    actionOutput.setText(csq.subSequence(start, end).toString());
    Appendable appendable = new StringBuilder();
    appendable.append(actionOutput.getText());
    return appendable;
  }

  @Override
  public Appendable append(char c) throws IOException {
    actionOutput.setText(String.valueOf(c));
    Appendable appendable = new StringBuilder();
    appendable.append(actionOutput.getText());
    return appendable;
  }
}
