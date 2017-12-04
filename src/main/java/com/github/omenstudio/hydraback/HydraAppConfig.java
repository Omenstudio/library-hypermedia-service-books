package com.github.omenstudio.hydraback;

public class HydraAppConfig {
    private static String appAddress = "http://localhost:8080";


    public static String getApplicationHttpAddress() {
        return appAddress;
    }

    public static void setApplicationHttpAddress(String newAppAddress) {
        appAddress = newAppAddress;
    }

}
