package com.musikrec.musikrec.Models;

import com.musikrec.musikrec.User.AppUser;
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
    private Long id;

    private Timestamp timestamp;


    //The Relationship Between likes And songs://
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "song_id",referencedColumnName = "song_id")
    private Song song;


    //The Relationship Between users And likes://
    @ManyToOne
    @JoinColumn(name = "user_id")
    private AppUser appUser;

}