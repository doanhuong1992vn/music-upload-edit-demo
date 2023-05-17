package com.music_upload.model;

import org.springframework.web.multipart.MultipartFile;

public class MusicForm {
    private long id;
    private String name;
    private String singer;
    private String genre;
    private MultipartFile url;

    public MusicForm() {
    }

    public MusicForm(long id, String name, String singer, String genre, MultipartFile url) {
        this.id = id;
        this.name = name;
        this.singer = singer;
        this.genre = genre;
        this.url = url;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSinger() {
        return singer;
    }

    public void setSinger(String singer) {
        this.singer = singer;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public MultipartFile getUrl() {
        return url;
    }

    public void setUrl(MultipartFile url) {
        this.url = url;
    }
}
