package com.musikrec.musikrec.Controllers;

import com.musikrec.musikrec.Dto.Responses.LikeResponseDto;
import com.musikrec.musikrec.Services.LikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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


    @PostMapping("/add")
    public ResponseEntity<Object> insertLike(
            @RequestParam Long songId,
            @RequestParam Long userId){
        likeService.insertLike(songId, userId);

        return new ResponseEntity<>("{ \"message\": \" Added like to song successfully  \" }" , HttpStatus.OK);

    }


    @DeleteMapping("/delete")
    public ResponseEntity<Object> deleteLike(@RequestParam Long id) {
        likeService.deleteLike(id);

        return new ResponseEntity<>("{\"message\": \" Deleted like from the song successfully  \" }", HttpStatus.OK);

    }
}
