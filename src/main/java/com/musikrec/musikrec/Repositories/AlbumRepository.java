package com.musikrec.musikrec.Repositories;

import com.musikrec.musikrec.Models.Album;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AlbumRepository extends JpaRepository<Album, Long> {


    @Query("select a from Album a where a.artist = :artist")
    List<Album> getAllAlbumsForArtist (@Param("artist") String artist);




    @Query("select a from Album a join fetch a.songs " +
            " where a.title = :title")
    Album getAlbumDetails(@Param("title") String title);
}
