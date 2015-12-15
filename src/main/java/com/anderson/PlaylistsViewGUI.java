package com.anderson;
/***
 * This window lets the user enter a database name previously created,
 * queries the songDB, and then returns the playlist contenets if it is found
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PlaylistsViewGUI extends JFrame {
    private JPanel rootPanel;
    private JTextField textField1; //Text field to get playlist name
    private JButton playlistSearchButton;
    private Database db = new Database(); //Create database that links to songDB
    private JList<String> songJList = new JList<String>();

    private Dimension d = new Dimension(350, 400);


    /***CONSTRUCTOR***/
    protected PlaylistsViewGUI(){

        setContentPane(rootPanel);
        rootPanel.setPreferredSize(d);
        songJList.setVisible(false);
        pack();
        setVisible(true);

        /***WHEN SEARCH PLAYLIST IS PRESSED***/
        playlistSearchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String[] toJlist = db.retrievePlaylist(textField1.getText()); //retrievePlaylist method returns array of songs in a playlist
                songJList = new JList<String>(toJlist); //Assign the toJlist array to the JList
                rootPanel.add(songJList);
                songJList.setVisible(true);
                pack();
            }
        });
    }
}
