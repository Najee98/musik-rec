package com.musikrec.musikrec.Integration.LastFmAPI.ApiResponse;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@AllArgsConstructor
public class LastFmTrack {

    private String name;
    private LastFmArtist artist;
    private LastFmAlbum album;

}