/**
 * AllTests.java
 */
package org.com1028.project.js00928;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;


/**
 * Runs all the unit tests for the project in a simple JUnit 4 test suite 
 * 
 * @author Jack Simmonds
 */
@RunWith(Suite.class)
@SuiteClasses({ CupCompetitionTest.class, LeagueTest.class, MatchTest.class, RecordTest.class, RoundTest.class, TeamTest.class })
public class AllTests {
  
}
