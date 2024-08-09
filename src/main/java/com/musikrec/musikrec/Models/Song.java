package com.musikrec.musikrec.Models;

import com.musikrec.musikrec.User.AppUser;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
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
    private Integer id;

    private String title;

    private String genre;

    private String releaseYear;

    @ManyToOne
    @JoinColumn(name = "artist_id")
    private Artist artist;

    @ManyToOne
    @JoinColumn(name = "album_id")
    private Album album;

    @OneToOne(mappedBy = "song")
    private Like like;

    private String imageUrl;

    private String previewUrl;

    @ManyToMany(mappedBy = "userSongs")
    private List<AppUser> users = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "song_playlist",
            joinColumns = @JoinColumn(name = "song_id"),
            inverseJoinColumns = @JoinColumn(name = "playlist_id"))
    private List<Playlist> songPlaylists = new ArrayList<>();

}





