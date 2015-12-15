package com.anderson;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Database does all the work for getting from and sending to the database.
 */

public class Database {
    static final String DATABASE_URL = "jdbc:mysql://localhost:3306/";
    private static String DB_NAME = "songDB";
    static final String USER = "root"; //TODO replace with your credentials
    static final String PASSWORD = "team13"; //TODO replace with your credentials
    static Statement statement = null;
    static Connection connection = null;
    ResultSet resultSet = null;
    String tableName;

    public Database(){
        try {
            try{
                String Driver = "com.mysql.jdbc.Driver";
                Class.forName(Driver);
            } catch (ClassNotFoundException cnfe){
                System.out.println("Drivers not found. Exit");
                System.exit(-1);
            }

            connection = DriverManager.getConnection(DATABASE_URL + DB_NAME, USER, PASSWORD);
            statement = connection.createStatement();

            System.out.println("Database created/connected");

        } catch (SQLException sqle){
            sqle.printStackTrace();

        }
    }


    /***CREATE PLAYLIST AND POPULATE IT**/
    public String createAndFillTable(String tableName, String[] songArray){

        String errorString = "Alphanumeric input only please. No spaces"; //Send this if something goes wrong
        String clearString = null; //Send this if nothing goes wrong
        try {
            ArrayList<String> songArrayList = new ArrayList<String>(Arrays.asList(songArray));
            String tableUpdate;

            String createPlaylistTable = "CREATE TABLE " + tableName + " (song varchar(100))";
            statement.executeUpdate(createPlaylistTable);

            String preparedStatement = "INSERT INTO " + tableName + " VALUES(?)";
            PreparedStatement psInsert = connection.prepareStatement(preparedStatement);


            for(String song : songArrayList){
                psInsert.setString(1, song);
                psInsert.executeUpdate();

                System.out.println(song);
                System.out.println(song + " inserted into " + tableName);
            }
            return clearString;

        } catch (Exception e){
            return errorString;
        }
    }

    /***FIND PLAYLIST AND SEND IT BACK AS AN ARRAY***/
    public String[] retrievePlaylist(String plName){
        this.tableName = plName;
        ArrayList<String> retrievePL = new ArrayList<String>();
        String preparedStatement = "SELECT * FROM " + this.tableName;

        try{
            PreparedStatement psSearch = connection.prepareStatement(preparedStatement);

            resultSet = psSearch.executeQuery();

            while(resultSet.next()){
                String songToAdd = resultSet.getString("song");
                System.out.println(songToAdd);
                retrievePL.add(songToAdd);
            }

            String[] arrayPL = retrievePL.toArray(new String[retrievePL.size()]);
            return arrayPL;



        }catch(SQLException e){
            System.out.println(e.getCause());
            String[] errorArray = {"ERROR"};
            return errorArray;
        }
    }
}
