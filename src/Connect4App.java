package edu.nyu.cs.pqs.connect4;

/** 
 * 
 * Observer Pattern used to communicate between the model and view
 * Factory Pattern used to generate new player - human or AI
 * Singleton Pattern used make sure only one model exists in the game
 * Builder Pattern used to construct a human player with multi parameters
 * 
 */
public class Connect4App {
  /**
   * New game begins with filling in player info and choosing the mode - one or two players
   */
  public void start() {
    new Connect4SetMode();
  }
  
  public static void main(String[] args) {
    new Connect4App().start();
  }
}
