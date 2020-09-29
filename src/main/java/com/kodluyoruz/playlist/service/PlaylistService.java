package com.kodluyoruz.playlist.service;

import com.kodluyoruz.playlist.domain.Playlist;
import com.kodluyoruz.playlist.repository.PlaylistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlaylistService {

    private final PlaylistRepository playlistRepository;

    @Autowired
    public PlaylistService(PlaylistRepository playlistRepository) {
        this.playlistRepository = playlistRepository;
    }

    public String createPlaylist(Playlist playlist) {
        return playlistRepository.create(playlist);
    }

    public List<Playlist> findAllByUserId(String userId) {
        return playlistRepository.findAllByUserId(userId);
    }

    public Playlist findById(String id) {
        return playlistRepository.findById(id);
    }

    public void delete(String id) {
        playlistRepository.delete(id);
    }
}
