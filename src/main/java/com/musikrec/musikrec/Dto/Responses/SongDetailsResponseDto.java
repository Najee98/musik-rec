package com.musikrec.musikrec.Dto.Responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class SongDetailsResponseDto implements Serializable {

    private Integer id;

    private String title;

    private String artist;

    private String genre;

    private String releaseYear;

    private Integer albumId;

}
