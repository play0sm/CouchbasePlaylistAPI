package com.kodluyoruz.playlist.domain;

import com.kodluyoruz.playlist.exception.NotFoundException;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Builder
public class Playlist {
    private String id;
    private String name;
    private String description;
    private int followersCount;
    private List<Track> tracks;
    private int trackCount;
    private String userId;

    public Playlist() {
        this.id = UUID.randomUUID().toString();
        this.trackCount = 0;
        this.tracks = new ArrayList<>();
        this.followersCount = 0;
    }

    public Playlist(String name, String description, int followersCount, List<Track> tracks, int trackCount, String userId) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.description = description;
        this.followersCount = followersCount;
        this.tracks = tracks;
        this.trackCount = trackCount;
        this.userId = userId;
    }

    public Playlist(String id, String name, String description, int followersCount, List<Track> tracks, int trackCount, String userId) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.followersCount = followersCount;
        this.tracks = tracks;
        this.trackCount = trackCount;
        this.userId = userId;
    }

    public void addTrack(Track track) {
        this.tracks.add(track);
        setTrackCount(this.tracks.size());
    }

    public void deleteTrack(String id) {
        Track result = null;
        for (Track track : this.tracks) {
            if (track.getId().equals(id)) {
                result = track;
                break;
            }
        }
        if (result == null) {
            throw new NotFoundException("Track not found");
        }
        tracks.remove(result);
        setTrackCount(this.tracks.size());
    }
}
