package edu.nyu.cs.pqs.connect4;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

public class Connect4Model {
  private static final int ROW = 6, COLUMN = 7;
  private String mode;
  private Player man1;
  private Player man2;
  private Player ai;
  private Color curColor;
  private int rowNum = ROW;
  private int colNum = COLUMN;
  private int[] colFillNum = new int[COLUMN];
  private Color[][] board = new Color[ROW][COLUMN];
  private boolean stopFlag = false;
  private List<Connect4Listener> listeners = new ArrayList<>();
  
  //singleton pattern - only one model is issued
  private Connect4Model() { }  
  private static final Connect4Model INSTANCE = new Connect4Model();
  public static Connect4Model getInstance() {
    return INSTANCE;
  }
  
  /**
   * Add listener to the model
   * @param listener
   * @return boolean to show if add successfully
   */
  public boolean addListener(Connect4Listener listener) {
    return listeners.add(listener);
  }
  
  /**
   * Remove listener from the model
   * @param listener
   * @return boolean to show if removed successfully
   */
  public boolean removeListener(Connect4Listener listener) {
    return listeners.remove(listener);
  }
  
  /**
   * Set up mode and players for the model
   * @param mode, p1, p2
   */
  public void setupMode(String mo, Player p1, Player p2) {
    this.mode = mo;
    man1 = p1;
    switch (mo) {
      case "ONE":
        ai = p2;
        break;    
      case "TWO":
        man2 = p2;
        break;
    }
  }
  
  /**
   * 
   * @return players' names to show in the notify panel
   */
  public String getPlayer1Name() {
    return ((PlayerHuman)man1).getPlayerName();
  }
  
  public String getPlayer2Name() {
    if (mode.equals("TWO")) {
      return ((PlayerHuman)man2).getPlayerName();
    } else {
      return "AI";
    }
  }  
  
  /**
   * 
   * @return a two dimension array with the current state of the play board
   */
  public Color[][] getBoard() {
    Color[][] boardShot = new Color[rowNum][colNum];
    for(int i = 0; i < rowNum; i++) {
      for(int j = 0; j < colNum; j++) {
        boardShot[i][j] = board[i][j];
      }
    }
    return boardShot;
  }
  
  /**
   * 
   * @return an array recording the current state of the filled columns - how may buttons filled in
   * each column
   */
  public int[] getColFillNum() {
    int[] fillShot = new int[colNum];
    for(int i = 0; i < colNum; i++) {
      fillShot[i] = colFillNum[i];
    }
    return fillShot;
  }

  /**
   * initialize model, clear board, clear the colFillNum array, set first player's color
   */
  public void init() {
    for(int i = 0; i < rowNum; i++) {
      for(int j = 0; j < colNum; j++) {
        board[i][j] = null;
      }
    }
    for(int i = 0; i < colNum; i++) {
      colFillNum[i] = 0;
    }
    curColor = man1.getPlayerColor();
    
  }
  
  
  /**
   * Player's drop in the specified column
   * @param the column that the player clicks the control button
   */
  public void playerMove(int col) {
    //human player move
    if (colFillNum[col] == 6) {
      fireColFullEvent(col);
      return;
    }
    int row = rowNum - colFillNum[col] - 1;
    State state = new State(row, col, curColor);
    addMove(state);
    if(!stopFlag) {
      switchPlayer();
      if(mode.equals("ONE")) {
        //AI move
        State st = ((PlayerAI)ai).aiAlgo(this);
        addMove(st);
        switchPlayer();
      }
    }  
  }
  
  private void addMove(State st) {
    colFillNum[st.getCIndex()]++;
    board[st.getRIndex()][st.getCIndex()] = curColor;
    fireAddMoveEvent(st);
    if(checkFour(st, board)) {
      fireWinEvent(st.getColor());
      fireGameOverEvent();
      stopFlag = true;
    } else if (isFull()) {
      fireTieEvent();
      fireGameOverEvent();
      stopFlag = true;
    }
  }
  
  /**
   * Check horizontal, vertical and diagonal connect
   * @param st - the row, column and color of the new drop, board - current board state
   * @return true if connect 4 in the three directions is found
   */
  public boolean checkFour(State st, Color[][] board) {
    return checkHorizontal(st, board) || checkVertical(st, board) || checkDiagonal(st, board);
  }
  
