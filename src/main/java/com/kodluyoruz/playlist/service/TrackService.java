package com.kodluyoruz.playlist.service;

import com.kodluyoruz.playlist.domain.Playlist;
import com.kodluyoruz.playlist.domain.Track;
import com.kodluyoruz.playlist.repository.PlaylistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TrackService {

    private final PlaylistRepository playlistRepository;

    @Autowired
    public TrackService(PlaylistRepository playlistRepository) {
        this.playlistRepository = playlistRepository;
    }

    public String addTrackToPlaylist(String playlistId, Track track) {
        Playlist playlist = playlistRepository.findById(playlistId);
        playlist.addTrack(track);
        playlistRepository.update(playlist);
        return track.getId();
    }

    public void deleteTrackFromPlaylist(String playlistId,String trackId){
        Playlist playlist = playlistRepository.findById(playlistId);
        playlist.deleteTrack(trackId);
        playlistRepository.update(playlist);
    }

}
