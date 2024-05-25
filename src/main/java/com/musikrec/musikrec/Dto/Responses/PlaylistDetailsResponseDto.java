package com.musikrec.musikrec.Dto.Responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class PlaylistDetailsResponseDto implements Serializable {

    private Long id;
    private String name;
    private String description;


    private List<SongResponseDto> songs;
}
