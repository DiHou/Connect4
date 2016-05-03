package edu.nyu.cs.pqs.connect4;

import java.awt.Color;

public class State {
  private final int row;
  private final int col;
  private final Color color;
  
  public State(int ro, int co, Color color) {
    this.row = ro;
    this.col = co;
    this.color = color;
  }
  
  /**
   * 
   * @return column index
   */
  public int getCIndex() {
    return col;
  }
  
  /**
   * 
   * @return row index
   */
  public int getRIndex() {
    return row;
  }
  
  /**
   * get color of the position
   * @return color
   */
  public Color getColor() {
    return color;
  }
}
