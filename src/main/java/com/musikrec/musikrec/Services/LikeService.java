package com.musikrec.musikrec.Services;

import com.musikrec.musikrec.Dto.Responses.SongResponseDto;

import java.util.List;

public interface LikeService {


    List<SongResponseDto> getAllLikeForUser();

    void insertLike(Integer songId);

    void DeleteLike(Integer id);



}
