package com.musikrec.musikrec.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@Table(name = "playlists")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Playlist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "playlist_id")
    private Long playlist_id;

    private String playlist_description;

    private String playlist_name;

    private String description;



    //The Relationship Between songs And playlists://
    @ManyToMany(mappedBy = "songPlaylists")
    private Set<Song> songs ;
}
