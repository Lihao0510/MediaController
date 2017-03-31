package com.oridway.videopush.model;

/**
 * Created by lihao on 2017/3/31.
 */

public class RTMPSource {

    private int id;
    private String videoCode;
    private String sourceUrl;
    private String videoName;
    private String videoOwner;
    private String startTime;
    private String videoLocation;

    public RTMPSource(String sourceUrl, String videoName) {
        this.sourceUrl = sourceUrl;
        this.videoName = videoName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getVideoCode() {
        return videoCode;
    }

    public void setVideoCode(String videoCode) {
        this.videoCode = videoCode;
    }

    public String getSourceUrl() {
        return sourceUrl;
    }

    public void setSourceUrl(String sourceUrl) {
        this.sourceUrl = sourceUrl;
    }

    public String getVideoName() {
        return videoName;
    }

    public void setVideoName(String videoName) {
        this.videoName = videoName;
    }

    public String getVideoOwner() {
        return videoOwner;
    }

    public void setVideoOwner(String videoOwner) {
        this.videoOwner = videoOwner;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getVideoLocation() {
        return videoLocation;
    }

    public void setVideoLocation(String videoLocation) {
        this.videoLocation = videoLocation;
    }
}
