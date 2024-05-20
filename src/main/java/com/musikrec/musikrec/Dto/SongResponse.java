package com.musikrec.musikrec.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SongResponse implements Serializable {

    private Long songId;
    private String songName;
    private String songArtist;
    private String songAlbum;


}
