/**
 * League.java
 */
package org.com1028.project.js00928;

import java.util.List;
import java.util.StringTokenizer;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Class holding the object for the league table to be displayed in the GUI
 * 
 * @author Jack Simmonds
 */
public class League implements IFileHandler, Serializable {  
  
  /** Fields for the name of the league and the season its in (i.e. the years) */
  private String name = null;
  private String season = null;
  
  /** Lists for the matches played in the league and the teams in the league */
  private List<Match> matches = null;
  private List<Team> teams = null;
  
  /** Regex for the season format i.e. must be of the form: 2015/16 */
  private static final String SEASON_CODE = "(([0-9]{4})(/)([0-9]{2}))";
  
  /** Static constant for the filename for the saved league data */
  private static final String FILENAME = "league.txt";
  
  /** Serial version UID needed as the class implemetns Serializable */
  private static final long serialVersionUID = 2008328241799312674L;
  
  /**
   * Public constructor to initialse fields at object creation
   * 
   * @param name
   *          the name of the league
   * @param season
   *          the season the league is played in (i.e. the years)
   */
  public League(String name, String season) {
    super();
    this.name = name;
    // ensure the season passed through the constructor matches the regex
    if(!season.matches(SEASON_CODE)) {
      throw new IllegalArgumentException("Season does not match the correct format");
    }
    this.season = season;
    this.matches = new ArrayList<Match>();
    this.teams = new ArrayList<Team>();
  }
  
  public String getName() {
    return this.name;
  }
  
  public String getSeason() {
    return this.season;
  }
  
  public void addTeam(Team team) {
    // null check for the team object input
    if(team == null) {
      throw new NullPointerException("Null team object added");      
    }
    // ensure no duplication of teams occurs
    if (teams.contains(team)) {
      throw new IllegalArgumentException("Duplicate team added");
    }
    this.teams.add(team);
  }
  
  public List<Team> getTeams() {
    return this.teams;
  }
  
  public void addMatch(Match match) {
    // null check for the match object input
    if(match == null) {
      throw new NullPointerException("Null match object added");      
    }
    // ensure no duplication of matches occurs
    if (matches.contains(match)) {
      throw new IllegalArgumentException("Duplicate match added");
    }
    this.matches.add(match);          
    
    // once a match added to the league update the teams record
    for(Team team : teams) {
      if(team.getName().equals(match.getHomeTeam().getName())) {
        team.getRecord().updateRecord(match.getHomeResult(), match.getHomeScore(), match.getAwayScore());
      }
      
      if(team.getName().equals(match.getAwayTeam().getName())) {
        team.getRecord().updateRecord(match.getAwayResult(), match.getAwayScore(), match.getHomeScore());
      }
    }
  }
  
  /**
   * Algorithm to sort the teams first by points total then by goal difference using bubble sort
   */
  public void sortTeams() {
    // outer for loop to loop through the array as many times as the size of the array to sort all j elements 
    for(int j = 0; j < teams.size(); j++) {
      // inner for loop to move the last index and swap if necessary
      for(int i = teams.size() - 1; i > 0; i--) {
        // First priority to sort is the points totals
        if(teams.get(i-1).getRecord().getPoints() < teams.get(i).getRecord().getPoints()) {
          // If the team has a higher points total they are swapped in the list
          Collections.swap(teams, i-1, i);
        } 
        // otherwise if points totals are equal sort by goal difference
        else if(teams.get(i-1).getRecord().getPoints() == teams.get(i).getRecord().getPoints()) {
          if(teams.get(i-1).getRecord().getGoalDifference() < teams.get(i).getRecord().getGoalDifference()) {
            Collections.swap(teams, i-1, i);
          }
        }
      }
    }    
  }
   
