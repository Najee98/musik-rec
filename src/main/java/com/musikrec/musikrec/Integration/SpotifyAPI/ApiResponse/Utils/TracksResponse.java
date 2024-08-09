package com.musikrec.musikrec.Integration.SpotifyAPI.ApiResponse.Utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TracksResponse {
    private String href;
    private List<Track> items;
    private int limit;
    private String next;
    private int offset;
    private String previous;
    private int total;

}
