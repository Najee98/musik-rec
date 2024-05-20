package com.musikrec.musikrec.Models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.musikrec.musikrec.User.AppUser;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "songs")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Song {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "song_id")
    private Long id;

    private String title;

    private String album;

    private String artist;

    private String genre;

    private String releaseYear;

    @OneToOne(mappedBy = "song")
    private Like like;

    @ManyToMany(mappedBy = "userSongs")
    private List<AppUser> users ;

    @ManyToMany
    @JoinTable(
            name = "song_playlist",
            joinColumns = @JoinColumn(name = "song_id"),
            inverseJoinColumns = @JoinColumn(name = "playlist_id"))
    @JsonManagedReference
    private List<Playlist> songPlaylists;

}





