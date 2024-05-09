package com.musikrec.musikrec.Services;

import com.musikrec.musikrec.Models.Like;
import com.musikrec.musikrec.Repositories.LikeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LikeServiceImpl implements LikeService {

    private final LikeRepository likeRepository;

}
