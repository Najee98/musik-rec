package com.musikrec.musikrec.Integration.LastFmAPI.ApiResponse;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class LastFmTracks {
    private List<LastFmTrack> lastFmTrack;

    public List<LastFmTrack> getTrack() {
        return lastFmTrack;
    }

    public void setTrack(List<LastFmTrack> lastFmTrack) {
        this.lastFmTrack = lastFmTrack;
    }
}