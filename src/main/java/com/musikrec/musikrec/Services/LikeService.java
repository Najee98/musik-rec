package com.musikrec.musikrec.Services;

import com.musikrec.musikrec.Dto.Responses.LikeResponseDto;
import com.musikrec.musikrec.Models.Like;

import java.util.List;

public interface LikeService {


    List<LikeResponseDto> getAllLikeForUser(Long userId);



    void insertLike(Long songId,Long userId);


    Long deleteLike(Long id);



}
