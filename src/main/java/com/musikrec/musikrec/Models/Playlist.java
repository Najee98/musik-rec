package com.musikrec.musikrec.Models;

import com.musikrec.musikrec.User.AppUser;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
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
    private List<Song> songs = new ArrayList<>();


    //The Relationship Between users And playlist://
    @ManyToOne
    @JoinColumn(name = "user_id")
    private AppUser appUser;

    public void addSong(Song song) {
        this.songs.add(song);
        song.getSongPlaylists().add(this);
    }
}
