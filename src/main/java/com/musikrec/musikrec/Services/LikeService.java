package com.musikrec.musikrec.Services;

import com.musikrec.musikrec.Dto.Responses.LikeResponseDto;

import java.util.List;

public interface LikeService {


    List<LikeResponseDto> getAllLikeForUser(Long userId);



    void insertLike(Long songId,Long userId);


    Long deleteLike(Long id);



}
