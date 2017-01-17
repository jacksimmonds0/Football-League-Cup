package org.com1028.project.js00928;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;


public class RecordTest {
  
  private Record record = null;
  
  @Before
  public void before() throws Exception {
    record = new Record(0, 0, 0, 0, 0, 0);
  }

  @Test
  public void testUpdateRecordWin() {    
    
    record.updateRecord(Result.WIN, 2, 1);
    
    assertEquals(1, record.getMatchesPlayed());
    assertEquals(1, record.getMatchesWon());
    assertEquals(0, record.getMatchesDrawn());
    assertEquals(0, record.getMatchesLost());
    assertEquals(2, record.getGoalsScored());
    assertEquals(1, record.getGoalsConceded());
  }
  
  @Test
  public void testUpdateRecordLoss() {
    
    record.updateRecord(Result.LOSS, 0, 3);
    
    assertEquals(1, record.getMatchesPlayed());
    assertEquals(0, record.getMatchesWon());
    assertEquals(0, record.getMatchesDrawn());
    assertEquals(1, record.getMatchesLost());
    assertEquals(0, record.getGoalsScored());
    assertEquals(3, record.getGoalsConceded());
  }
  
  @Test
  public void testUpdateRecordDraw() {
    
    record.updateRecord(Result.DRAW, 1, 1);
    
    assertEquals(1, record.getMatchesPlayed());
    assertEquals(0, record.getMatchesWon());
    assertEquals(1, record.getMatchesDrawn());
    assertEquals(0, record.getMatchesLost());
    assertEquals(1, record.getGoalsScored());
    assertEquals(1, record.getGoalsConceded());
  }

}
