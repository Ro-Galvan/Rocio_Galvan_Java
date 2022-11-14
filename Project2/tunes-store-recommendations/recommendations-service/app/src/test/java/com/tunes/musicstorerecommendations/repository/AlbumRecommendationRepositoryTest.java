package com.tunes.musicstorerecommendations.repository;

import com.tunes.musicstorerecommendations.model.AlbumRecommendation;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AlbumRecommendationRepositoryTest {

    @Autowired
    AlbumRecommendationRepository repository;

    private AlbumRecommendation recommendation;

    @Before
    public void setUp() {
        repository.deleteAll();

        recommendation = new AlbumRecommendation(1,1,true);
    }
    @Test
    public void shouldAddAndGetAlbumRecommendationFromDatabase() {

        recommendation = repository.save(recommendation);

        AlbumRecommendation fromRepo = repository.findById(recommendation.getAlbumRecommendationId()).get();
        assertEquals(recommendation, fromRepo);
    }

        @Test
        public void shouldUpdateAlbumRecommendationInDatabase() {

            repository.save(recommendation);

            recommendation.setLiked(false);

            recommendation = repository.save(recommendation);

            AlbumRecommendation fromRepo = repository.findById(recommendation.getAlbumRecommendationId()).get();
            assertEquals(recommendation, fromRepo);
    }

    @Test
    public void shouldDeleteAlbumRecommendationFromDatabase() {

        recommendation = repository.save(recommendation);

        repository.deleteById(recommendation.getAlbumRecommendationId());

        Optional<AlbumRecommendation> fromRepo = repository.findById(recommendation.getAlbumRecommendationId());

        assertFalse(fromRepo.isPresent());
    }

}
