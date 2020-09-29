package com.kodluyoruz.playlist.controller;

import com.kodluyoruz.playlist.domain.Playlist;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/playlists/{id}/tracks")
public class TrackController {

    @PostMapping
    public ResponseEntity<Void> addTrackToPlaylist(@PathVariable String id,@RequestBody Playlist playlist) {
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{trackId}")
    public ResponseEntity<Void> deleteTrackFromPlaylist(@PathVariable String id, @PathVariable String trackId) {
        return ResponseEntity.ok().build();
    }
}
