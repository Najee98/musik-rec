package com.musikrec.musikrec;


import com.musikrec.musikrec.Integration.LastFmAPI.LastFmService;
import com.musikrec.musikrec.Integration.SpotifyAPI.SpotifyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.Query;

@SpringBootApplication
public class MusikRecApplication implements CommandLineRunner{

	@Autowired
	SpotifyService spotifyService;

    public static void main(String[] args) {
		SpringApplication.run(MusikRecApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		spotifyService.fetchAndStoreSongs(150);
	}
}
