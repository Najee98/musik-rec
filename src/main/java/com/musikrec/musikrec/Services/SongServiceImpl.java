package com.musikrec.musikrec.Services;

import com.musikrec.musikrec.Models.Song;
import com.musikrec.musikrec.Repositories.SongRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SongServiceImpl implements SongService {

    private final SongRepository songRepository;

    @Override
    public Song getSongByTitle(String title)
    {
        return songRepository.findByTitle(title);
    }
}
