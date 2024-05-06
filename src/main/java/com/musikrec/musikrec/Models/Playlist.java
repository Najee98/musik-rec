package com.musikrec.musikrec.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "playlists")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Playlist {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    long playlistId;


    String playlistName;
    String playlistDescription;
}
