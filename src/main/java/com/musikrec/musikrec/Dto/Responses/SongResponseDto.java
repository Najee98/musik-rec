package com.musikrec.musikrec.Dto.Responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SongResponseDto implements Serializable {

    private Integer id;
    private String name;
    private String artist;
    private String album;

}
