package edu.nyu.cs.pqs.connect4;

public interface Connect4Listener {
  /**
   * Game starts
   */
  void gameStart();
  
  /**
   * Player hits one of the control buttons to drop
   * @param State is a class whose object is for the convenience of passing by the choice of
   *  chess board position and player's color representation
   */
  void playerMove(State state);
  
  /**
   * No space available for a full column
   * @param col - column index
   */
  void colFull(int col);
  
  /**
   * Deals with the winner's information
   * @param player
   */
  void gameWin(Player player);
  
  /**
   * Board is full and no player wins
   */
  void gameTie();
  
  /**
   * Game over when win or tie
   */
  void gameOver();
  
  /**
   * Restart new game
   */
  void restart();
  
}
