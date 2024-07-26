package com.musikrec.musikrec.Integration.SpotifyAPI.ApiResponse.Utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Track {
    private Album album;
    private List<Artist> artists;
    private List<String> available_markets;
    private ExternalUrls external_urls;
    private String href;
    private String id;
    private boolean is_local;
    private String name;
    private int disc_number;
    private int duration_ms;
    private boolean explicit;
    private ExternalIds external_ids;
    private int popularity;
    private String preview_url;
    private int track_number;
    private String type;
    private String uri;

}
