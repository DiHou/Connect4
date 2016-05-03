package edu.nyu.cs.pqs.connect4;

import java.awt.Color;

public interface Player {
  
  /**
   * get player type - man and AI
   * @return type of player
   */
  public String getPlayerType();
  
  /**
   * get player's color representation
   * @return color 
   */
  public Color getPlayerColor();
  
}
