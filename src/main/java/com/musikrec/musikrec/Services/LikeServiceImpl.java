package com.musikrec.musikrec.Services;


import com.musikrec.musikrec.Dto.Responses.LikeResponseDto;
import com.musikrec.musikrec.Exceptions.CustomExceptions.ResourceNotFoundException;
import com.musikrec.musikrec.Models.Like;
import com.musikrec.musikrec.Repositories.LikeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LikeServiceImpl implements LikeService {

    private final LikeRepository likeRepository;

    @Override
    public List<LikeResponseDto> getAllLikeForUser(Long userId) {

        List<LikeResponseDto> response = likeRepository.getAllLikeForUser(userId);

                if (response.isEmpty())
                    throw new ResourceNotFoundException("no like for user");
                else
                    return response;

    }

    @Override
    public Like getLike(Long id) {
        return likeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Like not found"));
    }

    @Override
    public void insertLike(Like like) {
        Like newLike = new Like();
        newLike.setId(like.getId());

        likeRepository.save(like);

    }

    @Override
    public Long deleteLike(Long id) {
        Optional<Like> likeOptional = likeRepository.findById(id);

        if (likeOptional.isEmpty())
            throw new ResourceNotFoundException("Like not found!");
        else {
            likeRepository.deleteById(id);
            return id;
        }
    }

}

