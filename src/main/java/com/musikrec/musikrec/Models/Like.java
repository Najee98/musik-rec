package com.musikrec.musikrec.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Entity
@Table(name = "likes")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Like {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "like_id")
    Long id;

    Long userId;
    Long songId;
    Timestamp likeTimestamp;

//    @OneToOne(mappedBy = "like")
//    Song song;

//    @OneToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "songId",referencedColumnName = "songId")
//    Song song;

}