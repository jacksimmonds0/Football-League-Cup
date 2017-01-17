/**
 * Match.java
 */
package org.com1028.project.js00928;

import java.io.Serializable;

/**
 * Class for a match object containing the two teams, their respective scores and the
 * corresponding results
 * 
 * @author Jack Simmonds
 */
public class Match implements Serializable {
  
  /** Fields for the home and away team objects */
  private Team homeTeam = null;
  private Team awayTeam = null;
  
  /** Fields for the home and away score */
  private int homeScore = 0;
  private int awayScore = 0;
  
  /** The result for each team of the match from the result enumeration */
  private Result homeResult = null;
  private Result awayResult = null;
  private static final long serialVersionUID = 8925019165024131662L;
  
  public Match(Team homeTeam, Team awayTeam, int homeScore, int awayScore) {
    super();
    this.homeTeam = homeTeam;
    this.awayTeam = awayTeam;
    this.homeScore = homeScore;
    this.awayScore = awayScore;
    
    // if clauses to set the correct result for each team
    if(homeScore > awayScore) {
      this.homeResult = Result.WIN;
      this.awayResult = Result.LOSS;
    }
    else if(homeScore < awayScore) {
      this.homeResult = Result.LOSS;
      this.awayResult = Result.WIN;
    }
    else {
      this.homeResult = Result.DRAW;
      this.awayResult = Result.DRAW;
    }
  }
  
  public Team getHomeTeam() {
    return homeTeam;
  }
  
  public void setHomeTeam(Team homeTeam) {
    this.homeTeam = homeTeam;
  }
  
  public Team getAwayTeam() {
    return awayTeam;
  }
  
  public void setAwayTeam(Team awayTeam) {
    this.awayTeam = awayTeam;
  }
  
  public int getHomeScore() {
    return homeScore;
  }
  
  public int getAwayScore() {
    return awayScore;
  }
  
  public Result getHomeResult() {
    return homeResult;
  }

  public Result getAwayResult() {
    return awayResult;
  }
  
  /**
   * Method to find the winner of the match and return the team object
   * @return the team object that is the winner of the match
   */
  public Team getWinner() {
    Team winner = null;
    if(homeResult == Result.WIN) {
      winner = homeTeam;
    }
    else if(awayResult == Result.WIN) {
      winner = awayTeam;
    }
    return winner;
  }
  
  

}
