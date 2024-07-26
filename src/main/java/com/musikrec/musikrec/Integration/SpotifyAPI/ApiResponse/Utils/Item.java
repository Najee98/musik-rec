package com.musikrec.musikrec.Integration.SpotifyAPI.ApiResponse.Utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Item {
    private String id;
    private String name;
    private List<Artist> artists;
    private Album album;
    private ExternalUrls external_urls;
    private String preview_url;

}