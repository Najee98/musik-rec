package com.musikrec.musikrec.Repositories;

import com.musikrec.musikrec.Models.Playlist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PlaylistRepository extends JpaRepository<Playlist, Long> {

   // Optional<Playlist> findByName(String name);

    //Optional<Playlist> findById(Long id);

    //void deleteById(Long id);
}
