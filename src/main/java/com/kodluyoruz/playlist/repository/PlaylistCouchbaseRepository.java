package com.kodluyoruz.playlist.repository;

import com.couchbase.client.core.deps.com.fasterxml.jackson.databind.ObjectMapper;
import com.couchbase.client.java.Cluster;
import com.couchbase.client.java.Collection;

import static com.couchbase.client.java.kv.LookupInSpec.get;

import com.couchbase.client.java.codec.TypeRef;
import com.couchbase.client.java.kv.GetResult;
import com.couchbase.client.java.kv.LookupInResult;
import com.couchbase.client.java.query.QueryResult;
import com.kodluyoruz.playlist.domain.Playlist;
import com.kodluyoruz.playlist.domain.Track;
import org.springframework.stereotype.Repository;

import javax.swing.tree.RowMapper;
import java.util.*;

import static com.couchbase.client.java.kv.MutateInSpec.*;

@Repository
public class PlaylistCouchbaseRepository implements PlaylistRepository {

    private final Cluster couchbaseCluster;
    private final Collection playlistCollection;


    public PlaylistCouchbaseRepository(Cluster couchbaseCluster, Collection playlistCollection) {
        this.couchbaseCluster = couchbaseCluster;
        this.playlistCollection = playlistCollection;
    }

    @Override
    public String create(Playlist playlist) {
        playlistCollection.insert(playlist.getId(), playlist);
        return playlist.getId();
    }

    @Override
    public void update(Playlist playList) {
        playlistCollection.replace(playList.getId(), playList);
    }

    @Override
    public Playlist findById(String id) {
        GetResult getResult = playlistCollection.get(id);
        return getResult.contentAs(Playlist.class);
    }

    @Override
    public List<Playlist> findAllByUserId(String id) {
        String statement = String.format("Select id, name, description, followersCount, tracks, trackCount, userId from playlist where userId = '%s'", id);
        QueryResult query = couchbaseCluster.query(statement);
        return query.rowsAs(Playlist.class);

    }

    @Override
    public void delete(String id) {
        playlistCollection.remove(id);
    }

    @Override
    public String addTrack(String playlistId, Track track) {
        playlistCollection.mutateIn(playlistId, Arrays.asList(
                arrayAppend("tracks", Collections.singletonList(track)),
                increment("trackCount", 1)
        ));
        return track.getId();
    }

    @Override
    public List<Track> getTracks(String playlistId) {
        LookupInResult result = playlistCollection.lookupIn(
                playlistId,
                Collections.singletonList(get("tracks"))
        );
        return result.contentAs(0, new TypeRef<List<Track>>() {
        });
    }

    @Override
    public void deleteTrack(String playlistId, String trackId) {
        List<Track> tracks = getTracks(playlistId);
        tracks.removeIf(track -> track.getId().equals(trackId));
        playlistCollection.mutateIn(playlistId, Arrays.asList(
                replace("tracks", tracks),
                decrement("trackCount", 1)
        ));
    }
}
