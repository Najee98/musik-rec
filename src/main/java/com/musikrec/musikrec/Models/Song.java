package com.musikrec.musikrec.Models;

import com.musikrec.musikrec.User.AppUser;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@Table(name = "songs")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Song {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "song_id")
    private Long song_id;

    private String song_album;

    private String song_artist;

    private String song_genre;

    private String song_name;

    private String song_year;

    private String artist_name;

    private String title;

    private String year;



    //The Relationship Between songs And likes://
    @OneToOne(mappedBy = "song")
    private Like like;


    //The Relationship Between songs And users://
    @ManyToMany(mappedBy = "userSongs")
    private Set<AppUser> users ;



    //The Relationship Between songs And recommendations://
    @ManyToMany
    @JoinTable(
            name = "recommended_song_id",
            joinColumns = @JoinColumn(name = "song_id"),
            inverseJoinColumns = @JoinColumn(name = "Recommendation_id"))
    private Set<Recommendation> songRecommendations;



    //The Relationship Between songs And playlists://
    @ManyToMany
    @JoinTable(
            name = "song_playlist_id",
            joinColumns = @JoinColumn(name = "song_id"),
            inverseJoinColumns = @JoinColumn(name = "playlist_id"))
    private Set<Playlist> songPlaylists;

}




