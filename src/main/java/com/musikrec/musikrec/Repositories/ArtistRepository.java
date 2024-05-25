package com.musikrec.musikrec.Repositories;

import com.musikrec.musikrec.Models.Artist;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArtistRepository extends JpaRepository<Artist, Long> {

    Artist findByName(String name);
}
