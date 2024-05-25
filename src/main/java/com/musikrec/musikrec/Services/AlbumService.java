package com.musikrec.musikrec.Services;

import com.musikrec.musikrec.Dto.Responses.AlbumDetailsDto;
import com.musikrec.musikrec.Dto.Responses.AlbumDto;
import com.musikrec.musikrec.Models.Album;
import java.util.List;

public interface AlbumService {

    List<AlbumDto> getAllAlbums ();


    AlbumDetailsDto getAlbum(Long albumId);
}
