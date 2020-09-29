package com.kodluyoruz.playlist.repository;

import com.couchbase.client.java.Cluster;
import com.couchbase.client.java.Collection;
import com.couchbase.client.java.kv.GetResult;
import com.couchbase.client.java.query.QueryResult;
import com.kodluyoruz.playlist.domain.Playlist;
import org.springframework.stereotype.Repository;

import java.util.List;

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
}
