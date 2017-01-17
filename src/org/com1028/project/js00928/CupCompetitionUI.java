/**
 * CupCompetitionUI.java
 */
package org.com1028.project.js00928;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;

import java.io.File;

/** 
 * User interface class for the cup competition using 7 custom swing components for the round
 * objects i.e. each match 
 * 
 * @author Jack Simmonds
 */
public class CupCompetitionUI {

  private JFrame frame;
  private RoundUI qf1;
  private RoundUI qf2;
  private RoundUI qf3;
  private RoundUI qf4;
  private RoundUI sf1;
  private RoundUI sf2;
  private RoundUI f;
  private CupCompetition cup;
  private String filename = null;  
    
   public void run() {
      try {
        CupCompetitionUI window = new CupCompetitionUI();
        window.frame.setVisible(true);
      }
      catch (Exception e) {
        e.printStackTrace();
      }    
   }

  /**
   * Create the application.
   */
  public CupCompetitionUI() {
    initialize();
  }

  /**
   * Initialize the contents of the frame.
   */
  private void initialize() {    
    cup = CupCompetition.getInstance();
    if(!cup.textFileEmpty("cupsave.txt")) {
      cup.readFromSaveFile();
     // qf1.setHomeName(cup.getRound1().getMatch().getHomeTeam().getShortName());
      
    }    
    
    // set up the frame the UI is displayed in
    frame = new JFrame();
    frame.setBounds(100, 100, 727, 520);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.getContentPane().setLayout(null);       
    
    // setting up all the rounds using the custom swing component - RoundUI
    qf1 = new RoundUI();
    qf1.setLocation(68, 99);
    frame.getContentPane().add(qf1);
    qf1.setLayout(null);
    
    qf2 = new RoundUI();
    qf2.setLocation(68, 186);
    frame.getContentPane().add(qf2);
    qf2.setLayout(null);
    
    qf3 = new RoundUI();
    qf3.setLocation(68, 272);
    frame.getContentPane().add(qf3);
    qf3.setLayout(null);
    
    qf4 = new RoundUI();
    qf4.setLocation(68, 363);
    frame.getContentPane().add(qf4);
    qf4.setLayout(null);
    
    sf1 = new RoundUI();
    sf1.setLocation(281, 146);
    frame.getContentPane().add(sf1);
    sf1.setLayout(null);
    
    sf2 = new RoundUI();
    sf2.setLocation(281, 323);
    frame.getContentPane().add(sf2);
    sf2.setLayout(null);
    
    f = new RoundUI();
    f.setLocation(510, 239);
    frame.getContentPane().add(f);
    f.setLayout(null);
    
    // the label for the title - Cup Competition
    JLabel lblCupCompetition = new JLabel("Cup Competition");
    lblCupCompetition.setFont(new Font("Lucida Grande", Font.PLAIN, 27));
    lblCupCompetition.setBounds(246, 26, 235, 61);
    frame.getContentPane().add(lblCupCompetition);
    
    // adding the action listener for the advance teams button
    JButton btnAdvanceTeams = new JButton("Advance Teams");
    btnAdvanceTeams.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        // check text fields are not null before advancing teams
        if(qf1.getHomeScore().getText() != null && qf1.getAwayScore().getText() != null
            && qf2.getHomeScore().getText() != null && qf2.getAwayScore().getText() != null) {
          // advance the teams to the first semi-final
          advanceTeams(qf1.getHomeName(), qf1.getAwayName(), qf1.getHomeScore(), qf1.getAwayScore(), 
              qf2.getHomeName(), qf2.getAwayName(), qf2.getHomeScore(), qf2.getAwayScore(), RoundName.SF1);          
        }
        
        if(qf3.getHomeScore().getText() != null && qf3.getAwayScore().getText() != null
            && qf4.getHomeScore().getText() != null && qf4.getAwayScore().getText() != null) {
          // advance the teams to the second semi-final
          advanceTeams(qf3.getHomeName(), qf3.getAwayName(), qf3.getHomeScore(), qf3.getAwayScore(), 
              qf4.getHomeName(), qf4.getAwayName(), qf4.getHomeScore(), qf4.getAwayScore(), RoundName.SF2);                                     
        }
        
        if(sf1.getHomeScore().getText() != null && sf1.getAwayScore().getText() != null
            && sf2.getHomeScore().getText() != null && sf2.getAwayScore().getText() != null) {
          // advance the teams to the final
          advanceTeams(sf1.getHomeName(), sf1.getAwayName(), sf1.getHomeScore(), sf1.getAwayScore(), 
              sf2.getHomeName(), sf2.getAwayName(), sf2.getHomeScore(), sf2.getAwayScore(), RoundName.F);
        }
        
      }
    });
    btnAdvanceTeams.setBounds(318, 442, 120, 40);
    frame.getContentPane().add(btnAdvanceTeams);
    
    JButton btnSelectMatches = new JButton("Select First Matches");
    btnSelectMatches.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        JFileChooser fileChooser = new JFileChooser();
        int returnValue = fileChooser.showOpenDialog(null);
        if(returnValue == JFileChooser.APPROVE_OPTION) {
          File selectedFile = fileChooser.getSelectedFile();
          filename = selectedFile.getName();  
          cup.readFromUserFile(filename);
          
          qf1.setHomeName(cup.getRound1().getMatch().getHomeTeam().getShortName());
          qf1.setAwayName(cup.getRound1().getMatch().getAwayTeam().getShortName());
          qf2.setHomeName(cup.getRound2().getMatch().getHomeTeam().getShortName());
          qf2.setAwayName(cup.getRound2().getMatch().getAwayTeam().getShortName());
          qf3.setHomeName(cup.getRound3().getMatch().getHomeTeam().getShortName());
          qf3.setAwayName(cup.getRound3().getMatch().getAwayTeam().getShortName());
          qf4.setHomeName(cup.getRound4().getMatch().getHomeTeam().getShortName());
          qf4.setAwayName(cup.getRound4().getMatch().getAwayTeam().getShortName());
        }
      }
    });
    
    btnSelectMatches.setBounds(450, 442, 149, 40);
    frame.getContentPane().add(btnSelectMatches);
    
    // open the league user interface
    JButton btnLeague = new JButton("League");
    btnLeague.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        // run the league UI
        LeagueUI.runLeague();
        // close the current window with the cup competition UI
        frame.setVisible(false);
        frame.dispose();
      }
    });
    btnLeague.setBounds(611, 442, 99, 40);
    frame.getContentPane().add(btnLeague);
    
    // closing the window
    frame.addWindowListener(new WindowAdapter() {
      public void windowClosing(WindowEvent e){
          int i = JOptionPane.showConfirmDialog(null, "Do you want to save changes?");
          if(i==0) {
            // if yes selected write to the save file and close
            cup.writeToFile("cupsave.txt");
            System.exit(0);
          }                         
      }
  });
  }
  
  
  private void advanceTeams(JTextPane homeName1, JTextPane awayName1, JTextField homeScore1, JTextField awayScore1,
     JTextPane homeName2, JTextPane awayName2, JTextField homeScore2, JTextField awayScore2, RoundName roundName) {
    Team homeTeam1 = null;
    Team awayTeam1 = null;

    // user the iterator design patter to iterate through the cup teams list
    for(Iterator iter = cup.getIterator(); iter.hasNext();) {
      Team team = (Team)iter.next();
      if(homeName1.getText().equals(team.getShortName())) {
        homeTeam1 = team;
      }
      else if(awayName1.getText().equals(team.getShortName())) {
        awayTeam1 = team;
      }
    }
    
    int homeScr1 = Integer.parseInt(homeScore1.getText());
    int awayScr1 = Integer.parseInt(awayScore1.getText());
      
    
    Team homeTeam2 = null;
    Team awayTeam2 = null;
    
    for(Iterator iter = cup.getIterator(); iter.hasNext();) {
      Team team = (Team)iter.next();
      if(homeName2.getText().equals(team.getShortName())) {
        homeTeam2 = team;
      }
      else if(awayName2.getText().equals(team.getShortName())) {
        awayTeam2 = team;
      }
    }
    
    int homeScr2 = Integer.parseInt(homeScore2.getText());
    int awayScr2 = Integer.parseInt(awayScore2.getText());
    
    
    Match match1 = new Match(homeTeam1, awayTeam1, homeScr1, awayScr1);
    Match match2 = new Match(homeTeam2, awayTeam2, homeScr2, awayScr2);
    Match match3 = new Match(match1.getWinner(), match2.getWinner(), 0, 0);
    
    Round round = new Round(match3);
    
    switch(roundName) {
      case SF1:
        cup.getRound1().setWinner(round);
        cup.getRound2().setWinner(round);
        sf1.setHomeName(match3.getHomeTeam().getShortName());
        sf1.setAwayName(match3.getAwayTeam().getShortName());
        break;
        
      case SF2:
        cup.getRound3().setWinner(round);
        cup.getRound4().setWinner(round);
        sf2.setHomeName(match3.getHomeTeam().getShortName());
        sf2.setAwayName(match3.getAwayTeam().getShortName());
        break;
        
      case F:
        cup.getRound1().getWinner().setWinner(round);
        cup.getRound3().getWinner().setWinner(round);
        f.setHomeName(match3.getHomeTeam().getShortName());
        f.setAwayName(match3.getAwayTeam().getShortName());
        break;
        
      default:
        break;
    }
    
  }
}
