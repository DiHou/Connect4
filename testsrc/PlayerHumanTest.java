package edu.nyu.cs.pqs.connect4;

import static org.junit.Assert.*;

import java.awt.Color;

import org.junit.Before;
import org.junit.Test;

public class PlayerHumanTest {
  Player man;
  
  @Before
  public void setUp() throws Exception {
    man = PlayerFactory.getHumanPlayer(Color.RED, "John", "NYC");
  }

  @Test
  public void testGetPlayerColor() {
    Color c = ((PlayerHuman)man).getPlayerColor();
    assertTrue(c.equals(Color.RED));
  }
  
  @Test
  public void testGetPlayerType() {
    String type = ((PlayerHuman)man).getPlayerType();
    assertTrue(type.equals("HUMAN"));
  }
  
  @Test
  public void testGetPlayerName() {
    String name = ((PlayerHuman)man).getPlayerName();
    assertTrue(name.equals("John"));
  }
  
  @Test
  public void testGetPlayerCity() {
    String city = ((PlayerHuman)man).getPlayerCity();
    assertTrue(city.equals("NYC"));
  }
  
  @Test
  public void testToStringr() {
    String s = man.toString();
    assertTrue(s.equals("John(HUMAN, RED color, NYC)"));
  } 
}
