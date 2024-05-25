package com.musikrec.musikrec.Repositories;

import com.musikrec.musikrec.Dto.Responses.PlaylistDetailsResponseDto;
import com.musikrec.musikrec.Dto.Responses.PlaylistResponseDto;
import com.musikrec.musikrec.Models.Playlist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlaylistRepository extends JpaRepository<Playlist, Long> {

    @Query("select new com.musikrec.musikrec.Dto.Responses.PlaylistResponseDto(" +
            "p.id, " +
            "p.name, " +
            "p.description) " +
            "from Playlist p where p.appUser.id = :userId")
    List<PlaylistResponseDto> getAllPlaylistsForUser(@Param("userId") Long userId);

    @Query("select p from Playlist p join fetch p.songs " +
            "where p.id = :playlistId and p.appUser.id = :userId")
    PlaylistDetailsResponseDto getPlaylist(@Param("userId") Long userId, @Param("playlistId") Long playlistId);
}
