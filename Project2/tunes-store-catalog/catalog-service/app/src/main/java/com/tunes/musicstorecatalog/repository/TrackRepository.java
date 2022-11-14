package com.tunes.musicstorecatalog.repository;

import com.tunes.musicstorecatalog.model.Track;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrackRepository extends JpaRepository<Track, Integer> {
}
