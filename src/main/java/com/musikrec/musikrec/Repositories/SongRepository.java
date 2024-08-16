package com.musikrec.musikrec.Repositories;

import com.musikrec.musikrec.Dto.Responses.SongResponseDto;
import com.musikrec.musikrec.Dto.Responses.SongSearchResponse;
import com.musikrec.musikrec.Models.Song;
import com.musikrec.musikrec.User.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import java.util.List;
import java.util.Optional;

@Repository
public interface SongRepository extends JpaRepository<Song, Integer> {

    @Query("SELECT DISTINCT s FROM Song s LEFT JOIN FETCH s.users WHERE s.id = :songId")
    Optional<Song> findByIdWithUsers(@Param("songId") Integer songId);

    @Query("SELECT s FROM Song s JOIN s.users u WHERE u.id = :userId " +
            "ORDER BY s.id DESC ")
    List<Song> findSongHistoryByUserId(@Param("userId") Integer userId);

    @Query("select new com.musikrec.musikrec.Dto.Responses.SongResponseDto(" +
            "s.id, " +
            "s.title, " +
            "s.artist.name, " +
            "s.album.title, " +
            "s.imageUrl, " +
            "s.previewUrl) " +
            "from Song s " +
            "where lower(s.title) like lower(concat('%', :query, '%'))")
    List<SongResponseDto> searchSongs(@Param("query") String query);


    @Query("SELECT DISTINCT s FROM Song s WHERE s.previewUrl IS NOT NULL ")
    List<Song> findAllSongs();
}
