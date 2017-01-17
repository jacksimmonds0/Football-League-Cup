/**
 * TeamUI.java
 */
package org.com1028.project.js00928;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class TeamUI extends JDialog {
  
  private final JPanel contentPanel = new JPanel();
  private JTextField txtTeamName;
  private JTextField txtShortTeam;
  private League league = null;  
  private static final long serialVersionUID = -1746791468472143431L;
  
  
  /**
   * Launch the dialog box
   */
  public void runTeamDialog() {    
    try {
      TeamUI dialog = new TeamUI(league);
      dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
      dialog.setVisible(true);
    }
    catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * Create the dialog.
   */
  public TeamUI(final League league) {
    this.league = league;   
    
    setBounds(100, 100, 450, 300);
    getContentPane().setLayout(new BorderLayout());
    contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
    getContentPane().add(contentPanel, BorderLayout.CENTER);
    contentPanel.setLayout(null);
    {
      txtTeamName = new JTextField();
      txtTeamName.setBounds(119, 103, 294, 26);
      contentPanel.add(txtTeamName);
      txtTeamName.setColumns(10);
    }
    
    txtShortTeam = new JTextField();
    txtShortTeam.setBounds(119, 152, 293, 26);
    contentPanel.add(txtShortTeam);
    txtShortTeam.setColumns(10);
    
    JLabel lblNewLabel = new JLabel("Add Team");
    lblNewLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 27));
    lblNewLabel.setBounds(134, 25, 165, 43);
    contentPanel.add(lblNewLabel);
    
    JLabel lblTeamName = new JLabel("Team Name:");
    lblTeamName.setBounds(18, 108, 89, 16);
    contentPanel.add(lblTeamName);
    
    JLabel lblSubname = new JLabel("Sub-name:");
    lblSubname.setBounds(18, 157, 89, 16);
    contentPanel.add(lblSubname);
    {
      JPanel buttonPane = new JPanel();
      buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
      getContentPane().add(buttonPane, BorderLayout.SOUTH);
      {
        JButton okButton = new JButton("Add");
        okButton.addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent e) {
            Team team = new Team(txtTeamName.getText(), txtShortTeam.getText());
            league.addTeam(team);               
            txtTeamName.setText(null);
            txtShortTeam.setText(null);
          }
        });
        okButton.setActionCommand("OK");
        buttonPane.add(okButton);
        getRootPane().setDefaultButton(okButton);
      }
      {
        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent e) {
            LeagueUI leagueUI = new LeagueUI(league);
            leagueUI.getFrame().setVisible(true);
            
          }
        });
        cancelButton.setActionCommand("Cancel");
        buttonPane.add(cancelButton);
      }
    }
  }
}
