package com.musikrec.musikrec.Services;

import com.musikrec.musikrec.Models.Album;
import java.util.List;

public interface AlbumService {

    List<Album> getAllAlbumsForArtist (String artist);


    Album getAlbumDetails(String title);
}
