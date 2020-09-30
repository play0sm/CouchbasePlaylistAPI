package com.kodluyoruz.playlist.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Builder
public class Track {
    private String id;
    private String name;
    private String length;
    private String artist;

    public Track() {
        this.id = UUID.randomUUID().toString();
    }

    public Track(String name, String length, String artist) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.length = length;
        this.artist = artist;
    }

    public Track(String id, String name, String length, String artist) {
        this.id = id;
        this.name = name;
        this.length = length;
        this.artist = artist;
    }
}
