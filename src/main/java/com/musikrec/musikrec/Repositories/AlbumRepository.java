package com.musikrec.musikrec.Repositories;

import com.musikrec.musikrec.Models.Album;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AlbumRepository extends JpaRepository<Album, Long> {


    @Query("SELECT a FROM Album a WHERE a.artist = :artist")
    List<Album> getAllAlbums (@Param("artist") String artist);

}
