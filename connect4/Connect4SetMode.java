package edu.nyu.cs.pqs.connect4;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Connect4SetMode {
  private JFrame frame = new JFrame("Select mode to start Connect4");
  private JPanel infoPanel = new JPanel(new GridLayout(5,2,5,5));
  private JPanel modePanel = new JPanel(new BorderLayout());
  private JPanel panel = new JPanel(new BorderLayout());
  private final JTextField name1 = new JTextField();
  private final JTextField city1 = new JTextField();
  private final JTextField name2 = new JTextField();
  private final JTextField city2 = new JTextField();
  private JButton oneBtn = new JButton("One Player");
  private JButton twoBtn = new JButton("Two Players"); 
  
  private String n1 = "";
  private String c1 = "";
  private String n2 = "";
  private String c2 = "";

  /**
   * A setMode window is created to get the user's info and mode preference
   */
  public Connect4SetMode() {
    infoPanel.add(new JLabel("Add player1: "));
    infoPanel.add(name1);
    infoPanel.add(new JLabel("Add city1: "));
    infoPanel.add(city1);
    infoPanel.add(new JLabel("Add player2: "));
    infoPanel.add(name2);
    infoPanel.add(new JLabel("Add city2: "));
    infoPanel.add(city2);
    infoPanel.add(new JLabel(" "));
    addBtnAction(oneBtn);
    addBtnAction(twoBtn);
    panel.add(infoPanel, BorderLayout.NORTH);
    panel.add(modePanel,BorderLayout.SOUTH);
    frame.add(panel);
    frame.setVisible(true);
    frame.setSize(400,250);
    frame.setLocationRelativeTo(null);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);    
  }
  
  private void addBtnAction(JButton btn) {
    btn.setPreferredSize(new Dimension(200, 50));
    //get the only Connect4Model Instance
    Connect4Model model = Connect4Model.getInstance();
    
    if(btn.getText().equals("One Player")) {
      modePanel.add(btn, BorderLayout.WEST);
      btn.addActionListener(new ActionListener() {     
        @Override
        public void actionPerformed(ActionEvent e) {         
          n1 = name1.getText();
          c1 = city1.getText();
          if(n1.trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Please enter Play1's name to proceed",
                "Connect4 Game", JOptionPane.INFORMATION_MESSAGE);
            
          } else {
            JOptionPane.showMessageDialog(null, "Welcome " + n1 + ", game starts!",
                "Connect4 Game", JOptionPane.INFORMATION_MESSAGE);         
            Player p1 = PlayerFactory.getHumanPlayer(Color.RED, n1, c1);
            Player p2 = PlayerFactory.getAIPlayer(Color.BLUE);
            model.setupMode("ONE", p1, p2);
            new Connect4View(model, Color.RED);           
          }         
        }
      });
    } else if (btn.getText().equals("Two Players")) {
      modePanel.add(btn, BorderLayout.EAST);
      btn.addActionListener(new ActionListener() {      
        @Override
        public void actionPerformed(ActionEvent e) {
          n1 = name1.getText();
          c1 = city1.getText();
          n2 = name2.getText();
          c2 = city2.getText();
          if(n1.trim().equals("") || n2.trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Please enter both Players' names to proceed",
                "Connect4 Game", JOptionPane.INFORMATION_MESSAGE);
            
          } else {
            JOptionPane.showMessageDialog(null, "Welcome " + n1 + " and " + n2 + ", game starts!",
                "Connect4 Game", JOptionPane.INFORMATION_MESSAGE);
            Player p1 = PlayerFactory.getHumanPlayer(Color.RED, n1, c1);
            Player p2 = PlayerFactory.getHumanPlayer(Color.BLUE, n2, c2);
            model.setupMode("TWO", p1, p2);
            new Connect4View(model, Color.RED);
            new Connect4View(model, Color.BLUE);
          }
        }
      });
    }
  }
}
