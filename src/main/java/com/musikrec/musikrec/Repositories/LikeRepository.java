package com.musikrec.musikrec.Repositories;

import com.musikrec.musikrec.Models.Like;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LikeRepository extends JpaRepository<Like,Long> {

     Optional<Like> findById(Long likeId);


}
