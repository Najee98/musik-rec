package com.musikrec.musikrec.Repositories;

import com.musikrec.musikrec.Dto.Responses.LikeResponseDto;
import com.musikrec.musikrec.Models.Like;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface LikeRepository extends JpaRepository<Like,Integer> {


    @Query("select new com.musikrec.musikrec.Dto.Responses.LikeResponseDto(" +
            "s.id, " +
            "s.title, " +
            "s.artist.name," +
            "l.timestamp) " +
            "from Like l " +
            "join l.song s " +
            "join l.appUser u " +
            "where u.id = :userId")
    List<LikeResponseDto> getAllLikeForUser(@Param("userId") Integer userId);
}
