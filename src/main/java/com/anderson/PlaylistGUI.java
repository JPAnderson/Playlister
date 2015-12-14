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
    private JLabel errorLabel;
    private JButton searchForPlaylistButton;

    private Dimension d = new Dimension(300, 350);

    private String userArtist;
    private int userSongCount;
    private ArrayList<String> errorArrayList;

    protected PlaylistGUI(){

        setContentPane(rootPanel);
        rootPanel.setPreferredSize(d);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setVisible(true);


        submitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                userArtist = artistBox.getText();
                userSongCount = Integer.parseInt(playlistCountBox.getText());

                try {
                    PlaylistFinder list = new PlaylistFinder(userSongCount, userArtist);
                    errorArrayList = list.createPlayList();

                    if(errorArrayList.get(0).equals("ERROR")){
                        errorLabel.setText(errorArrayList.get(1));
                    }
                }catch(IOException ioe){
                    System.out.println("Something probably stupid went wrong");
                }

               DataBaseGUI dbg = new DataBaseGUI(errorArrayList);


            }
        });


        searchForPlaylistButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                PlaylistsViewGUI pGUI = new PlaylistsViewGUI();
            }
        });

        clearFieldsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                playlistCountBox.setText("");
                artistBox.setText("");
            }
        });


    }

}

//minneapolis edu simpleCardLayout
