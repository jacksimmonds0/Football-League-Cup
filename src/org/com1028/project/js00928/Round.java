/**
 * Round.java
 */
package org.com1028.project.js00928;

import java.io.Serializable;

/**
 * Class for defining the nodes/rounds for the modified binary tree that is the cup competition
 * 
 * @author Jack Simmonds
 */
public class Round implements Serializable {

  /** The match object for that round containing the two teams and the score */
  private Match match = null;
  
  /** Pointer to another round object for the winner i.e. the next round */
  private Round winner = null;
  private static final long serialVersionUID = 4852151012261647407L;
  
  public Round(Match match) {
    super();
    this.match = match;
  }
 
  public Match getMatch() {
    return match;
  }
 
  public void setMatch(Match match) {
    this.match = match;
  }
 
  public Round getWinner() {
    return winner;
  }
  
  public void setWinner(Round winner) {
    this.winner = winner;
  }
  
  
}
