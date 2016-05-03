package edu.nyu.cs.pqs.connect4;

import java.awt.Color;

public class PlayerFactory {
  /**
   * generate new human player
   * @param color, name, city
   * @return an instance of human player
   */
  public static Player getHumanPlayer(Color color, String name, String city) {
    if (color == null || name.equals("")) {
      throw new IllegalArgumentException("Player color and name required.");
    }
    return new PlayerHuman.Builder(name, color, "HUMAN").playerCity(city).build();
  }
  
  /**
   * generate new AI player
   * @param color
   * @return an instance of AI player
   */
  public static Player getAIPlayer(Color color) {
    if (color == null) {
      throw new IllegalArgumentException("Player color required.");
    }
    return new PlayerAI(color);
  }
}
