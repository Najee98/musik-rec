package com.musikrec.musikrec.Integration;

import com.musikrec.musikrec.Integration.ApiResponse.LastFmResponse;
import com.musikrec.musikrec.Integration.ApiResponse.LastFmTrackResponse;
import com.musikrec.musikrec.Integration.ApiResponse.LastFmTrack;
import com.musikrec.musikrec.Models.Album;
import com.musikrec.musikrec.Models.Song;
import com.musikrec.musikrec.Repositories.AlbumRepository;
import com.musikrec.musikrec.Repositories.SongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class LastFmService {

    private final SongRepository songRepository;
    private final AlbumRepository albumRepository;
    private final LastFmConfig lastFmConfig;

    @Autowired
    public LastFmService(SongRepository songRepository, AlbumRepository albumRepository, LastFmConfig lastFmConfig) {
        this.songRepository = songRepository;
        this.albumRepository = albumRepository;
        this.lastFmConfig = lastFmConfig;
    }

    public void fetchAndSaveSongs() {
        String url = lastFmConfig.getApiUrl() + "?method=chart.gettoptracks&api_key=" + lastFmConfig.getApiKey() + "&format=json";
        RestTemplate restTemplate = new RestTemplate();
        LastFmResponse response = restTemplate.getForObject(url, LastFmResponse.class);

        if (response != null && response.getTracks() != null) {
            List<LastFmTrack> lastFmTracks = response.getTracks().getTrack();
            for (LastFmTrack lastFmTrack : lastFmTracks) {
                Song song = new Song();
                song.setTitle(lastFmTrack.getName());
                song.setArtist(lastFmTrack.getArtist().getName());

//                // Fetch album details
//                Album album = fetchAlbumDetails(lastFmTrack.getArtist().getName(), lastFmTrack.getName());
//                if (album != null) {
//                    song.setAlbum(album);
//                }

                songRepository.save(song);
            }
        }
    }

    private Album fetchAlbumDetails(String artistName, String trackName) {
        String url = lastFmConfig.getApiUrl() + "?method=track.getInfo&api_key=" + lastFmConfig.getApiKey() + "&artist=" + artistName + "&track=" + trackName + "&format=json";
        RestTemplate restTemplate = new RestTemplate();
        LastFmTrackResponse response = restTemplate.getForObject(url, LastFmTrackResponse.class);

        if (response != null && response.getTrack() != null) {
            LastFmTrack lastFmTrack = response.getTrack();
            Album album = new Album();
            album.setTitle(lastFmTrack.getAlbum().getTitle());
            album.setArtist(lastFmTrack.getArtist().getName());
            album.setImageUrl(lastFmTrack.getAlbum().getImages().get(0).getText());

            return albumRepository.save(album);
        }
        return null;
    }
}
