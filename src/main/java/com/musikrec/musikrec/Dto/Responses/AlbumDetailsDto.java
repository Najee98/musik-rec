package com.musikrec.musikrec.Dto.Responses;

import com.musikrec.musikrec.Models.Song;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AlbumDetailsDto implements Serializable {

    private Long id;
    private String name;
    private String imageUrl;

    private List<SongResponseDto> songs;
}
