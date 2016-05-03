package edu.nyu.cs.pqs.connect4;

import static org.junit.Assert.*;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import edu.nyu.cs.pqs.connect4.Connect4Model;

public class Connect4ModelTest {
  Connect4Model model; 
  private Color[][] setBoard(List<State> states) {
    Color[][] board = new Color[6][7];
    for(int i = 0; i < states.size(); i++) {
      State s = states.get(i);
      board[s.getRIndex()][s.getCIndex()] = s.getColor();     
    }
    return board;
  }
  
  @Before
  public void setUp() throws Exception {
    model = Connect4Model.getInstance();
  }

  @Test
  public void testCheckHorizontalTrue() {
    List <State> states = new ArrayList <State>();
    states.add(new State(5, 1, Color.BLUE));
    states.add(new State(5, 2, Color.BLUE));
    states.add(new State(5, 3, Color.BLUE));
    states.add(new State(5, 4, Color.BLUE));
    Color[][] board = setBoard(states);
    assertTrue(model.checkHorizontal(states.get(1), board));
    assertTrue(model.checkFour(states.get(1), board));
  }
  
  @Test
  public void testCheckHorizontalFalse() {
    List <State> states = new ArrayList <State>();
    states.add(new State(5, 0, Color.BLUE));
    states.add(new State(5, 1, Color.BLUE));
    states.add(new State(5, 3, Color.BLUE));
    states.add(new State(5, 4, Color.BLUE));
    Color[][] board = setBoard(states);
    assertFalse(model.checkHorizontal(states.get(2), board));
  }
  
  @Test
  public void testCheckVerticalTrue() {
    List <State> states = new ArrayList <State>();
    states.add(new State(0, 2, Color.BLUE));
    states.add(new State(1, 2, Color.BLUE));
    states.add(new State(2, 2, Color.BLUE));
    states.add(new State(3, 2, Color.BLUE));
    states.add(new State(4, 2, Color.RED));
    Color[][] board = setBoard(states);
    assertTrue(model.checkVertical(states.get(0), board));
    assertTrue(model.checkFour(states.get(0), board));
  }
  
  @Test
  public void testCheckVerticalFalse() {
    List <State> states = new ArrayList <State>();
    states.add(new State(0, 2, Color.RED));
    states.add(new State(1, 2, Color.RED));
    states.add(new State(2, 2, Color.BLUE));
    states.add(new State(3, 2, Color.BLUE));
    states.add(new State(4, 2, Color.BLUE));
    Color[][] board = setBoard(states);
    assertFalse(model.checkVertical(states.get(0), board));
  }
  
  @Test
  public void testCheckDiagnal_RightUp() {
    List <State> states = new ArrayList <State>();
    states.add(new State(5, 0, Color.BLUE));
    states.add(new State(4, 1, Color.BLUE));
    states.add(new State(3, 2, Color.BLUE));
    states.add(new State(2, 3, Color.BLUE));
    states.add(new State(5, 1, Color.BLUE));
    Color[][] board = setBoard(states);
    assertTrue(model.checkDiagonal(states.get(2), board));
    assertFalse(model.checkDiagonal(states.get(4), board));
    assertTrue(model.checkFour(states.get(2), board));
  }
  
  @Test
  public void testCheckDiagnal_LeftUp() {
    List <State> states = new ArrayList <State>();
    states.add(new State(5, 4, Color.BLUE));
    states.add(new State(4, 3, Color.BLUE));
    states.add(new State(3, 2, Color.BLUE));
    states.add(new State(2, 1, Color.BLUE));
    states.add(new State(5, 2, Color.BLUE));
    Color[][] board = setBoard(states);
    assertTrue(model.checkDiagonal(states.get(3), board));
    assertFalse(model.checkDiagonal(states.get(4), board));
    assertTrue(model.checkFour(states.get(3), board));
  }
  
  @Test
  public void testCheckDiagnal_false() {
    List <State> states = new ArrayList <State>();
    states.add(new State(5, 3, Color.BLUE));
    states.add(new State(4, 2, Color.RED));
    states.add(new State(3, 1, Color.BLUE));
    states.add(new State(2, 0, Color.BLUE));
    states.add(new State(5, 2, Color.BLUE));
    Color[][] board = setBoard(states);
    assertFalse(model.checkDiagonal(states.get(3), board));
  }
  
  @Test
  public void testCheckFour_false() {
    List <State> states = new ArrayList <State>();
    states.add(new State(5, 3, Color.BLUE));
    states.add(new State(4, 2, Color.RED));
    states.add(new State(3, 1, Color.BLUE));
    states.add(new State(2, 0, Color.RED));
    states.add(new State(5, 2, Color.BLUE));
    Color[][] board = setBoard(states);
    assertFalse(model.checkFour(states.get(3), board));
  }

}
