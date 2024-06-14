package com.musikrec.musikrec.Models;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "albums")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Album {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    String title;

    @ManyToOne
    @JoinColumn(name = "artist_id")
    Artist artist;

    String imageUrl;

    @OneToMany(mappedBy = "album", cascade = CascadeType.ALL, orphanRemoval = true)
    List<Song> songs = new ArrayList<>();


}
