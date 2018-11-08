package com.example.xxx.bakeme;

public class Steps {

    private String shortDescription;
    private String description;
    private String videoUrl;

    public Steps(String sStrong, String sDesc, String sVideo) {

        shortDescription = sStrong;
        description = sDesc;
        videoUrl = sVideo;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public String getDescription() {
        return description;
    }

    public String getVideoUrl() {
        return videoUrl;
    }
}
