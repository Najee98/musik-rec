package com.musikrec.musikrec;


import com.musikrec.musikrec.Integration.LastFmAPI.LastFmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MusikRecApplication {

    public static void main(String[] args) {
		SpringApplication.run(MusikRecApplication.class, args);
	}

}
