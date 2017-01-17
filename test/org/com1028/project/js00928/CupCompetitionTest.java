package org.com1028.project.js00928;

import static org.junit.Assert.assertEquals;

import org.junit.Test;


public class CupCompetitionTest {

  /**
   * Tests both the read in the matches as well as the create tree as that method is called
   * within "readFromUserFile" along with the getters for each round
   */
  @Test
  public void testValidReadinFirstMatches() {
    CupCompetition cup = CupCompetition.getInstance();
    
    cup.readFromUserFile("testcup.txt");
    
    assertEquals("CHE", cup.getRound1().getMatch().getHomeTeam().getShortName());
    assertEquals("ARS", cup.getRound1().getMatch().getAwayTeam().getShortName());
    assertEquals("MNU", cup.getRound2().getMatch().getHomeTeam().getShortName());
    assertEquals("MCI", cup.getRound2().getMatch().getAwayTeam().getShortName());
    assertEquals("LIV", cup.getRound3().getMatch().getHomeTeam().getShortName());
    assertEquals("EVE", cup.getRound3().getMatch().getAwayTeam().getShortName());
    assertEquals("STO", cup.getRound4().getMatch().getHomeTeam().getShortName());
    assertEquals("TOT", cup.getRound4().getMatch().getAwayTeam().getShortName());
  }

}
