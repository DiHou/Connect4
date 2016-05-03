package edu.nyu.cs.pqs.connect4;

import static org.junit.Assert.*;

import java.awt.Color;

import org.junit.Before;
import org.junit.Test;

public class PlayerAITest {
  Player AI;
  Connect4Model model;
  
  @Before
  public void setUp() throws Exception {
    AI = PlayerFactory.getAIPlayer(Color.BLUE);
    model = Connect4Model.getInstance();
  }

  @Test
  public void testGetPlayerType() {
    String type = AI.getPlayerType();
    assertTrue(type.equals("AI"));
  }
  
  @Test
  public void testGetPlayerColor() {
    Color c = AI.getPlayerColor();
    assertTrue(c.equals(Color.BLUE));
  }
  
  @Test
  public void testToStringr() {
    String s = AI.toString();
    assertTrue(s.equals("AI (Blue color)"));
  }

}
