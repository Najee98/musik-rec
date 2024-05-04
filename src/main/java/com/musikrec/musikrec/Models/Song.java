package com.musikrec.musikrec.Models;

import jakarta.persistence.*;

@Entity
@Table(name = "songs")
public class Song {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long songId;

    String songName;
    String songArtist;
    String songAlbum;
    String songGenre;
    String songYear;

}
