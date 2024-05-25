package com.musikrec.musikrec.Services;



import com.musikrec.musikrec.Dto.Responses.SongResponseDto;
import com.musikrec.musikrec.Dto.Responses.SongSearchResponse;
import com.musikrec.musikrec.Models.Song;
import java.util.List;

public interface SongService {

    List<SongResponseDto> getAllSongs();

    Song getSong(Long id);

    void addSongToPlaylist(Long songId, Long playlistId);

    void removeSongFromPlaylist(Long songId, Long playlistId);

    List<SongSearchResponse> searchSong(String query);
}
