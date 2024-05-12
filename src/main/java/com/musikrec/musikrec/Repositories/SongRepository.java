package com.musikrec.musikrec.Repositories;

import com.musikrec.musikrec.Models.Song;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;




@Repository
public interface SongRepository extends JpaRepository<Song, Long> {

}
