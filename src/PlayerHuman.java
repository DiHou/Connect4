package edu.nyu.cs.pqs.connect4;

import java.awt.Color;

public class PlayerHuman implements Player{
  private final Color playerColor;
  private final String playerType;
  private final String playerName;
  private final String playerCity;
  
  /**
   * This builder pattern is to construct a player with multi parameters - three required and one optional
   */
  public static class Builder {
    //required
    private final Color playerColor;
    private final String playerType;
    private final String playerName;
    //optional
    private String playerCity;
    
    public Builder(String name, Color color, String type) {    
      playerColor = color;
      playerType = type;
      playerName = name;
    }
     
    public Builder playerCity(String city) {
      playerCity = city;
      return this;
    }
    
    public PlayerHuman build() {
      return new PlayerHuman(this);
    }
  }
  
  private PlayerHuman(Builder builder) {
    this.playerColor = builder.playerColor;
    this.playerType = builder.playerType;
    this.playerName = builder.playerName;
    this.playerCity = builder.playerCity;
  }

  /**
   * get player color
   * @return color
   */
  @Override
  public Color getPlayerColor() {
    return playerColor;
  }
  
  /**
   * get player type
   * @return type
   */
  @Override
  public String getPlayerType() {
    return playerType;
  }
  
  /**
   * get player name
   * @return name
   */
  public String getPlayerName() {
    return playerName;
  }
  
  /**
   * get player city
   * @return city
   */
  public String getPlayerCity() {
    return playerCity;
  }
  
  @Override
  public String toString() {
    String color = playerColor.equals(Color.RED) ? "RED" : "BLUE";
    return playerName + "(" + playerType + ", " +  color + " color, " + playerCity + ")";
  }  
}
