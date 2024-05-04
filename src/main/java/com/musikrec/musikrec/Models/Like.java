package com.musikrec.musikrec.Models;

import jakarta.persistence.*;

@Entity
@Table(name = "likes")
public class Like {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long likeId;

    Long userId;
    String comment;

}