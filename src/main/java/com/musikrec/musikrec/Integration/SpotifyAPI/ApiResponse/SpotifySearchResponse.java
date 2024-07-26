package com.musikrec.musikrec.Integration.SpotifyAPI.ApiResponse;

import com.musikrec.musikrec.Integration.SpotifyAPI.ApiResponse.Utils.Tracks;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SpotifySearchResponse {

    private Tracks tracks;

//    Integer id;
//    String name;
//    String artist;
//    String album;
//    String image;
//    String url;

}
