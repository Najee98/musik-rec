package com.musikrec.musikrec.Dto.Responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AlbumDto implements Serializable {

    private Integer id;
    private String name;
    private String imageUrl;

}
