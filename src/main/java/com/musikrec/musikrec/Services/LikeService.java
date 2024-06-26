package com.musikrec.musikrec.Services;

import com.musikrec.musikrec.Dto.Responses.LikeResponseDto;

import java.util.List;

public interface LikeService {


    List<LikeResponseDto> getAllLikeForUser();



    void insertLike(Integer songId);


    Integer deleteLike(Integer id);



}
