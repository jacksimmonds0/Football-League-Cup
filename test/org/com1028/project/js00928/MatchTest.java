package org.com1028.project.js00928;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;


public class MatchTest {
  
  private Match match1 = null;
  private Match match2 = null;
  private Match match3 = null;
  private Team arsenal = null;
  private Team chelsea = null;
  
  @Before
  public void before() throws Exception {
    arsenal = new Team("Arsenal", "ARS");
    chelsea = new Team("Chelsea", "CHE");
    
    match1 = new Match(arsenal, chelsea, 0, 1);
    match2 = new Match(chelsea, arsenal, 1, 1);
    match3 = new Match(chelsea, arsenal, 2, 0);
  }

  @Test
  public void testHomeResults() {
    assertEquals(Result.LOSS, match1.getHomeResult());
    assertEquals(Result.DRAW, match2.getHomeResult());
    assertEquals(Result.WIN, match3.getHomeResult());
  }
  
  @Test
  public void testAwayResults() {
    assertEquals(Result.WIN, match1.getAwayResult());
    assertEquals(Result.DRAW, match2.getAwayResult());
    assertEquals(Result.LOSS, match3.getAwayResult());
  }
  
  @Test
  public void testHomeScore() {
    assertEquals(0, match1.getHomeScore());
    assertEquals(1, match2.getHomeScore());
    assertEquals(2, match3.getHomeScore());    
  }
  
  @Test
  public void testAwayScore() {
    assertEquals(1, match1.getAwayScore());
    assertEquals(1, match2.getAwayScore());
    assertEquals(0, match3.getAwayScore());    
  }
  

}
