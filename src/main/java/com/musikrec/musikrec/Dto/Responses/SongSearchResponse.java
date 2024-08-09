package com.musikrec.musikrec.Dto.Responses;

import lombok.Data;

@Data
public class SongSearchResponse {

    private Integer id;
    private String title;
    private String artist;
    private String album;
    private String imageUrl;
    private String previewUrl;

}
