/**
 * Team.java
 */
package org.com1028.project.js00928;

import java.io.Serializable;

/**
 * Class for defining the teams that will partake in the league
 * 
 * @author Jack Simmonds
 */
public class Team implements Serializable {
  
  /** The name of the team e.g. Chelsea */
  private String name = null;
  
  /** The shortened name of the team e.g, CHE */
  private String shortName = null;
  
  /** The teams record */
  private Record record = null;
  
  /** Regular expression for validating the short name for the team */
  private static final String SHORT_NAME_VALIDATION = "([A-Z]{3})";
  private static final long serialVersionUID = 1L;
  
  public Team(String name, String shortName) {
    // ensure the first character in the teams name is uppercase
    if(!Character.isUpperCase(name.charAt(0))) {
      throw new IllegalArgumentException("Team name must be upper case");
    }
    this.name = name;
    
    // ensure the short name matches the regular expression above
    if(!shortName.matches(SHORT_NAME_VALIDATION)) {
      throw new IllegalArgumentException("Short team name must be 3 characters all upper case");
    }
    this.shortName = shortName;
    
    // Each time a team object is created the teams record must be 0 for all fields
    this.record = new Record(0, 0, 0, 0, 0, 0);
  }
  
  public String getName() {
    return this.name;
  }
  
  public Record getRecord() {
    return this.record;
  }
  
  public String getShortName() {
    return this.shortName;
  }
  

}
