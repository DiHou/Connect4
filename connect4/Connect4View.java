package edu.nyu.cs.pqs.connect4;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Connect4View implements Connect4Listener, ActionListener{
  private static final int ROW = 6, COLUMN = 7;
  private final Connect4Model model;
  private Color playerColor;
  private int rowNum = ROW;
  private int colNum = COLUMN;
  //6X7 frame components - title, control/chess/notify panels
  private JFrame frame = new JFrame();
  private JPanel chessPanel = new JPanel(new GridLayout(rowNum, colNum));
  private JPanel ctrlPanel = new JPanel(new GridLayout(1, colNum));
  private JPanel bottomPanel = new JPanel(new BorderLayout());
  private JButton restartBtn = new JButton("restart");
  private JButton[] ctrlBtns = new JButton[colNum];
  private JButton[][] innerBtns = new JButton[rowNum][colNum];
  private JLabel notify = new JLabel();
  private String notifyRED;
  private String notifyBLUE;
  
  public Connect4View(Connect4Model model, Color c) {
    this.model = model;
    model.addListener(this);
    playerColor = c;
    notifyRED = model.getPlayer1Name() + "'s turn";
    notifyBLUE = model.getPlayer2Name() + "'s turn";  
    gameStart();
  }
  
  private void setupBoard() {   
    //set frame title
    frame.setTitle(this.playerColor.equals(Color.RED) ? 
        model.getPlayer1Name() + " plays RED" : model.getPlayer2Name() + " plays BLUE");   
    //set up control panel
    ctrlPanel.setLayout(new GridLayout(1, COLUMN));
    for (int i = 0; i < colNum; i++) {
      JButton ctrlButton = new JButton();
      ctrlButton.addActionListener(this);
      ctrlBtns[i] = ctrlButton;
      ctrlPanel.add(ctrlButton);
      ctrlBtns[i].setEnabled(playerColor.equals(Color.RED) ? true : false);
    }   
    //set up chess board
    for (int i = 0; i < rowNum; i++) {
      for (int j = 0; j < colNum; j++) {
        JButton btn = new JButton();
        innerBtns[i][j] = btn;
        chessPanel.add(innerBtns[i][j]);
      }
    }
    
    chessPanel.setBackground(Color.BLACK);
    //set up notify field
    notify.setText(notifyRED);
    bottomPanel.add(notify, BorderLayout.WEST);
    bottomPanel.add(restartBtn, BorderLayout.EAST);
    frame.add(ctrlPanel, BorderLayout.NORTH);
    frame.add(chessPanel, BorderLayout.CENTER);
    frame.add(bottomPanel, BorderLayout.SOUTH);
    frame.setVisible(true);
    frame.setSize(500, 500);
    if(frame.getTitle().equals(model.getPlayer1Name() + " plays RED")) {
      frame.setLocationRelativeTo(null);
    }    
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    //add restart action for the restart button
    restartBtn.addActionListener(new ActionListener(){
      @Override
      public void actionPerformed(ActionEvent e) {        
        model.restart();
      }
    });
  }
  
  @Override
  public void gameStart() {
    setupBoard();
    model.init();
  }
  
  @Override
  public void playerMove(State st) {
    int r = st.getRIndex();
    int c = st.getCIndex();
    Color color = st.getColor();
    innerBtns[r][c].setText("O");
    innerBtns[r][c].setFont(new Font("Arial", Font.BOLD, 40));
    innerBtns[r][c].setForeground(color);
    //notify new turn, inactivate current player's control buttons
    notify.setText(color.equals(Color.RED) ? notifyBLUE : notifyRED);
    for(int i=0; i<colNum; i++) {
      ctrlBtns[i].setEnabled(playerColor.equals(color) ? false : true );
    }
  }
  
  @Override
  public void colFull(int col) {
    JOptionPane.showMessageDialog(null, "No place available, pick another column");
  }

  @Override
  public void gameWin(Player player) {
    JOptionPane.showMessageDialog(null, player.toString() + " wins.\nClick restart to start new game");
  }

  @Override
  public void gameTie() {
    JOptionPane.showMessageDialog(null, "It's a tie. \nClick restart to start new game");
  }

  /**
   * Add action listener to the control buttons to perform drop action
   * @param e - action event
   */
  @Override
  public void actionPerformed(ActionEvent e) {
    for(int i = 0; i < colNum; i++) {
      if (e.getSource() == ctrlBtns[i]) {
        model.playerMove(i);
      }
    }
  }
  
  /**
   * When game over, inactivate all the control buttons
   */
  @Override
  public void gameOver() {
    for(int i = 0; i < ctrlBtns.length; i++) {
      ctrlBtns[i].setEnabled(false);
    }
  }
  
  /**
   * When restart, clear all innerbuttons' text, initiate model and reset status
   */
  @Override
  public void restart() {
    //clear board
    for(int i = 0; i < colNum; i++) {
      for(int j = 0; j < rowNum; j++) {
        innerBtns[j][i].setText(null);
      }
    }
    frame.repaint();
    //initiate model
    model.init();
    //reset to start from red
    for (int i = 0; i < colNum; i++) {
      ctrlBtns[i].setEnabled(playerColor.equals(Color.RED) ? true : false);
    }
    notify.setText(notifyRED);   
  }
}
