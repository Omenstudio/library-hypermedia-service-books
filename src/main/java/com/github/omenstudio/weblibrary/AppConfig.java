package com.github.omenstudio.weblibrary;


import com.github.omenstudio.hydraback.utils.HydraUrlResolver;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("com.github.omenstudio.hydraback.*")
public class AppConfig {

    static {
        HydraUrlResolver.setApplicationAddress("https://library-service-books.herokuapp.com");
        HydraUrlResolver.setApiPath("/api");
        HydraUrlResolver.setVocabPath("/api/vocab");
    }

}
