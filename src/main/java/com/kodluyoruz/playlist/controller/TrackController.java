package com.kodluyoruz.playlist.controller;

import com.kodluyoruz.playlist.domain.Track;
import com.kodluyoruz.playlist.service.TrackService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/playlists/{id}/tracks")
public class TrackController {

    TrackService trackService;

    public TrackController(TrackService trackService) {
        this.trackService = trackService;
    }

    @PostMapping
    public ResponseEntity<Void> addTrackToPlaylist(@PathVariable String id, @RequestBody Track track) {
        String trackId = trackService.addTrackToPlaylist(id, track);
        URI uri = URI.create(String.format("/playlists/%s/tracks/%s", id, trackId));
        return ResponseEntity.created(uri).build();
    }

    @DeleteMapping("/{trackId}")
    public ResponseEntity<Void> deleteTrackFromPlaylist(@PathVariable String id, @PathVariable String trackId) {
        trackService.deleteTrackFromPlaylist(id,trackId);
        return ResponseEntity.noContent().build();
    }
}
