package com.SpaceNews.articlesfeed;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AppConfig {

    @Bean
    public RestTemplate restTemplate() {
        SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
        factory.setConnectTimeout(5000); // délai d'attente de connexion en ms
        factory.setReadTimeout(5000); // délai d'attente de lecture en ms
        return new RestTemplate(factory);
    }
}
