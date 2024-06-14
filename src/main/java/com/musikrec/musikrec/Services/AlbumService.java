package com.musikrec.musikrec.Services;

import com.musikrec.musikrec.Dto.Responses.AlbumDetailsDto;
import com.musikrec.musikrec.Dto.Responses.AlbumDto;
import java.util.List;

public interface AlbumService {

    List<AlbumDto> getAllAlbums ();


    AlbumDetailsDto getAlbum(Integer albumId);
}
