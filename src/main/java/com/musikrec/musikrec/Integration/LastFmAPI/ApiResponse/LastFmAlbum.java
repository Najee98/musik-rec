package com.musikrec.musikrec.Integration.LastFmAPI.ApiResponse;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class LastFmAlbum {
    private String title;

    @JsonProperty("image")
    private List<LastFmAlbumImage> lastFmAlbumImages;


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<LastFmAlbumImage> getImages() {
        return lastFmAlbumImages;
    }

    public void setImages(List<LastFmAlbumImage> lastFmAlbumImages) {
        this.lastFmAlbumImages = lastFmAlbumImages;
    }
}