/**
 * LeagueUI.java
 */
package org.com1028.project.js00928;

import java.awt.EventQueue;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableColumn;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.JTextField;

/** 
 * The GUI class for the league which is where the system initially runs from to show the league
 * table, add teams along with match data as well as opening the cup competition UI 
 * 
 * @author Jack Simmonds
 */
public class LeagueUI {
  
  private JFrame frame;
  
  /** The field for the league table in the form of a JTable */
  private JTable table;
  
  /** Scroll pane so the table can be scrolled if necessary */
  private JScrollPane scrollPane;
  
  /** The name of the file chosen by the user for match data input */ 
  private String filename = null;
  
  /** The league object that the class will use */
  private League league = new League("Premier League", "2015/16");
  
  /** The text field for the title (name of the league and the season */
  private JTextField txtName;
  
  /** Buttons for the bottom of the UI respectively */
  private JButton btnSelectMatchData;
  private JButton btnAddTeam;
  private JButton btnCupCompetition;

  
  /**
   * Launch the application.
   */
  public static void main(String[] args) {
    EventQueue.invokeLater(new Runnable() {
      public void run() {
        runLeague();
      }
    });
  }
  
  public static void runLeague() {
    try {
      LeagueUI window = new LeagueUI(new League("Premier League", "2015/16"));
      window.getFrame().setVisible(true);
    }
    catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * Create the application.
   */
  public LeagueUI(League league) {
    this.league = league;
    if(this.league.getTeams().isEmpty()) {
      this.league.readFromSaveFile();
    }    
    initialize();    
  }

  /**
   * Initialize the contents of the frame.
   */
  private void initialize() {
    setFrame(new JFrame());
    getFrame().setBounds(100, 100, 700, 563);
    getFrame().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    getFrame().getContentPane().setLayout(null);     
    
    league.sortTeams();    
    
    scrollPane = new JScrollPane();
    scrollPane.setBounds(67, 89, 543, 355);
    getFrame().getContentPane().add(scrollPane);
    
    // set up the table
    setUpTable();    
    scrollPane.setViewportView(table);      
    
    // selecting the match data text file using a JFileChooser
    btnSelectMatchData = new JButton("Select Match Data");
    btnSelectMatchData.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        JFileChooser fileChooser = new JFileChooser();
        int returnValue = fileChooser.showOpenDialog(null);
        if(returnValue == JFileChooser.APPROVE_OPTION) {          
          File selectedFile = fileChooser.getSelectedFile();
          // get the filename of the file selected by the user
          filename = selectedFile.getName();  
          // use the file handling method from league to read the file correctly
          league.readFromUserFile(filename);
          // set up the table again as the league data will have changed due to file input
          setUpTable();
        }
      }   
    });
    btnSelectMatchData.setBounds(89, 470, 143, 41);
    getFrame().getContentPane().add(btnSelectMatchData);
    
    // setting up the title
    txtName = new JTextField();
    txtName.setEditable(false);
    txtName.setFont(new Font("Lucida Grande", Font.PLAIN, 27));
    // title uses the overriding toString method in the league class
    txtName.setText(league.toString());
    txtName.setBounds(142, 17, 385, 60);
    getFrame().getContentPane().add(txtName);
    txtName.setColumns(10);
    
    // open the add team dialog box
    btnAddTeam = new JButton("Add Team");
    btnAddTeam.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        // pass the current league object to the add team dialog
        TeamUI teamUI = new TeamUI(league);
        // run the dialog
        teamUI.runTeamDialog();
        // dispose of the current user interface
        frame.setVisible(false);
        frame.dispose(); 
      }
    });
    btnAddTeam.setBounds(262, 470, 143, 41);
    getFrame().getContentPane().add(btnAddTeam);
    
    // button listener for the opening the cup competition user interface
    btnCupCompetition = new JButton("Cup Competition");
    btnCupCompetition.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        // create a new cup competition object and run it
        CupCompetitionUI cupUI = new CupCompetitionUI();
        cupUI.run();
        // dispose of the current user interface (the leagueUI)
        frame.setVisible(false);
        frame.dispose();
      }
    });
    btnCupCompetition.setBounds(437, 470, 143, 41);
    getFrame().getContentPane().add(btnCupCompetition);
    
    // managing the closing of the window if the user presses the red x
    frame.addWindowListener(new WindowAdapter(){
      public void windowClosing(WindowEvent e){
          // confirm the changes will be saved by the user
          int i = JOptionPane.showConfirmDialog(null, "Do you want to save changes?");
          if(i==0) {
            // write the lists to a file to be read upon re-opening the GUI 
            league.writeToFile("league.txt");
            // close the system
            System.exit(0);
          }                         
      }
  });
    
  }
  
  /**
   * Helper method to set up the data contained within the JTable for each row for 
   * the table set up method
   * 
   * @return the 2D array of objects for the table
   */
  private Object[][] setTableData() {
    List<Team> teams = league.getTeams();
    Object[][] data = new Object[teams.size()][10];
    
    // for loop to iterate through the rows of the table and print information on each team
    for(int i = 0; i < teams.size(); i ++) {
      data[i][0] = i + 1;                                         // position in the league
      data[i][1] = teams.get(i).getName();                        // name
      data[i][2] = teams.get(i).getRecord().getMatchesPlayed();   // matches played
      data[i][3] = teams.get(i).getRecord().getMatchesWon();      // won
      data[i][4] = teams.get(i).getRecord().getMatchesDrawn();    // drawn
      data[i][5] = teams.get(i).getRecord().getMatchesLost();     // lost
      data[i][6] = teams.get(i).getRecord().getGoalsScored();     // scored
      data[i][7] = teams.get(i).getRecord().getGoalsConceded();   // conceded
      data[i][8] = teams.get(i).getRecord().getGoalDifference();  // goal difference
      data[i][9] = teams.get(i).getRecord().getPoints();          // points
    } 
    return data;
  }
  
  /**
   * Method to set up all the table including the column names and the row data from the method above
   */
  private void setUpTable() {
    String[] columnNames = {"", "Name", "Pld", "Won", "Draw", "Loss", "F", "A", "GD", "Pts"};    
    Object[][] data = setTableData();
    
    table = new JTable(data, columnNames);
    table.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
    
    TableColumn column = null;
    // set up the varying column widths
    for(int i = 0; i < 10; i++) {
      column = table.getColumnModel().getColumn(i);
      // set the team name column width
      if(i == 1) {
        column.setPreferredWidth(200);
      }
      // set the position in the league column width
      else if(i == 0) {
        column.setPreferredWidth(30);
      }
      // all other columns are a standard width of 50
      else {
        column.setPreferredWidth(50);
      }
    }    
    // set the row height and bounds of the table
    table.setRowHeight(20);   
    table.setBounds(0, 0, 450, 278);
    
    // add the table to a scroll pane so the table can be scrolled
    scrollPane.setViewportView(table);    
  }

  public JFrame getFrame() {
    return frame;
  }

  public void setFrame(JFrame frame) {
    this.frame = frame;
  }
  
  
}
