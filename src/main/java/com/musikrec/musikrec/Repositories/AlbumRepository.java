package com.musikrec.musikrec.Repositories;

import com.musikrec.musikrec.Models.Album;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AlbumRepository extends JpaRepository<Album, Long> {


    @Query("select a from Album a where a.id = :albumId")
    List<Album> getAllAlbums(@Param("albumId") Long albumId);




    @Query("select a from Album a join fetch a.songs " +
            " where a.id = :albumId")
    Album getAlbum(@Param("albumId") Long albumId);
}
