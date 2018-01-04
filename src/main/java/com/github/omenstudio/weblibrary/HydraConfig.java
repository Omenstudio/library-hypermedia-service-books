package com.github.omenstudio.weblibrary;


import com.github.omenstudio.hydra.utils.HydraUrlResolver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;


@Configuration
@EnableAspectJAutoProxy
@ComponentScan("com.github.omenstudio.hydra.*")
public class HydraConfig {

    @Bean
    public CommandLineRunner registerApplicationPaths(
            @Value("${hydra.address}")  String url,
            @Value("${hydra.api}")  String entrypoint,
            @Value("${hydra.vocab}")  String vocab,
            @Value("${hydra.contexts}")  String context
    ) {
        return e -> {
            HydraUrlResolver.setServerAddress(url);
            HydraUrlResolver.setApiAddress(entrypoint);
            HydraUrlResolver.setVocabAddress(vocab);
            HydraUrlResolver.setContextsAddress(context);
        };
    }

}

