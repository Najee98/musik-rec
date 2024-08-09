package com.musikrec.musikrec.Integration.SpotifyAPI.ApiResponse.Utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Tracks {
    private List<Item> items;
}