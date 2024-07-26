package com.musikrec.musikrec.Services;



import com.musikrec.musikrec.Dto.Responses.SongDetailsResponseDto;
import com.musikrec.musikrec.Dto.Responses.SongResponseDto;
import com.musikrec.musikrec.Dto.Responses.SongSearchResponse;
import com.musikrec.musikrec.Models.Song;
import java.util.List;

public interface SongService {

    List<SongResponseDto> getAllSongs();

    SongDetailsResponseDto getSong(Integer id);

    void addSongToPlaylist(Integer songId, Integer playlistId);

    void removeSongFromPlaylist(Integer songId, Integer playlistId);

    List<SongSearchResponse> searchSong(String query);

    void insertHistory(Integer songId);

    List<SongResponseDto> getUserHistory();
}
