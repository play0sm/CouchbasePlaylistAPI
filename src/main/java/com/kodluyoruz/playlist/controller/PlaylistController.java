package com.kodluyoruz.playlist.controller;

import com.kodluyoruz.playlist.domain.Playlist;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/playlists")
public class PlaylistController {

    @PostMapping
    public ResponseEntity<Void> createPlaylist(@RequestBody Playlist playlist) {
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<Playlist>> findAllByUserId(@RequestParam String userId) {
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Void> findById(@PathVariable String id) {
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        return ResponseEntity.ok().build();
    }
}
