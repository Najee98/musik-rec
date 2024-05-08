package com.musikrec.musikrec.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.sql.Timestamp;
import java.util.Set;

@Entity
@Table(name = "recommendations")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Recommendation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "recommendation_id")
    private Long recommendation_id;

    private Long user_id;

    private String recommendation_song_id;

    private String song_id;

    private Timestamp recommendation_timestamp;


    //The Relationship Between songs And recommendations://
    @ManyToMany(mappedBy = "songRecommendations")
    private Set<Song> likes;

}
