package com.musikrec.musikrec.Integration.ApiResponse;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@JsonIgnoreProperties(ignoreUnknown = true)
public class LastFmResponse {
    private LastFmTracks lastFmTracks;

    public LastFmTracks getTracks() {
        return lastFmTracks;
    }

    public void setTracks(LastFmTracks lastFmTracks) {
        this.lastFmTracks = lastFmTracks;
    }

}

