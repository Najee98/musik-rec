package com.musikrec.musikrec.Integration.LastFmAPI;

import com.musikrec.musikrec.Exceptions.CustomExceptions.ResourceNotFoundException;
import com.musikrec.musikrec.Integration.LastFmAPI.ApiResponse.LastFmResponse;
import com.musikrec.musikrec.Integration.LastFmAPI.ApiResponse.LastFmTrackResponse;
import com.musikrec.musikrec.Integration.LastFmAPI.ApiResponse.LastFmTrack;
import com.musikrec.musikrec.Models.Album;
import com.musikrec.musikrec.Models.Artist;
import com.musikrec.musikrec.Models.Song;
import com.musikrec.musikrec.Repositories.AlbumRepository;
import com.musikrec.musikrec.Repositories.SongRepository;
import com.musikrec.musikrec.Services.ArtistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class LastFmService {

    private final SongRepository songRepository;
    private final AlbumRepository albumRepository;
    private final LastFmConfig lastFmConfig;
    private final ArtistService artistService;

    @Autowired
    public LastFmService(SongRepository songRepository, AlbumRepository albumRepository, LastFmConfig lastFmConfig, ArtistService artistService) {
        this.songRepository = songRepository;
        this.albumRepository = albumRepository;
        this.lastFmConfig = lastFmConfig;
        this.artistService = artistService;
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
                try{
                    Artist artist = artistService.getArtistByName(lastFmTrack.getArtist().getName());
                    song.setArtist(artist);
                }catch (ResourceNotFoundException e){
                    e.getMessage();
                }finally {
                    Artist artist = new Artist();
                    artist.setName("unknown artist");
                    song.setArtist(artist);
                }


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
            album.setArtist(artistService.getArtistByName(lastFmTrack.getArtist().getName()));
            album.setImageUrl(lastFmTrack.getAlbum().getImages().get(0).getText());

            return albumRepository.save(album);
        }
        return null;
    }
}
