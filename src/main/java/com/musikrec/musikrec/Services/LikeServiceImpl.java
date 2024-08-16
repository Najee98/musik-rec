package com.musikrec.musikrec.Services;


import com.musikrec.musikrec.Dto.Responses.SongResponseDto;
import com.musikrec.musikrec.Exceptions.CustomExceptions.ResourceNotFoundException;
import com.musikrec.musikrec.Models.Like;
import com.musikrec.musikrec.Models.Song;
import com.musikrec.musikrec.Repositories.LikeRepository;
import com.musikrec.musikrec.Repositories.SongRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LikeServiceImpl implements LikeService {

    private final LikeRepository likeRepository;

    private final SongRepository songRepository;

    private final UserService userService;

    @Override
    public List<SongResponseDto> getAllLikeForUser() {

        Integer userId = userService.getUserFromLogin().getId();

        return likeRepository.getAllLikeForUser(userId);
    }

    @Override
    public void insertLike(Integer songId) {

        Integer userId = userService.getUserFromLogin().getId();

        Song song = songRepository.findById(songId)
                .orElseThrow(()->new ResourceNotFoundException("song not found"));


        Like like = new Like();

        like.setSong(song);
        like.setAppUser(userService.getUserById(userId));
        like.setTimestamp(new Timestamp(System.currentTimeMillis()));

        likeRepository.save(like);

    }

    @Override
    public void DeleteLike(Integer id) {
        Optional<Like> likeOptional = likeRepository.findById(id);

        if (likeOptional.isEmpty()) {
            throw new ResourceNotFoundException("Like not found!");
        } else {
            Like like = likeOptional.get();

            // Remove the association with the Song before deleting the Like
            if (like.getSong() != null) {
                like.setSong(null);
            }

            likeRepository.deleteById(id);
        }
    }


}

