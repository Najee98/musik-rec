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
    Long id;

    Long userId;
    String songId;
    Timestamp recommendationTimestamp;

//    @ManyToMany(mappedBy = "likedRecommendations")
//    Set<Song> likes;
}
