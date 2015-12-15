package com.anderson;
/***
 * This GUI class was hardcoded. It is the form that displays when the user picks an artist and
 * number of songs. It displays a JList containing all the songs, and allows you to save the
 * playlist in the database
 *
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

public class DataBaseGUI extends JFrame {
    private JPanel rootPanel = new JPanel();
    private Dimension d = new Dimension(400, 350);
    private JLabel saveLabel = new JLabel();
    private JLabel title = new JLabel();
    private JLabel error = new JLabel();
    private JButton save = new JButton("Save list");
    private JButton confirmSave = new JButton("Save!");
    private JScrollPane JSP = new JScrollPane();
    private JList<String> songJList;
    private JTextField saveNameTextBox = new JTextField();

    private Database db = new Database();



    protected DataBaseGUI(ArrayList<String> songList){
      //  db.createDatabase();

        final String[] songArray = songList.toArray(new String[songList.size()]);
        songJList = new JList(songArray);

        rootPanel.setPreferredSize(d);
        setContentPane(rootPanel);
        setVisible(true);

        saveNameTextBox.setColumns(10);

        title.setText("Playlist Result:");
        rootPanel.add(title, BorderLayout.PAGE_START);
        rootPanel.add(JSP, BorderLayout.CENTER);
        JSP.setViewportView(songJList);
        rootPanel.add(save, BorderLayout.LINE_END);
        rootPanel.add(saveLabel, BorderLayout.SOUTH);
        rootPanel.add(saveNameTextBox, BorderLayout.SOUTH);
        rootPanel.add(confirmSave, BorderLayout.SOUTH);

        saveLabel.setVisible(false);
        saveNameTextBox.setVisible(false);
        confirmSave.setVisible(false);
        pack();

        save.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                saveLabel.setText("Please enter a name for this playlist");
                saveLabel.setVisible(true);
                saveNameTextBox.setVisible(true);
                confirmSave.setVisible(true);
            }
        });

        confirmSave.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                rootPanel.add(error, BorderLayout.PAGE_END);
                String toDataBase = saveNameTextBox.getText();
                String er = db.createAndFillTable(toDataBase, songArray);
                if(er != null){

                    error.setText(er);
                } else {
                    error.setText("Playlist saved as " + toDataBase + ". Safe to close window");
                }

            }
        });
    }
}
