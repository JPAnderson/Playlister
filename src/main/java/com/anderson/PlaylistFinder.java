package com.anderson;

import com.echonest.api.v4.*;

import java.io.IOException;
import java.util.ArrayList;


/**
 * This class sets up and makes the echonest calls to retrieve the playlist.
 */



public class PlaylistFinder {
    private int playListCount;
    private String artist;
    ArrayList<String> songOrErrorReturnList = new ArrayList<String>();

    /***CONSTRUCTOR***/
    public PlaylistFinder(int songs, String artist){
       this.playListCount = songs;
        this.artist = artist;
    }

    public ArrayList createPlayList() throws IOException{
        try{
            EchoNestAPI en = new EchoNestAPI("SEFRW8AEZGLGAWCA3"); //Echonest key
            BasicPlaylistParams params = new BasicPlaylistParams(); //Parameters of basic playlist
            params.addArtist(this.artist); //Add the artist
            params.setType(BasicPlaylistParams.PlaylistType.ARTIST_RADIO); //Find other songs based on the artist supplied
            params.setResults(this.playListCount); //Get this many songs
            Playlist playlist = en.createBasicPlaylist(params); //Create a basic playlist with these parameters

            //For every song object in the songs returns playlist, add the song name and artist to an array list.
            for (Song song : playlist.getSongs()) {
                songOrErrorReturnList.add(song.getTitle() + " - " + song.getArtistName());
            }
            return songOrErrorReturnList; //return said array list

            //If there's an echonest exception
        }catch(EchoNestException ENE){
            songOrErrorReturnList.add(0, "ERROR"); //Add pre-recorded error  messages to the arraylist and send THAT
            songOrErrorReturnList.add(1, "Please ensure artist names are correct");
            songOrErrorReturnList.add(2, "and that you are asking for 1-100 songs");
            return songOrErrorReturnList;
        }
    }

}

