package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
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

  private final JLabel portfolioNameTextLabel;
  private final JTextArea portfolioNameTextArea;

  private final JLabel quantityTextLabel;
  private final JTextArea quantityTextArea;

  private final JLabel stockTickerTextLabel;
  private final JTextArea stockTickerTextArea;

  private final JLabel dateLabel;
  private final JTextArea dateTextArea;

  private final JLabel instructionLabel;
  private final JLabel actionOutput;
  private final JLabel portfolioOutputs;

  private final List<IViewListener> listeners;

  private String command;

  private boolean firstActionTaken;

  //private final Map<String, JTextArea> textAreaMap;

  public GUIView() {
    super("Stock Market Simulator");
    this.listeners = new ArrayList<>();
    this.command = "";
    this.firstActionTaken = false;

    this.setLayout(new BorderLayout());
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    // Instructions label at the top
    this.instructionLabel = new JLabel("<html>To create a portfolio, enter a name of your choice for the portfolio in the text box and click Create Portfolio.<br>"
            + "To load a portfolio, enter the name of the portfolio in the text box and click Load Portfolio.<br>"
            + "To buy a stock, enter the name of the portfolio, the ticker symbol of the stock, the quantity of shares, and the date in the text boxes and click Buy.<br>"
            + "To sell a stock, enter the name of the portfolio, the ticker symbol of the stock, the quantity of shares, and the date in the text boxes and click Sell.<br>"
            + "To get the value of a portfolio, enter the name of the portfolio, and the date in the text boxes and click Get Value.<br>"
            + "To get the composition of a portfolio, enter the name of the portfolio, and the date in the text boxes and click Get Composition.<br>"
            + "To quit the program, exit out of the page.</html>");
    this.instructionLabel.setPreferredSize(new Dimension(800, 120));
    this.add(instructionLabel, BorderLayout.NORTH);

    JPanel mainPanel = new JPanel(new GridBagLayout());
    GridBagConstraints gbc = new GridBagConstraints();
    gbc.fill = GridBagConstraints.BOTH;
    this.add(mainPanel, BorderLayout.CENTER);

    JPanel labelsPanel = new JPanel(new GridLayout(1, 4));
    Border labelBorder = BorderFactory.createLineBorder(Color.BLACK, 1);

    this.portfolioNameTextLabel = new JLabel("Portfolio Name");
    this.portfolioNameTextLabel.setBorder(labelBorder);
    labelsPanel.add(portfolioNameTextLabel);

    this.stockTickerTextLabel = new JLabel("Stock Ticker (XXXX)");
    this.stockTickerTextLabel.setBorder(labelBorder);
    labelsPanel.add(stockTickerTextLabel);

    this.quantityTextLabel = new JLabel("Quantity (#)");
    this.quantityTextLabel.setBorder(labelBorder);
    labelsPanel.add(quantityTextLabel);

    this.dateLabel = new JLabel("Date (YYYY-MM-DD)");
    this.dateLabel.setBorder(labelBorder);
    labelsPanel.add(dateLabel);

    gbc.gridx = 0;
    gbc.gridy = 0;
    gbc.gridwidth = 1;
    gbc.weightx = 1;
    gbc.weighty = 0.05;
    mainPanel.add(labelsPanel, gbc);

    JPanel textAreasPanel = new JPanel(new GridLayout(1, 4));
    Border textAreaBorder = BorderFactory.createLineBorder(Color.BLACK, 1);

    this.portfolioNameTextArea = new JTextArea(1, 10);
    this.portfolioNameTextArea.setBorder(textAreaBorder);
    textAreasPanel.add(portfolioNameTextArea);

    this.stockTickerTextArea = new JTextArea(1, 10);
    this.stockTickerTextArea.setBorder(textAreaBorder);
    textAreasPanel.add(stockTickerTextArea);

    this.quantityTextArea = new JTextArea(1, 10);
    this.quantityTextArea.setBorder(textAreaBorder);
    textAreasPanel.add(quantityTextArea);

    this.dateTextArea = new JTextArea(1, 10);
    this.dateTextArea.setBorder(textAreaBorder);
    textAreasPanel.add(dateTextArea);

    gbc.gridy = 1;
    gbc.weighty = 0.05;
    mainPanel.add(textAreasPanel, gbc);

    JPanel buttonsPanel1 = new JPanel(new GridLayout(1, 3));

    this.createPortfolioButton = new JButton("Create Portfolio");
    this.createPortfolioButton.setActionCommand("createPortfolio");
    buttonsPanel1.add(createPortfolioButton);

    this.loadPortfolioButton = new JButton("Load Portfolio");
    this.loadPortfolioButton.setActionCommand("loadPortfolio");
    buttonsPanel1.add(loadPortfolioButton);

    this.buyButton = new JButton("Buy");
    this.buyButton.setActionCommand("buy");
    buttonsPanel1.add(buyButton);

    gbc.gridy = 2;
    gbc.weighty = 0.075;
    mainPanel.add(buttonsPanel1, gbc);

    JPanel buttonsPanel2 = new JPanel(new GridLayout(1, 3));

    this.sellButton = new JButton("Sell");
    this.sellButton.setActionCommand("sell");
    buttonsPanel2.add(sellButton);

    this.getValueButton = new JButton("Get Value");
    this.getValueButton.setActionCommand("getValue");
    buttonsPanel2.add(getValueButton);

    this.getCompositionButton = new JButton("Get Composition");
    this.getCompositionButton.setActionCommand("getComposition");
    buttonsPanel2.add(getCompositionButton);

    gbc.gridy = 3;
    gbc.weighty = 0.075;
    mainPanel.add(buttonsPanel2, gbc);

    JPanel outputPanel = new JPanel(new GridLayout(1, 2));
    Border outputBorder = BorderFactory.createLineBorder(Color.BLACK, 1);

    this.actionOutput = new JLabel("Your actions will be displayed here:");
    this.actionOutput.setBorder(outputBorder);
    this.actionOutput.setVerticalAlignment(SwingConstants.TOP);
    outputPanel.add(actionOutput);

    this.portfolioOutputs = new JLabel("Your portfolios will be displayed here:");
    this.portfolioOutputs.setBorder(outputBorder);
    this.portfolioOutputs.setVerticalAlignment(SwingConstants.TOP);
    outputPanel.add(portfolioOutputs);

    gbc.gridy = 4;
    gbc.weighty = 0.3;
    mainPanel.add(outputPanel, gbc);

    JPanel emptyPanel = new JPanel();
    this.add(emptyPanel, BorderLayout.SOUTH);

    getCompositionButton.addActionListener(this);
    createPortfolioButton.addActionListener(this);
    loadPortfolioButton.addActionListener(this);
    buyButton.addActionListener(this);
    sellButton.addActionListener(this);
    getValueButton.addActionListener(this);

    setPreferredSize(new Dimension(1200, 800));
    pack();
    setVisible(true);
  }


  @Override
  public void actionPerformed(ActionEvent e) {
//    if (!firstActionTaken) {
//      actionOutput.setText("");
//      firstActionTaken = true;
//    }
//    if (e.getActionCommand().equals("quit")) {
//      System.exit(0);
//    }
//    command = e.getActionCommand() + " " + textAreaMap.get(e.getActionCommand()).getText();
//    for (IViewListener listener : listeners) {
//      listener.handleSetData();
//      listener.handleGetData();
//    }
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
    addActionText(csq.toString());
    Appendable appendable = new StringBuilder();
    appendable.append(actionOutput.getText());
    return appendable;
  }

  @Override
  public Appendable append(CharSequence csq, int start, int end) throws IOException {
    return this.append(csq.subSequence(start, end));
  }

  @Override
  public Appendable append(char c) throws IOException {
    addActionText(String.valueOf(c));
    Appendable appendable = new StringBuilder();
    appendable.append(actionOutput.getText());
    return appendable;
  }

  private void addActionText(String additionalText) {
    String[] newLines = additionalText.split("\n");
    StringBuilder newText = new StringBuilder("<html>");
    for (String line : newLines) {
      newText.append(line).append("<br>");
    }
    newText.append(actionOutput.getText());
    newText.append("</html>");
    actionOutput.setText(newText.toString());
  }
}
