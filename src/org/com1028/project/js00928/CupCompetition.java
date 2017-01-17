/**
 * CupCompetition.java
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

/**
 * Class that defines the cup competition binary tree for use in the CupCompetitionUI
 * Also implements the file handler interface for saving and reading in data from the user
 * 
 * @author Jack Simmonds
 */
public class CupCompetition implements IFileHandler, Serializable, Container {
  
  /** Singleton pattern also used here as only one cup competition object should be instantiated */
  private static CupCompetition cup = new CupCompetition();
  
  /** This is a reverse binary tree as the tree grows from the leaf nodes not the head */
  private Round round1 = null;
  private Round round2 = null;
  private Round round3 = null;
  private Round round4 = null;
  
  /** The list for the 8 teams put into the first round quarter final matches */
  private List<Team> teams = null;
  
  /** Static constant for the filename the class saves to */
  private static final String FILENAME = "cupsave.txt";
  private static final long serialVersionUID = 7057177599654575769L;
  
  /**
   * Private constructor so the object cannot be instantiated by other classes
   * Creates the new array list of teams that partake in the cup competition
   */
  private CupCompetition() {
    super();
    this.teams = new ArrayList<Team>();
  }  
  
  /**
   * Get the single instance of this class due to the singleton pattern
   * @return
   */
  public static CupCompetition getInstance() {
    return cup;
  }
  
  /**
   * Method to create the tree - from the leaf nodes (quarter finals) and build upwards
   */
  public void createTree() {        
    // Ensure the list has all the elements in it
    if(teams.size() == 8) {
      // Create the leaf nodes with the matches based up on the user input  
      round1 = new Round(new Match(teams.get(0), teams.get(1), 0, 0));
      round2 = new Round(new Match(teams.get(2), teams.get(3), 0, 0));
      round3 = new Round(new Match(teams.get(4), teams.get(5), 0, 0));
      round4 = new Round(new Match(teams.get(6), teams.get(7), 0, 0));    
    }        
  }   

  /**
   * Read from the user selected file for the first round match-ups in the cup
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
        String homeShort = null;
        String awayName = null;
        String awayShort = null;
        
        StringTokenizer tokens = new StringTokenizer(line);
        int count = 0;
        
        // only execute while there is more data to read in
        while(tokens.hasMoreTokens()) {
          String word = tokens.nextToken();
          count++;
          
          // read the home name first, then the short name
          // then do the same for the away for the 3rd and 4th tokens
          switch(count) {
            case 1:
              homeName = word;
              break;
            
            case 2:
              homeShort = word;
              break;
              
            case 3: 
              awayName = word;       
              break;
              
            case 4: 
              awayShort = word;
              break;
              
            default:
              break;
          }                   
        }
        
        // use these values to create a home and away team object
        Team homeTeam = new Team(homeName, homeShort);
        Team awayTeam = new Team(awayName, awayShort);
        
        // add these objects to the list to be used to create the tree
        teams.add(homeTeam);
        teams.add(awayTeam);
                             
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
      // since all the teams have been read in the tree can be created
      createTree();
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
    // ensure the file is not empty
    if(!textFileEmpty(FILENAME)) {
      try {
        FileInputStream fis = new FileInputStream(FILENAME);
        ObjectInputStream ois = new ObjectInputStream(fis);
        List<Round> rounds = new ArrayList<Round>();
              
        // read the object by casting the list of rounds
        rounds = (List<Round>) ois.readObject();
        
        // if null make a new list
        if(rounds == null) {
          rounds = new ArrayList<Round>();
        }
        
        // set all the rounds in this class from the list read
        round1 = rounds.get(0);
        round2 = rounds.get(1);
        round3 = rounds.get(2);
        round4 = rounds.get(3);
        
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
      FileOutputStream fos = new FileOutputStream(filename);
      ObjectOutputStream oos = new ObjectOutputStream(fos);  
      
      // add all the rounds to a new array list
      List<Round> rounds = new ArrayList<Round>();
      rounds.add(round1);
      rounds.add(round2);
      rounds.add(round3);
      rounds.add(round4);
      
      // write the array list to a file
      oos.writeObject(rounds); 
      oos.close(); 
    } catch(Exception e) {
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
      if (br.readLine() == null) {
        empty = true;
      }
    }
    catch (FileNotFoundException e) {
      e.printStackTrace();
    }
    catch (IOException e) {
      e.printStackTrace();
    }         
    return empty;
  } 
  
  // Getters for all the rounds in the cup tournament
  public Round getRound1() {
    return round1;
  }
  
  public Round getRound2() {
    return round2;
  }
  
  public Round getRound3() {
    return round3;
  }
 
  public Round getRound4() {
    return round4;
  }
  
    
  /**
   * Get the iterator from the iterator pattern below
   */
  @Override
  public Iterator getIterator() {
    return new TeamIterator();
  }
  
  /**
   * Private class for the iterator pattern for iterating through the list of teams in
   * the CupCompetition class
   * 
   * @author Jack Simmonds
   */
  private class TeamIterator implements Iterator {
    
    /** Index of the array */
    int index;
    
    /**
     * Does the array have a value next in the array
     */
    @Override
    public boolean hasNext() {
      if(index < teams.size()){
        return true;
     }
     return false;
    }
    
    /** 
     * Get the next object in the array
     */
    @Override
    public Object next() {
      if(this.hasNext()){
        return teams.get(index++);
     }
     return null;
    }
  }
   
}

