package com.tunes.musicstorecatalog.repository;

import com.tunes.musicstorecatalog.model.Track;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TrackRepositoryTest {
    @Autowired
    TrackRepository repository;

    private Track track;

    @Before
    public void setUp() {
        repository.deleteAll();

        track = new Track(1, "Rolling in the Deep", 3);
    }

    @Test
    public void shouldAddAndGetTrackFromDatabase() {
        track = repository.save(track);

        Track fromRepo = repository.findById(track.getTrackId()).get();
        assertEquals(track, fromRepo);
    }

    @Test
    public void shouldUpdateTrackInDatabase() {
        repository.save(track);

        track.setRunTime(4);

        track = repository.save(track);

        Track fromRepo = repository.findById(track.getTrackId()).get();
        assertEquals(track, fromRepo);
    }

    @Test
    public void shouldDeleteTrackFromDatabase() {
        track = repository.save(track);

        repository.deleteById(track.getTrackId());

        Optional<Track> fromRepo = repository.findById(track.getTrackId());

        assertFalse(fromRepo.isPresent());
    }

}