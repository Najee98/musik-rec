package com.musikrec.musikrec;


import com.musikrec.musikrec.Integration.LastFmService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@RequiredArgsConstructor
public class MusikRecApplication implements CommandLineRunner{

	private final LastFmService lastFmService;

    public static void main(String[] args) {
		SpringApplication.run(MusikRecApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		lastFmService.fetchAndSaveSongs();
	}
}
