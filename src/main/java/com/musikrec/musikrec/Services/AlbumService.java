package com.musikrec.musikrec.Services;

import com.musikrec.musikrec.Models.Album;
import java.util.List;

public interface AlbumService {

    List<Album> getAllAlbums ();


    Album getAlbum(Long albumId);
}
