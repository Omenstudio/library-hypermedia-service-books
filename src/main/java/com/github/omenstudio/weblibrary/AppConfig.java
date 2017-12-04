package com.github.omenstudio.weblibrary;


import com.github.omenstudio.hydraback.HydraAppConfig;
import com.github.omenstudio.hydraback.aspect.HydraResponseBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("com.github.omenstudio.hydraback.*")
public class AppConfig {

    @Autowired
    HydraResponseBuilder hydraResponseBuilder;

    static {
        HydraAppConfig.setApplicationHttpAddress("http://localhost:8080");
    }

    public static String getApplicationHttpAddress() {
        return HydraAppConfig.getApplicationHttpAddress();
    }
}