  /** 
   * Read from the user file for the match data for the league
   */
  @Override
  public void readFromUserFile(String filename) {
    BufferedReader buffer = null;
    
    try {
      // read from the file selected in the input parameter
      FileReader in = new FileReader(filename);
      buffer = new BufferedReader(in);
      
      String line = buffer.readLine();
      
      // only execute when there is a line of text in the file
      while(line != null) {
        String homeName = null;
        String awayName = null;
        int homeScore = 0;
        int awayScore = 0;

        StringTokenizer tokens = new StringTokenizer(line);
        int count = 0;
        
        // only execute while there is more data to read in
        while(tokens.hasMoreTokens()) {
          String word = tokens.nextToken();
          count++;
          
          // read the home name first, then the home score, then the away score then finally
          // read in the away name to the system
          switch(count) {
            case 1:
              homeName = word;
              break;
            
            case 2:
              homeScore = Integer.parseInt(word);
              break;
              
            case 3: 
              awayScore = Integer.parseInt(word);       
              break;
              
            case 4: 
              awayName = word;
              break;
              
            default:
              break;
          }                   
        }
        
        Team homeTeam = null;
        Team awayTeam = null;
        
        // Find the team entered in the text file in the list of teams
        for(Team team : teams) {
          if(homeName.equals(team.getShortName())) {
            // assign the local team variables to the team found in the list
            homeTeam = team;
          }
          else if(awayName.equals(team.getShortName())) {
            // do the same for the away team
            awayTeam = team;
          }
        }
        
        // create a new match object with the two teams and the scores entered from the file
        Match match = new Match(homeTeam, awayTeam, homeScore, awayScore);
        
        // add the match to the list which in-turn updates the teams records
        addMatch(match);        
        
        line = buffer.readLine();
      }           
    }    
    catch (FileNotFoundException e) {
      System.out.println("Cannot find the file");
      e.printStackTrace();
    }
    catch (IOException e) {
      System.out.println("Cannot read the file");
      e.printStackTrace();
    }
    finally {
      // sort the teams as the league table will have changed
      sortTeams();
      // Close the file.
      if (buffer != null) {
        try {
          buffer.close();
        }
        catch (IOException e) {
          System.out.println("Could not close the file");
          e.printStackTrace();
        }
      }
    }
  }  
  
  /** 
   * Method to read from the save file of the class (uses FILENAME constant)
   */
  @SuppressWarnings("unchecked")
  @Override
  public void readFromSaveFile() {
    // ensure the text file is not empty
    if(!textFileEmpty(FILENAME)) {
      try {
        // use file input and object input streams to read from the save file (with a constant filename)
        FileInputStream fis = new FileInputStream(FILENAME);
        ObjectInputStream ois = new ObjectInputStream(fis);
        
        // cast the two list types to read the object
        this.teams = (List<Team>) ois.readObject();
        this.matches = (List<Match>) ois.readObject();
        
        // if the lists are null create new arraylists to prevent null pointer exception
        if(teams == null) {
          this.teams = new ArrayList<Team>();
        }
        if(matches == null) {
          this.matches = new ArrayList<Match>();
        }
        ois.close();           
      } catch (IOException e) {
        e.printStackTrace();
      } catch (ClassNotFoundException e) {
        e.printStackTrace();
      }
    }    
  }
  
  @Override
  public void writeToFile(String filename) {
    try {
      // use of object output streams to write the list of objects to a file
      FileOutputStream fos = new FileOutputStream(filename);
      ObjectOutputStream oos = new ObjectOutputStream(fos);   
      oos.writeObject(teams); 
      oos.writeObject(matches);
      oos.close(); 
    } 
    // catch any exceptions the code above may incur
    catch(Exception e) {
      e.printStackTrace();
    }    
  }
  
  /**
   * Method to see if a text file is empty to reduce exceptions with file handling
   */
  @Override
  public boolean textFileEmpty(String filename) {
    boolean empty = false;
    BufferedReader br;
    try {
      br = new BufferedReader(new FileReader(filename));
      // if the buffered reader cannot read the next line the file must be empty
      if (br.readLine() == null) {
        empty = true;
      }
    }
    // catch the appropriate file handling exceptions
    catch (FileNotFoundException e) {
      e.printStackTrace();
    }
    catch (IOException e) {
      e.printStackTrace();
    }         
    return empty;
  }
  
  /**
   * toString method used for the name of the league in a specific format
   * e.g. Premier League - 2015/16
   */
  @Override
  public String toString() {
    return this.name + " - " + this.season;
  }
  
  
}
