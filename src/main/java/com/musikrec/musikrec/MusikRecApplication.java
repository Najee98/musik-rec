package com.musikrec.musikrec;

import com.musikrec.musikrec.Integration.LastFmService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MusikRecApplication implements CommandLineRunner {

	private final LastFmService lastFmService;

	public MusikRecApplication(LastFmService lastFmService){
		this.lastFmService = lastFmService;
	}

	public static void main(String[] args) {
		SpringApplication.run(MusikRecApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		lastFmService.fetchAndSaveSongs();
	}
}
