package com.musikrec.musikrec.Services;

import com.musikrec.musikrec.Dto.Responses.LikeResponseDto;
import com.musikrec.musikrec.Models.Like;

import java.util.List;

public interface LikeService {


    List<LikeResponseDto> getAllLikeForUser(Long userId);


    Like getLike(Long id);


    void insertLike(Like like);


    Long deleteLike(Long id);



}
