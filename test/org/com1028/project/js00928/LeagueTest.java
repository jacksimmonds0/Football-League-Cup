/**
 * LeagueTest.java
 */
package org.com1028.project.js00928;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

/**
 * Class selected for detailed unit testing using JUnit 4 as the league class links the most of
 * the other classes in the UML class diagram
 * 
 * @author Jack Simmonds
 */
public class LeagueTest {
  
  private League league = null;
  private Team arsenal = null;
  private Team chelsea = null;
  private Team manutd = null;
  private Team mancity = null;
  private Team liverpool = null;
  
  /**
   * Set up the league, team and match objects for use in each of the tests
   * Add the objects to the respective lists
   * 
   * @throws Exception
   */
  @Before
  public void before() throws Exception {
    league = new League("Premier League", "2015/16");
    
    arsenal = new Team("Arsenal", "ARS");
    chelsea = new Team("Chelsea", "CHE");
    manutd = new Team("Manchester Utd", "MNU");
    mancity = new Team("Manchester City", "MCI");
    liverpool = new Team("Liverpool", "LIV");
    
    league.addTeam(arsenal);
    league.addTeam(chelsea);
    league.addTeam(manutd);
    league.addTeam(mancity);
    league.addTeam(liverpool);
    
    Match match1 = new Match(arsenal, chelsea, 2, 1);
    Match match2 = new Match(manutd, liverpool, 2, 3);
    Match match3 = new Match(arsenal, mancity, 1, 4);
    Match match4 = new Match(mancity, manutd, 3, 0);
    Match match5 = new Match(chelsea, manutd, 2, 2);
    
    league.addMatch(match1);
    league.addMatch(match2);
    league.addMatch(match3);
    league.addMatch(match4);
    league.addMatch(match5);
  }

  /**
   * Test that all the correct points can be retrieved as expected
   */
  @Test
  public void testValid() {
    assertEquals(3, league.getTeams().get(0).getRecord().getPoints());
    assertEquals(1, league.getTeams().get(1).getRecord().getPoints());
    assertEquals(1, league.getTeams().get(2).getRecord().getPoints());
    assertEquals(6, league.getTeams().get(3).getRecord().getPoints());
    assertEquals(3, league.getTeams().get(4).getRecord().getPoints());
  }
  
  /**
   * Test that the validation for the season code regex in the league constructor is correct
   */
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidSeasonCode() {
    // create a league object with invalid season field
    new League("Premier League", "16/17");
  }
  
  /**
   * Test that the league sort algorithm works as intended
   */
  @Test
  public void testLeagueSort() {
    // sort the teams and check the order has been changed
    assertEquals(league.getTeams().get(0), arsenal);
    league.sortTeams();    
    assertEquals(league.getTeams().get(0), mancity);
  }
  
  /**
   * Test that matches are read in and the league is changed accordingly
   */
  @Test
  public void testReadInMatches() {
    assertEquals(league.getTeams().get(0), arsenal);
    
    // after reading in the text file the order of the league changes
    league.readFromUserFile("testmatches.txt");
    league.sortTeams();   
    
    // from arsenal first to chelsea being first
    assertEquals(league.getTeams().get(0), chelsea);
  }

  /**
   * Test that the save and read features have been implemented correctly
   */
  @Test
  public void testSaveAndRead() {
    league.writeToFile("testleague.txt");
    
    // create a new league and check the teams list is empty
    League league1 = new League("A-league", "2015/16");
    assertEquals(true, league1.getTeams().isEmpty());
    
    league1.readFromSaveFile();
    league1.sortTeams();
 
    // after reading from the save file the first in the list should be chelsea
    assertEquals("CHE", league1.getTeams().get(0).getShortName());
  }
  
  /**
   * Use the empty text file in the project to test this helper file handling 
   * method has been implemented correctly
   */
  @Test
  public void testEmptyTextFile() {
    // the text file is intended to be empty
    assertEquals(true, league.textFileEmpty("empty.txt"));
  }
      
  
}
