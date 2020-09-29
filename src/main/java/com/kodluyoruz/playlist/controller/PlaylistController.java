package com.kodluyoruz.playlist.controller;

import com.kodluyoruz.playlist.domain.Playlist;
import com.kodluyoruz.playlist.service.PlaylistService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/playlists")
public class PlaylistController {

    PlaylistService playlistService;

    public PlaylistController(PlaylistService playlistService) {
        this.playlistService = playlistService;
    }

    @PostMapping
    public ResponseEntity<Void> createPlaylist(@RequestBody Playlist playlist) {
        String id = playlistService.createPlaylist(playlist);
        URI uri = URI.create(String.format("/playlists/%s", id));
        return ResponseEntity.created(uri).build();
    }

    @GetMapping
    public ResponseEntity<List<Playlist>> findAllByUserId(@RequestParam String userId) {
        return ResponseEntity.ok(playlistService.findAllByUserId(userId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Playlist> findById(@PathVariable String id) {
        return ResponseEntity.ok(playlistService.findById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        playlistService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
