package com.musikrec.musikrec.Models;

import com.musikrec.musikrec.User.AppUser;
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
    private Long id;

    private String description;

    private String name;


    //The Relationship Between songs And playlists://
    @ManyToMany(mappedBy = "songPlaylists")
    private Set<Song> songs ;


    //The Relationship Between users And playlist://
    @ManyToOne
    @JoinColumn(name = "user_id")
    private AppUser appUser;


}