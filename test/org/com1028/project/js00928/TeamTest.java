package org.com1028.project.js00928;

import static org.junit.Assert.assertEquals;

import org.junit.Test;


public class TeamTest {

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidShortNameFormat() {
    new Team("Chelsea", "CHEL");
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidName() {
    new Team("chelsea", "CHE");
  }
  
  @Test
  public void testValid() {
    Team team = new Team("Chelsea", "CHE");
    
    assertEquals("Chelsea", team.getName());
    assertEquals("CHE", team.getShortName());
  }

}
