package com.musikrec.musikrec.Dto.Responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LikeResponseDto implements Serializable {

    private Integer id;

    private String title;

    private String artist;

    private Timestamp timestamp;


}
