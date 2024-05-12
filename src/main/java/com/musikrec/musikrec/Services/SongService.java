package com.musikrec.musikrec.Services;



import com.musikrec.musikrec.Models.Song;
import java.util.List;

public interface SongService {

    List<Song> getAllSongs();


    Song getSong(Long id);


    void insertSong(Song song);


    void updateSong(Song song);


    Long deleteSong(Long id);

}
