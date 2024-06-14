package com.musikrec.musikrec.Services;

import com.musikrec.musikrec.Dto.Responses.LikeResponseDto;

import java.util.List;

public interface LikeService {


    List<LikeResponseDto> getAllLikeForUser(Integer userId);



    void insertLike(Integer songId,Integer userId);


    Integer deleteLike(Integer id);



}
