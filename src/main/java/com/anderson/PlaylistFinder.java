package com.anderson;

import com.echonest.api.v4.*;

import java.io.IOException;
import java.util.ArrayList;
//TODO add echonest libraries to github

/**
 * Created by Jack on 12/7/2015.
 */



public class PlaylistFinder {
    private int playListCount;
    private String artist;
    ArrayList<String> songOrErrorReturnList = new ArrayList<String>();

    public PlaylistFinder(int songs, String artist){
       this.playListCount = songs;
        this.artist = artist;
    }

    public ArrayList createPlayList() throws IOException{
        try{
            EchoNestAPI en = new EchoNestAPI("SEFRW8AEZGLGAWCA3");
            BasicPlaylistParams params = new BasicPlaylistParams();
            params.addArtist(this.artist);
            params.setType(BasicPlaylistParams.PlaylistType.ARTIST_RADIO);
            params.setResults(this.playListCount);
            Playlist playlist = en.createBasicPlaylist(params);

            for (Song song : playlist.getSongs()) {
                songOrErrorReturnList.add(song.getTitle() + " - " + song.getArtistName());
//                System.out.println(song.getTitle());
//                System.out.println(song.getArtistName());
            }
            return songOrErrorReturnList;

        }catch(EchoNestException ENE){
            songOrErrorReturnList.add(0, "ERROR");
            songOrErrorReturnList.add(1, "Please enter a valid artist name");
            return songOrErrorReturnList;

        } finally {
          //  songOrErrorReturnList.clear();
        }
    }

}

