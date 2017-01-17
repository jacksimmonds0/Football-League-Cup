/**
 * RoundUI.java
 */
package org.com1028.project.js00928;

import javax.swing.JTextField;
import javax.swing.JPanel;
import javax.swing.JTextPane;

/**
 * Custom swing component for each round in the single elimination cup competition GUI
 * extends JPanel as the compenent is a JPanel with swing objects inside
 * 
 * @author Jack Simmonds
 */
public class RoundUI extends JPanel {

  /** The text panes to display the short name for each team */
  private JTextPane homeName;
  private JTextPane awayName;
  
  /** The text fields so the user can input the scores for each team */
  private JTextField homeScore;
  private JTextField awayScore;
  private static final long serialVersionUID = -5454910273015895865L;  
  
  public RoundUI() {
    // set the size of the JPanel
    this.setSize(89, 61);
    
    // setting up the size and location of each inner component
    homeName = new JTextPane();
    homeName.setSize(46, 16);
    homeName.setLocation(6, 6);
    add(homeName);
    
    // same is applied for the away name, score and the home score as above
    awayName = new JTextPane();
    awayName.setSize(46, 16);
    awayName.setLocation(6, 34);
    add(awayName);
    
    homeScore = new JTextField();
    homeScore.setSize(19, 16);
    homeScore.setLocation(64, 6);
    add(homeScore);
    
    awayScore = new JTextField();
    awayScore.setSize(19, 16);
    awayScore.setLocation(64, 34);
    add(awayScore);
  }
  
  // setters for the home and away names as these will change with the system
  public void setHomeName(String homeName) {
    this.homeName.setText(homeName);
  }
  
  public void setAwayName(String awayName) {
    this.awayName.setText(awayName);
  }
  
  // getters for all 4 fields contained within the custom component
  public JTextPane getHomeName() {
    return this.homeName;
  }
  
  public JTextPane getAwayName() {
    return this.awayName;
  }
  
  public JTextField getHomeScore() {
    return this.homeScore;
  }
  
  public JTextField getAwayScore() {
    return this.awayScore;
  }
}
