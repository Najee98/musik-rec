package com.musikrec.musikrec.Models;

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
    Long id;

    String title;
    String artist;
    String year;

//    @OneToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "likeId",referencedColumnName = "songId")
//    Like like;

//    @OneToOne(mappedBy = "song")
//    Like like;
    //@ManyToMany
//@JoinTable(
//            //name = "recommendedSongId",
//            joinColumns = @JoinColumn(name = "songId"),
//            inverseJoinColumns = @JoinColumn(name = "RecommendationId"))
//    Set<Recommendation> likedRecommendations;
}
