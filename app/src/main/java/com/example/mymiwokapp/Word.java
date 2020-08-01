package com.example.mymiwokapp;

public class Word {
    private String English;
    private String Miwok;
    private int audioId;
    private int imageId;

    public Word(String english, String miwok, int audioId, int imageId) {
        English = english;
        Miwok = miwok;
        this.audioId = audioId;
        this.imageId = imageId;
    }

    public Word(String english, String miwok, int audioId) {
        English = english;
        Miwok = miwok;
        this.audioId = audioId;
    }

    public String getEnglish() {
        return English;
    }

    public void setEnglish(String english) {
        English = english;
    }

    public String getMiwok() {
        return Miwok;
    }

    public void setMiwok(String miwok) {
        Miwok = miwok;
    }

    public int getAudioId() {
        return audioId;
    }

    public void setAudioId(int audioId) {
        this.audioId = audioId;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }
}
