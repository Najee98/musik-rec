package com.musikrec.musikrec.Integration.LastFmAPI;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LastFmConfig {

    @Value("${lastfm.api.key}")
    private String apiKey;

    @Value("${lastfm.api.url}")
    private String apiUrl;

    public String getApiKey() {
        return apiKey;
    }

    public String getApiUrl() {
        return apiUrl;
    }
}
