package com.musikrec.musikrec.Integration.ApiResponse;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class LastFmTrackResponse {

    @JsonProperty("track")
    private LastFmTrack lastFmTrack;

    public LastFmTrack getTrack() {
        return lastFmTrack;
    }

    public void setTrack(LastFmTrack lastFmTrack) {
        this.lastFmTrack = lastFmTrack;
    }

}
