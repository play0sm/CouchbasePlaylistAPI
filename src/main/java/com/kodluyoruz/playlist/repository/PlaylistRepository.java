package com.kodluyoruz.playlist.repository;

import com.kodluyoruz.playlist.domain.Playlist;
import com.kodluyoruz.playlist.domain.Track;

import java.util.List;

public interface PlaylistRepository {

    public String create(Playlist playlist);

    public void update(Playlist playList);

    public Playlist findById(String id);

    public List<Playlist> findAllByUserId(String id);

    public void delete(String id);

    public String addTrack(String playlistId, Track track);

    public List<Track> getTracks(String playlistId);

    public void deleteTrack(String playlistId,String trackId);
}
