package com.musikrec.musikrec.Repositories;

import com.musikrec.musikrec.Dto.Responses.SongSearchResponse;
import com.musikrec.musikrec.Models.Song;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface SongRepository extends JpaRepository<Song, Integer> {

    @Query("select " +
            "s.id as songId, " +
            "s.title as title, " +
            "s.artist as artistName, " +
            "s.album.title as albumName " +
            "from Song s " +
            "where lower(s.title) like lower(concat('%', :query, '%'))")
    List<SongSearchResponse>searchSongs(@Param("query") String query);

}
