package com.tunes.musicstorecatalog.repository;

import com.tunes.musicstorecatalog.model.Album;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

import static org.junit.Assert.*;

//referenced solved car-lot-repository from week 3 day 2

@RunWith(SpringRunner.class)
@SpringBootTest
public class AlbumRepositoryTest {
    @Autowired
    AlbumRepository repository;

    private Album album;
    private Album album2;

    @Before
    public void setUp() {
        repository.deleteAll();

        album = new Album("21-The Album",1, LocalDate.of(2011,12,01),1, new BigDecimal("19.99"));
//        album2 = new Album("More Life",2, LocalDate.of(2017,03,18),2, new BigDecimal("23.00"));
    }

    @Test
    public void shouldAddAndGetAlbumFromDatabase() {

        album = repository.save(album);

        Album fromRepo = repository.findById(album.getAlbumId()).get();
        assertEquals(album, fromRepo);
    }

    @Test
    public void shouldUpdateAlbumInDatabase() {

        repository.save(album);

        album.setListPrice(new BigDecimal("25.00"));

        album = repository.save(album);

        Album fromRepo = repository.findById(album.getAlbumId()).get();
        assertEquals(album, fromRepo);
    }

    @Test
    public void shouldDeleteAlbumFromDatabase() {

        album = repository.save(album);

        repository.deleteById(album.getAlbumId());

        Optional<Album> fromRepo = repository.findById(album.getAlbumId());

        assertFalse(fromRepo.isPresent());
    }

}