  /**
   * Check horizontal connect
   */
  public boolean checkHorizontal(State st, Color[][] board) {
    int x = st.getCIndex();
    int y = st.getRIndex();
    int cnt = 1;
    int a = x - 1;
    while(a >= 0) {
      if(board[y][a] == board[y][a+1]) {
        cnt++;
      } else {
        break;
      }
      a--;
    }
    a = x + 1;
    while(a < colNum) {
      if(board[y][a] == board[y][a-1]) {
        cnt++;
      } else {
        break;
      }
      a++;
    }
    return cnt >= 4;
  }
  
  /**
   * Check vertical connect
   */
  public boolean checkVertical(State st, Color[][] board) {
    int x = st.getCIndex();
    int y = st.getRIndex();
    int cnt = 1;
    while (y + 1 < rowNum) {
      if(board[y][x] == board[y+1][x]) {
        cnt++;
        y++;
      } else {
        break;
      }
    }
    return cnt >= 4;
  }
  
  /**
   * Check diagonal connect
   */
  public boolean checkDiagonal(State st, Color[][] board) {
    int x = st.getCIndex();
    int y = st.getRIndex();
    int cnt = 1;
    int a = x - 1;
    int b = y + 1;
    while(a >=0 && b < rowNum) {
      if(board[b][a] == board[b-1][a+1]) {
        cnt++;
      } else {
        break;
      }
      a--;
      b++;
    }   
    a = x + 1;
    b = y + 1;
    while(a < colNum && b < rowNum) {
      if(board[b][a] == board[b-1][a-1]) {
        cnt++;
      } else {
        break;
      }
      a++;
      b++;
    }   
    a = x + 1;
    b = y - 1;
    while(a < colNum && b >= 0) {
      if(board[b][a] == board[b+1][a-1]) {
        cnt++;
      } else {
        break;
      }
      a++;
      b--;
    }
    if(cnt >= 4) {
      return true;
    } else {
      cnt = 1;
    }
    
    a = x - 1;
    b = y - 1;
    while(a >= 0 && b >= 0) {
      if(board[b][a] == board[b+1][a+1]) {
        cnt++;
      } else {
        break;
      }
      a--;
      b--;
    }
    return cnt >= 4;
  }
  
  /**
   * Check if the board is full to decide a tie
   */
  private boolean isFull() {
    for(int i = 0; i < colNum; i++) {
      if(colFillNum[i] < 6) {
        return false;
      }
    }
    return true;
  }
  
  /**
   * Switch players' colors to activate drop
   */
  private void switchPlayer() {
    if(mode.equals("ONE")) {
      curColor = curColor.equals(Color.RED) ? ai.getPlayerColor() : man1.getPlayerColor();
    }
    if(mode.equals("TWO")) {
      curColor = curColor.equals(Color.RED) ? man2.getPlayerColor() : man1.getPlayerColor();
    }
  }
  
  /**
   * Restart the game any time
   */
  public void restart() {
    fireRestartEvent();
  }
  
  /**
   * This listener observes the new move made by the players
   */
  private void fireAddMoveEvent(State state) {
    for(Connect4Listener listener : listeners) {
      listener.playerMove(state);
    }
  }
  
  /**
   * This listener observes the winner
   */
  private void fireWinEvent(Color playerColor) {
    Player player;
    if(mode.equals("ONE")) {
      player = playerColor.equals(Color.RED) ? man1 : ai;
    } else {
      player = playerColor.equals(Color.RED) ? man1 : man2;
    }
    for(Connect4Listener listener : listeners) {
      listener.gameWin(player);
    }
  }
  
  /**
   * This listener observes the tie if the board is full with no winner
   */
  private void fireTieEvent() {
    for(Connect4Listener listener : listeners) {
      listener.gameTie();
    }
  }
  
  /**
   * This listener observes the full columns
   */
  private void fireColFullEvent(int col) {
    for(Connect4Listener listener : listeners) {
      listener.colFull(col);
    }
  }
  
  /**
   * This listener informs a game over and inactivate all the control buttons
   */
  private void fireGameOverEvent() {
    for(Connect4Listener listener : listeners) {
      listener.gameOver();
    }
  }
  
  /**
   * This listener notifies to restart the game for all players
   */
  private void fireRestartEvent() {
    stopFlag = false;
    for(Connect4Listener listener : listeners) {
      listener.restart();
    }
  }
}