package com.tunes.musicstorecatalog.repository;

import com.tunes.musicstorecatalog.model.Artist;
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
public class ArtistRepositoryTest {
    @Autowired
    ArtistRepository repository;

    private Artist artist;

    @Before
    public void setUp() {
        repository.deleteAll();

        artist = new Artist("Adele","@BritishAdele","@Adele");
    }

    @Test
    public void shouldAddAndGetArtistFromDatabase() {
        artist = repository.save(artist);

        Artist fromRepo = repository.findById(artist.getArtistId()).get();
        assertEquals(artist, fromRepo);
    }

    @Test
    public void shouldUpdateArtistInDatabase() {
        repository.save(artist);

        artist.setInstagram("@Adele");

        artist = repository.save(artist);

        Artist fromRepo = repository.findById(artist.getArtistId()).get();
        assertEquals(artist, fromRepo);
    }

    @Test
    public void shouldDeleteArtistFromDatabase() {
        artist = repository.save(artist);

        repository.deleteById(artist.getArtistId());

        Optional<Artist> fromRepo = repository.findById(artist.getArtistId());

        assertFalse(fromRepo.isPresent());
    }
}