package com.anderson;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    /***
     *  Playlister program:
     *  This program takes a user artist and playlist amount to call the API echonest to return back
     *  a playlist of random songs that are similar to the artist entered. This playlist can then be
     *  saved in a database called songDB. This database can then be queried to retrieve previously saved
     *  playlists.
     */


    public static void main(String[] args) {


        //Setting the look and feel to Windows.
        //Must be called before instantiating the GUI

        /***Set windows look and feel***/
        try {
            UIManager.setLookAndFeel(
                    UIManager.getSystemLookAndFeelClassName());

        } catch (UnsupportedLookAndFeelException e) {
            System.out.println("Look and feel not supported!");
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            System.out.println("Class not found exception!");
            e.printStackTrace();
        } catch (InstantiationException e) {
            System.out.println("Instantiation exception!");
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            System.out.println("Illegal access!");
            e.printStackTrace();
        }

        //Create new playlister main window
        PlaylistGUI GUI = new PlaylistGUI();

    }
}

