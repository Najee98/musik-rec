package com.musikrec.musikrec.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.musikrec.musikrec.User.AppUser;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "playlists")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Playlist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "playlist_id")
    private Integer id;

    private String description;

    private String name;

    @ManyToMany(mappedBy = "songPlaylists", cascade = CascadeType.ALL)
    private List<Song> songs = new ArrayList<>();

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "user_id")
    private AppUser appUser;

    public void addSong(Song song) {
        this.songs.add(song);
        song.getSongPlaylists().add(this);
    }

    public void removeSong(Song song) {
        this.songs.remove(song);
        song.getSongPlaylists().remove(this);
    }

}
