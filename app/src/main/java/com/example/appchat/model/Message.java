package com.example.appchat.model;


import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class Message {

    private String message;
    private String timestamp;
    private String receivedId;
    private boolean isSeen;

    public Message(String message, String timestamp, String receivedId) {
        this.message = message;
        this.timestamp = timestamp;
        this.receivedId = receivedId;
        isSeen = false;
    }

    public Message(){

    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getReceivedId() {
        return receivedId;
    }

    public void setReceivedId(String receivedId) {
        this.receivedId = receivedId;
    }

    public boolean isSeen() {
        return isSeen;
    }

    public void setSeen(boolean seen) {
        isSeen = seen;
    }
}
