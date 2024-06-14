package com.musikrec.musikrec.Services;


import com.musikrec.musikrec.Dto.Responses.LikeResponseDto;
import com.musikrec.musikrec.Exceptions.CustomExceptions.ResourceNotFoundException;
import com.musikrec.musikrec.Models.Like;
import com.musikrec.musikrec.Models.Song;
import com.musikrec.musikrec.Repositories.LikeRepository;
import com.musikrec.musikrec.Repositories.SongRepository;
import com.musikrec.musikrec.User.AppUserRepository;
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
    public List<LikeResponseDto> getAllLikeForUser(Integer userId) {

        List<LikeResponseDto> response = likeRepository.getAllLikeForUser(userId);

                if (response.isEmpty())
                    throw new ResourceNotFoundException("no like for user");
                else
                    return response;

    }

    @Override
    public void insertLike(Integer songId,Integer userId) {

        Song song = songRepository.findById(songId)
                .orElseThrow(()->new ResourceNotFoundException("song not found"));


        Like like = new Like();

        like.setSong(song);
        like.setAppUser(userService.getUserById(userId));
        like.setTimestamp(new Timestamp(System.currentTimeMillis()));

        likeRepository.save(like);

    }

    @Override
    public Integer deleteLike(Integer id) {
        Optional<Like> likeOptional = likeRepository.findById(id);

        if (likeOptional.isEmpty())
            throw new ResourceNotFoundException("Like not found!");
        else {
            likeRepository.deleteById(id);
            return id;
        }
    }

}

