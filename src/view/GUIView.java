package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.Border;

public class GUIView extends JFrame implements ActionListener {
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

  public GUIView() {
    super();
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setSize(1100, 800);

    this.setLayout(new BorderLayout());

    JPanel inputPanel = new JPanel();
    inputPanel.setLayout(new GridLayout(0, 1));
    inputPanel.setPreferredSize(new Dimension(600, 800)); // Set preferred size for input panel

    this.createPortfolioTextLabel = new JLabel("Enter a name to Create your Portfolio:");
    inputPanel.add(createPortfolioTextLabel);

    this.createPortfolioTextArea = new JTextArea();
    inputPanel.add(createPortfolioTextArea);

    this.createPortfolioButton = new JButton("Create Portfolio");
    this.createPortfolioButton.setActionCommand("Create Portfolio");
    inputPanel.add(createPortfolioButton);

    this.loadPortfolioTextLabel = new JLabel("Enter portfolio name to Load Portfolio:");
    inputPanel.add(loadPortfolioTextLabel);

    this.loadPortfolioTextArea = new JTextArea();
    inputPanel.add(loadPortfolioTextArea);

    this.loadPortfolioButton = new JButton("Load Portfolio");
    this.loadPortfolioButton.setActionCommand("Load Portfolio");
    inputPanel.add(loadPortfolioButton);

    this.buyTextLabel = new JLabel("Enter Portfolio Name, Stock Ticker, Quantity, " +
            "and Date (PortfolioName XXXX # YYYY-MM-DD) to Purchase a Holding:");
    inputPanel.add(buyTextLabel);

    this.buyTextArea = new JTextArea();
    inputPanel.add(buyTextArea);

    this.buyButton = new JButton("Buy Holding");
    this.buyButton.setActionCommand("Buy Holding");
    inputPanel.add(buyButton);

    this.sellTextLabel = new JLabel("Enter Portfolio Name, Stock Ticker, Quantity, " +
            "and Date (PortfolioName XXXX # YYYY-MM-DD) to Sell a Holding:");
    inputPanel.add(sellTextLabel);

    this.sellTextArea = new JTextArea();
    inputPanel.add(sellTextArea);

    this.sellButton = new JButton("Sell");
    this.sellButton.setActionCommand("Sell");
    inputPanel.add(sellButton);

    this.getValueTextLabel = new JLabel("Enter Portfolio Name, then Date " +
            "(PortfolioName YYYY-MM-DD) to Get the Value of a Portfolio:");
    inputPanel.add(getValueTextLabel);

    this.getValueTextArea = new JTextArea();
    inputPanel.add(getValueTextArea);

    this.getValueButton = new JButton("Get Value");
    this.getValueButton.setActionCommand("Get Value");
    inputPanel.add(getValueButton);

    this.getCompositionTextLabel = new JLabel("Enter Portfolio Name, then Date " +
            "(PortfolioName YYYY-MM-DD) to Get the Composition of a Portfolio:");
    inputPanel.add(getCompositionTextLabel);

    this.getCompositionTextArea = new JTextArea();
    inputPanel.add(getCompositionTextArea);

    this.getCompositionButton = new JButton("Get Composition");
    this.getCompositionButton.setActionCommand("Get Composition");
    inputPanel.add(getCompositionButton);


    this.quitButton = new JButton("Quit");
    this.quitButton.setActionCommand("Quit");
    inputPanel.add(quitButton);

    this.add(inputPanel, BorderLayout.CENTER);

    JPanel outputPanel = new JPanel();
    outputPanel.setLayout(new GridLayout(2, 1));
    outputPanel.setPreferredSize(new Dimension(325, 800));

    this.actionOutput = new JLabel("Your actions will be displayed here");
    Border border = BorderFactory.createLineBorder(Color.BLACK, 1);
    this.actionOutput.setBorder(border);
    this.actionOutput.setVerticalAlignment(JLabel.TOP);
    outputPanel.add(actionOutput);

    this.portfolioOutputs = new JLabel("Your portfolios will be displayed here");
    this.portfolioOutputs.setBorder(border);
    this.portfolioOutputs.setVerticalAlignment(JLabel.TOP);
    outputPanel.add(portfolioOutputs);

    this.add(outputPanel, BorderLayout.EAST);

    this.setVisible(true);
  }

  @Override
  public void actionPerformed(ActionEvent e) {

  }

}
