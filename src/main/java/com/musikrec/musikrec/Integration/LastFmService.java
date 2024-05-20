package com.musikrec.musikrec.Integration;

import com.musikrec.musikrec.Integration.ApiResponse.LastFmResponse;
import com.musikrec.musikrec.Integration.ApiResponse.Track;
import com.musikrec.musikrec.Models.Song;
import com.musikrec.musikrec.Repositories.SongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.List;

@Service
public class LastFmService {

    private final SongRepository songRepository;

    private final LastFmConfig lastFmConfig;

    @Autowired
    public LastFmService(SongRepository songRepository, LastFmConfig lastFmConfig) {
        this.songRepository = songRepository;
        this.lastFmConfig = lastFmConfig;
    }

    public void fetchAndSaveSongs() {
        String url = lastFmConfig.getApiUrl() + "?method=chart.gettoptracks&api_key=" + lastFmConfig.getApiKey() + "&format=json";
        RestTemplate restTemplate = new RestTemplate();
        LastFmResponse response = restTemplate.getForObject(url, LastFmResponse.class);

        if (response != null && response.getTracks() != null) {
            List<Track> tracks = response.getTracks().getTrack();
            int count = 0;
            for (Track track : tracks) {
                if (count >= 50) {
                    break;
                }
                Song song = new Song();
                song.setTitle(track.getName());
                song.setArtist(track.getArtist().getName());
                song.setAlbum("test album");

                songRepository.save(song);
                count++;
            }
        }
    }
}


