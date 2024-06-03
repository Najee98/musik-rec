package com.musikrec.musikrec.Dto.Requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlaylistUpdateRequest implements Serializable {

    private String name;
    private String description;

}
