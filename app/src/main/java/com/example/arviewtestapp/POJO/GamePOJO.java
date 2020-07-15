package com.example.arviewtestapp.POJO;

public class GamePOJO
{
    private int viewers, channels;
    private String gameName, imageUrl;

    public int getViewers() {
        return viewers;
    }

    public void setViewers(int viewers) {
        this.viewers = viewers;
    }

    public int getChannels() {
        return channels;
    }

    public void setChannels(int channels) {
        this.channels = channels;
    }

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public GamePOJO(int viewers, int channels, String gameName, String imageUrl) {
        this.viewers = viewers;
        this.channels = channels;
        this.gameName = gameName;
        this.imageUrl = imageUrl;
    }
}
