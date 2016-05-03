package edu.nyu.cs.pqs.connect4;

import java.awt.Color;
import java.util.Random;

public class PlayerAI implements Player {
  private static final int ROW = 6, COLUMN = 7;
  private final Color playerColor;
  
  public PlayerAI(Color color) {
    playerColor = color;
  }
  
  @Override
  public String getPlayerType() {
    return "AI";
  }

  @Override
  public Color getPlayerColor() {
    return playerColor;
  }
  
  @Override
  public String toString() {
    return "AI (Blue color)";
  }
  
  /**
   * AI's algorithm to take new steps against human player
   * @param model
   * @return the chosen state recording AI's next move
   */
  public State aiAlgo(Connect4Model model) {
    Color[][] board = model.getBoard();
    int[] colFill = model.getColFillNum();
    int colNum = COLUMN;
    int rowNum = ROW;
    //check every available position to see if there is a step to win for AI
    for(int col = 0; col < colNum; col++) {
      if(colFill[col] == 6) {
        continue;
      }
      int row = rowNum - colFill[col] - 1;
      State state = new State(row, col, playerColor);
      board[row][col] = playerColor;
      //check winning step
      if(model.checkFour(state, board)) {
        return state;
      }     
      board[row][col] = null;
    }
    
    //check if there is a step for opponent to win and AI will take the step
    for (int i = 0; i < colNum; i++ ) {
      if(colFill[i] == 6) {
        continue;
      }
      int row = rowNum - colFill[i] - 1;
      Color opponentColor = playerColor.equals(Color.BLUE) ? Color.RED : Color.BLUE;
      State state = new State(row, i, opponentColor);
      board[row][i] = opponentColor;
      if(model.checkFour(state, board)) {
        return new State(row, i, playerColor);
      }    
      board[row][i] = null;
    }
    //if not for the above two situations, generate random num between 0-6 to choose available position
    Random rand = new Random();
    int c = 0;
    while(true) {
      c = rand.nextInt(7);
      if(colFill[c] < 6) {
        break;
      }
    }
    int row = rowNum - colFill[c] - 1;
    State state = new State(row, c, playerColor);
    return state;
  }
}
