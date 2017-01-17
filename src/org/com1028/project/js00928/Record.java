/**
 * Record.java
 */
package org.com1028.project.js00928;

import java.io.Serializable;

/**
 * Class defining the record for a team in the league, i.e. matches played, won etc. implements Serializable 
 * so the object can be serialized correctly when writing to a text file
 * 
 * @author Jack Simmonds
 */
public class Record implements Serializable{

  private int matchesPlayed = 0;
  private int matchesWon = 0;
  private int matchesDrawn = 0;
  private int matchesLost = 0;
  private int goalsScored = 0;
  private int goalsConceded = 0;
  private int goalDifference = 0;
  private int points = 0;
  private static final long serialVersionUID = 1526687119151690196L;
  
  public Record(int matchesPlayed, int matchesWon, int matchesDrawn, int matchesLost, int goalsScored, int goalsConceded) {
    super();
    this.matchesPlayed = matchesPlayed;
    this.matchesWon = matchesWon;
    this.matchesDrawn = matchesDrawn;
    this.matchesLost = matchesLost;
    this.goalsScored = goalsScored;
    this.goalsConceded = goalsConceded;
  }
    
  /**
   * Method to update the record according to the new match the team has played
   * 
   * @param result
   *          the result of the match for the team (win, loss or draw)
   * @param scored
   *          how many the team scored in the match
   * @param conceded
   *          how many the team conceded the team in the match
   */
  public void updateRecord(Result result, int scored, int conceded) {
     
    // new match added so the matches played increases by one
    setMatchesPlayed(this.matchesPlayed + 1);
    
    // change the corresponding matches result record field according to the result input
    switch(result) {
      case WIN:
        setMatchesWon(this.matchesWon + 1);
        break;
        
      case DRAW:
        setMatchesDrawn(this.matchesDrawn + 1);
        break;
      
      case LOSS:
        setMatchesLost(this.matchesLost + 1);
        break;
    }
    
    // change the goals scored/conceded according to the match score
    setGoalsScored(this.goalsScored + scored);
    setGoalsConceded(this.goalsConceded + conceded);
    
    // update goal difference and points accordingly
    calculateGoalDifference();
    calculatePoints();
    
  }

  public int getMatchesPlayed() {
    return matchesPlayed;
  }

  public void setMatchesPlayed(int matchesPlayed) {
    this.matchesPlayed = matchesPlayed;
  }

  public int getMatchesWon() {
    return matchesWon;
  }

  public void setMatchesWon(int matchesWon) {
    this.matchesWon = matchesWon;
  }

  public int getMatchesDrawn() {
    return matchesDrawn;
  }

  public void setMatchesDrawn(int matchesDrawn) {
    this.matchesDrawn = matchesDrawn;
  }

  public int getMatchesLost() {
    return matchesLost;
  }

  public void setMatchesLost(int matchesLost) {
    this.matchesLost = matchesLost;
  }

  public int getGoalsScored() {
    return goalsScored;
  }

  public void setGoalsScored(int goalsScored) {
    this.goalsScored = goalsScored;
  }

  public int getGoalsConceded() {
    return goalsConceded;
  }

  public void setGoalsConceded(int goalsConceded) {
    this.goalsConceded = goalsConceded;
  }

  public int getGoalDifference() {
    return goalDifference;
  }

  public void calculateGoalDifference() {
    // the goal difference is the goals scored minus the goals conceded
    this.goalDifference = this.goalsScored - this.goalsConceded;
  }

  public int getPoints() {
    return points;
  }

  public void calculatePoints() {
    // the points total for a team is based up 3 points for a win and 1 for a draw
    this.points = (this.matchesWon*3) + this.matchesDrawn;
  }
  
  
  
  
  
}
