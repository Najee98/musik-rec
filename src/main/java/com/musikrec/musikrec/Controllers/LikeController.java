package com.musikrec.musikrec.Controllers;

import com.musikrec.musikrec.Dto.Responses.LikeResponseDto;
import com.musikrec.musikrec.Dto.Responses.SongResponseDto;
import com.musikrec.musikrec.Models.Like;
import com.musikrec.musikrec.Services.LikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/likes")

public class LikeController {


    private final LikeService likeService;

    @GetMapping()
    public ResponseEntity<List<LikeResponseDto>> getAllLikeForUser(@RequestParam Long userId) {
        return ResponseEntity.ok(likeService.getAllLikeForUser(userId));
    }


    @GetMapping("/get/{id}")
    public ResponseEntity<Like> getLike(@PathVariable Long id) {
        return ResponseEntity.ok(likeService.getLike(id));
    }


    @PostMapping("/add")
    public void insertLike(@RequestBody Like like) {
        likeService.insertLike(like);
    }


    @DeleteMapping("/delete")
    public void deleteLike(@RequestParam Long id) {
        likeService.deleteLike(id);
    }
}
