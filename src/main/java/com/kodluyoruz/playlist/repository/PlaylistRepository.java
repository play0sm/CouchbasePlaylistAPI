package com.kodluyoruz.playlist.repository;

import com.kodluyoruz.playlist.domain.Playlist;

import java.util.List;

public interface PlaylistRepository {

    public String create(Playlist playlist);

    public void update(Playlist playList);

    public Playlist findById(String id);

    public List<Playlist> findAllByUserId(String id);

    public void delete(String id);
}
