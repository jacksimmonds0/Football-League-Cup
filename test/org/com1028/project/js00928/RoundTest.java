package org.com1028.project.js00928;

import static org.junit.Assert.assertEquals;

import org.junit.Test;


public class RoundTest {

  @Test
  public void testValid() {
    Team team1 = new Team("Chelsea", "CHE");
    Team team2 = new Team("Arsenal", "ARS");
    Match match = new Match(team1, team2, 2, 1);
    
    Round round = new Round(match);
        
    assertEquals(match, round.getMatch());
    assertEquals(team1, round.getMatch().getHomeTeam());
    assertEquals(team2, round.getMatch().getAwayTeam());
  }

}
