package com.anderson;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.io.IOException;
import java.util.ArrayList;

/***********Main window GUI**************/

/*****COMPONENTS************/
public class PlaylistGUI extends JFrame{
    private JPanel rootPanel;
    private JTextField playlistCountBox;
    private JTextField artistBox;
    private JButton submitButton;
    private JButton clearFieldsButton;
    private JButton searchForPlaylistButton;
    private JLabel errorLabel;
    private JLabel errorLabel2;
    private Dimension d = new Dimension(300, 350);

    private String userArtist;
    private int userSongCount;
    private ArrayList<String> errorArrayList;

    /***CONSTRUCTOR***/
    protected PlaylistGUI(){

        setContentPane(rootPanel);
        rootPanel.setPreferredSize(d);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setVisible(true);

        /***WHEN SUBMIT IS CLICKED***/
        submitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    userArtist = artistBox.getText(); //Get the artist name from artistTextBox
                    userSongCount = Integer.parseInt(playlistCountBox.getText()); //Get the song count from playlistCountBox

                    PlaylistFinder list = new PlaylistFinder(userSongCount, userArtist); //Create new playlist finder.
                    errorArrayList = list.createPlayList(); //Call createPlaylist on finder object. Will return an arraylist of songs

                    if(errorArrayList.get(0).equals("ERROR")){ //If the first element in the arraylist == "ERROR"

                        errorLabel.setText(errorArrayList.get(1)); //Set the error labels
                        errorLabel2.setText(errorArrayList.get(2));

                    } else {
                        DataBaseGUI dbg = new DataBaseGUI(errorArrayList); //Everything checks out. Create the next form and send it the list of songs.
                    }

                }catch(IOException ioe){
                    System.out.println("Something went wrong"); //Probably won't reach here
                }catch(NumberFormatException nfe){
                    errorLabel2.setText("All fields required");//....probably
                }
            }
        });

        /***WHEN SEARCH LISTS IS CLICKED***/
        searchForPlaylistButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                PlaylistsViewGUI pGUI = new PlaylistsViewGUI(); //Show the find playlists form
            }
        });

        //Clear the textfields if clearFields is clicked
        clearFieldsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                playlistCountBox.setText("");
                artistBox.setText("");
            }
        });
    }
}


