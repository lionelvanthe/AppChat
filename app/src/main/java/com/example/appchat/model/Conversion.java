package com.example.appchat.model;


public class Conversion {

    private String name;
    private String lastMessage;
    private String timestamp;
    private String urlAvatar;
    private String conversionId;
    private boolean isOnline;
    private boolean isSeen;

    public Conversion(String conversionId, String name, String lastMessage, String timestamp, String urlAvatar, boolean isOnline, boolean isSeen) {
        this.conversionId = conversionId;
        this.name = name;
        this.lastMessage = lastMessage;
        this.timestamp = timestamp;
        this.urlAvatar = urlAvatar;
        this.isOnline = isOnline;
        this.isSeen = isSeen;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastMessage() {
        return lastMessage;
    }

    public void setLastMessage(String lastMessage) {
        this.lastMessage = lastMessage;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getUrlAvatar() {
        return urlAvatar;
    }

    public void setUrlAvatar(String urlAvatar) {
        this.urlAvatar = urlAvatar;
    }

    public String getConversionId() {
        return conversionId;
    }

    public void setConversionId(String conversionId) {
        this.conversionId = conversionId;
    }

    public boolean isOnline() {
        return isOnline;
    }

    public void setOnline(boolean online) {
        isOnline = online;
    }

    public boolean isSeen() {
        return isSeen;
    }

    public void setSeen(boolean seen) {
        isSeen = seen;
    }
}
