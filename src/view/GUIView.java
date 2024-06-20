package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

import javax.swing.*;
import javax.swing.border.Border;

public class GUIView extends JFrame implements ActionListener, IGUIView {

  private final JTextArea portfolioNameTextArea;
  private final JTextArea quantityTextArea;
  private final JTextArea stockTickerTextArea;
  private final JLabel actionOutput;
  private final JLabel portfolioOutputs;
  private final List<IViewListener> listeners;
  private final JTextArea yearTextArea;
  private final JTextArea monthTextArea;
  private final JTextArea dayTextArea;
  private String command;
  private boolean firstActionTaken;
  private final Map<String, List<Supplier<String>>> textAreaMap;

  public GUIView() {
    super("Stock Market Simulator");
    this.listeners = new ArrayList<>();
    this.command = "";
    this.firstActionTaken = false;

    this.setLayout(new BorderLayout());
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    JLabel instructionLabel = new JLabel("<html>To create a portfolio, enter a name of your choice for the portfolio in the text box and click Create Portfolio.<br>"
            + "To load a portfolio, enter the name of the portfolio in the text box and click Load Portfolio.<br>"
            + "To buy a stock, enter the name of the portfolio, the ticker symbol of the stock, the quantity of shares, and the date in the text boxes and click Buy.<br>"
            + "To sell a stock, enter the name of the portfolio, the ticker symbol of the stock, the quantity of shares, and the date in the text boxes and click Sell.<br>"
            + "To get the value of a portfolio, enter the name of the portfolio, and the date in the text boxes and click Get Value.<br>"
            + "To get the composition of a portfolio, enter the name of the portfolio, and the date in the text boxes and click Get Composition.<br>"
            + "To quit the program, exit out of the page.</html>");
    instructionLabel.setPreferredSize(new Dimension(800, 120));
    this.add(instructionLabel, BorderLayout.NORTH);

    JPanel mainPanel = new JPanel(new GridBagLayout());
    GridBagConstraints gbc = new GridBagConstraints();
    gbc.fill = GridBagConstraints.BOTH;
    this.add(mainPanel, BorderLayout.CENTER);

    JPanel labelsPanel = new JPanel(new GridLayout(1, 4));
    Border labelBorder = BorderFactory.createLineBorder(Color.BLACK, 1);

    JLabel portfolioNameTextLabel = new JLabel("Portfolio Name");
    portfolioNameTextLabel.setBorder(labelBorder);
    labelsPanel.add(portfolioNameTextLabel);

    JLabel stockTickerTextLabel = new JLabel("Stock Ticker (XXXX)");
    stockTickerTextLabel.setBorder(labelBorder);
    labelsPanel.add(stockTickerTextLabel);

    JLabel quantityTextLabel = new JLabel("Quantity (#)");
    quantityTextLabel.setBorder(labelBorder);
    labelsPanel.add(quantityTextLabel);


    JPanel dateLabelsPanel = new JPanel(new GridLayout(1, 3));
    dateLabelsPanel.setBorder(labelBorder);

    JLabel yearLabel = new JLabel("Year (####)");
    yearLabel.setBorder(labelBorder);
    dateLabelsPanel.add(yearLabel);

    JLabel monthLabel = new JLabel("Month ((#)#)");
    monthLabel.setBorder(labelBorder);
    dateLabelsPanel.add(monthLabel);

    JLabel dayLabel = new JLabel("Day ((#)#)");
    dayLabel.setBorder(labelBorder);
    dateLabelsPanel.add(dayLabel);

    labelsPanel.add(dateLabelsPanel);

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
    JScrollPane portfolioScrollPane = new JScrollPane();
    portfolioScrollPane.setViewportView(portfolioNameTextArea);
    textAreasPanel.add(portfolioScrollPane);

    this.stockTickerTextArea = new JTextArea(1, 10);
    this.stockTickerTextArea.setBorder(textAreaBorder);
    JScrollPane stockTickerScrollPane = new JScrollPane();
    stockTickerScrollPane.setViewportView(stockTickerTextArea);
    textAreasPanel.add(stockTickerScrollPane);

    this.quantityTextArea = new JTextArea(1, 10);
    this.quantityTextArea.setBorder(textAreaBorder);
    JScrollPane quantityScrollPane = new JScrollPane();
    quantityScrollPane.setViewportView(quantityTextArea);
    textAreasPanel.add(quantityScrollPane);

    JPanel dateAreasPanel = new JPanel(new GridLayout(1, 3));

    this.yearTextArea = new JTextArea(1, 6);
    this.yearTextArea.setBorder(textAreaBorder);
    JScrollPane yearScrollPane = new JScrollPane();
    yearScrollPane.setViewportView(yearTextArea);
    dateAreasPanel.add(yearScrollPane);

    this.monthTextArea = new JTextArea(1, 2);
    this.monthTextArea.setBorder(textAreaBorder);
    JScrollPane monthScrollPane = new JScrollPane();
    monthScrollPane.setViewportView(monthTextArea);
    dateAreasPanel.add(monthScrollPane);

    this.dayTextArea = new JTextArea(1, 2);
    this.dayTextArea.setBorder(textAreaBorder);
    JScrollPane dayScrollPane = new JScrollPane();
    dayScrollPane.setViewportView(dayTextArea);
    dateAreasPanel.add(dayScrollPane);

    textAreasPanel.add(dateAreasPanel);

    gbc.gridy = 1;
    gbc.weighty = 0.05;
    mainPanel.add(textAreasPanel, gbc);

    JPanel buttonsPanel1 = new JPanel(new GridLayout(1, 3));

    JButton createPortfolioButton = new JButton("Create Portfolio");
    createPortfolioButton.setActionCommand("CreatePortfolio");
    buttonsPanel1.add(createPortfolioButton);

    JButton loadPortfolioButton = new JButton("Load Portfolio");
    loadPortfolioButton.setActionCommand("LoadPortfolio");
    buttonsPanel1.add(loadPortfolioButton);

    JButton buyButton = new JButton("Buy");
    buyButton.setActionCommand("BuyPortfolioHolding");
    buttonsPanel1.add(buyButton);

    gbc.gridy = 2;
    gbc.weighty = 0.075;
    mainPanel.add(buttonsPanel1, gbc);

    JPanel buttonsPanel2 = new JPanel(new GridLayout(1, 3));

    JButton sellButton = new JButton("Sell");
    sellButton.setActionCommand("SellPortfolioHolding");
    buttonsPanel2.add(sellButton);

    JButton getValueButton = new JButton("Get Value");
    getValueButton.setActionCommand("GetPortfolioValue");
    buttonsPanel2.add(getValueButton);

    JButton getCompositionButton = new JButton("Get Composition");
    getCompositionButton.setActionCommand("GetPortfolioComposition");
    buttonsPanel2.add(getCompositionButton);

    gbc.gridy = 3;
    gbc.weighty = 0.075;
    mainPanel.add(buttonsPanel2, gbc);

    JPanel outputPanel = new JPanel(new GridLayout(1, 2));
    Border outputBorder = BorderFactory.createLineBorder(Color.BLACK, 1);

    this.actionOutput = new JLabel("Your actions will be displayed here:");
    this.actionOutput.setBorder(outputBorder);
    this.actionOutput.setVerticalAlignment(SwingConstants.TOP);
    JScrollPane actionScrollPane = new JScrollPane();
    actionScrollPane.setViewportView(actionOutput);
    outputPanel.add(actionScrollPane);

    this.portfolioOutputs = new JLabel("Your portfolios will be displayed here:");
    this.portfolioOutputs.setBorder(outputBorder);
    this.portfolioOutputs.setVerticalAlignment(SwingConstants.TOP);
    JScrollPane portfoliosScrollPane = new JScrollPane();
    portfoliosScrollPane.setViewportView(portfolioOutputs);
    outputPanel.add(portfoliosScrollPane);

    gbc.gridy = 4;
    gbc.weighty = 0.3;
    mainPanel.add(outputPanel, gbc);

    JPanel emptyPanel = new JPanel();
    this.add(emptyPanel, BorderLayout.SOUTH);

    textAreaMap = new HashMap<>();

    List<Supplier<String>> loadPortfolioTextAreas = new ArrayList<>();
    loadPortfolioTextAreas.add(this::getPortfolioName);

    List<Supplier<String>> createPortfolioTextAreas = new ArrayList<>();
    createPortfolioTextAreas.add(this::getPortfolioName);

    List<Supplier<String>> buyTextAreas = new ArrayList<>();
    buyTextAreas.add(this::getPortfolioName);
    buyTextAreas.add(this::getTicker);
    buyTextAreas.add(this::getQuantity);
    buyTextAreas.add(this::getDate);

    List<Supplier<String>> sellTextAreas = new ArrayList<>();
    sellTextAreas.add(this::getPortfolioName);
    sellTextAreas.add(this::getTicker);
    sellTextAreas.add(this::getQuantity);
    sellTextAreas.add(this::getDate);

    List<Supplier<String>> getValueTextAreas = new ArrayList<>();
    getValueTextAreas.add(this::getPortfolioName);
    getValueTextAreas.add(this::getDate);

    List<Supplier<String>> getCompositionTextAreas = new ArrayList<>();
    getCompositionTextAreas.add(this::getPortfolioName);
    getCompositionTextAreas.add(this::getDate);

    textAreaMap.put("LoadPortfolio", loadPortfolioTextAreas);
    textAreaMap.put("CreatePortfolio", createPortfolioTextAreas);
    textAreaMap.put("BuyPortfolioHolding", buyTextAreas);
    textAreaMap.put("SellPortfolioHolding", sellTextAreas);
    textAreaMap.put("GetPortfolioValue", getValueTextAreas);
    textAreaMap.put("GetPortfolioComposition", getCompositionTextAreas);

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
    if (!firstActionTaken) {
      actionOutput.setText("");
      firstActionTaken = true;
    }
    StringBuilder inputs = new StringBuilder();
    for (Supplier<String> textArea : textAreaMap.get(e.getActionCommand())) {
      inputs.append(" ").append(textArea.get());
    }
    command = e.getActionCommand() + inputs;
    for (IViewListener listener : listeners) {
      try {
        listener.handleSetData();
      } catch (IOException ex) {
        return;
      }
      listener.handleGetData();
    }
  }

  @Override
  public String getData() {
    return command;
  }

  @Override
  public void setData(String data) {
    if (!data.isEmpty()) {
      String[] portfolio = data.split(" ");
      StringBuilder portfoliosString = new StringBuilder("<html>");
      for (int i = portfolio.length - 1; i >= 0; i--) {
        portfoliosString.append(portfolio[i]).append("<br>");
      }
      portfoliosString.append("</html>");
      portfolioOutputs.setText(portfoliosString.toString());
    }
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

  private String getTicker() {
    return stockTickerTextArea.getText();
  }

  private String getPortfolioName() {
    return portfolioNameTextArea.getText();
  }

  private String getQuantity() {
    return quantityTextArea.getText();
  }

  private String getDate() {
    if (yearTextArea.getText().isEmpty() || monthTextArea.getText().isEmpty()
            || dayTextArea.getText().isEmpty()) {
      return "";
    }
    if (monthTextArea.getText().length() == 1) {
      monthTextArea.setText("0" + monthTextArea.getText());
    }
    if (dayTextArea.getText().length() == 1) {
      dayTextArea.setText("0" + dayTextArea.getText());
    }
    return yearTextArea.getText() + "-" + monthTextArea.getText() + "-" + dayTextArea.getText();
  }
}
