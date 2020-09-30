package com.kodluyoruz.playlist.repository;

import com.couchbase.client.java.Cluster;
import com.couchbase.client.java.Collection;

import static com.couchbase.client.java.kv.LookupInSpec.get;

import com.couchbase.client.java.kv.GetResult;
import com.couchbase.client.java.kv.LookupInResult;
import com.couchbase.client.java.query.QueryResult;
import com.kodluyoruz.playlist.domain.Playlist;
import com.kodluyoruz.playlist.domain.Track;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;

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
    public List getTracks(String playlistId) {
        LookupInResult result = playlistCollection.lookupIn(
                playlistId,
                Collections.singletonList(get("tracks"))
        );
        return result.contentAs(0, List.class);
    }

    @Override
    public void deleteTrack(String playlistId, String trackId) {
        List<LinkedHashMap> tracks = getTracks(playlistId);
        LinkedHashMap result = null;
        for (LinkedHashMap track : tracks) {
            if (track.get("id").equals(trackId)) {
                result = track;
                break;
            }
        }
        tracks.remove(result);
        playlistCollection.mutateIn(playlistId, Arrays.asList(
                upsert("tracks", Collections.singletonList(tracks)),
                decrement("trackCount", 1)
        ));
    }
}
