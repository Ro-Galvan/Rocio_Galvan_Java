package com.tunes.musicstorerecommendations.repository;

import com.tunes.musicstorerecommendations.model.ArtistRecommendation;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ArtistRecommendationRepositoryTest {

    @Autowired
    ArtistRecommendationRepository repository;
    private ArtistRecommendation recommendation;

    @Before
    public void setUp() {
        repository.deleteAll();

        recommendation = new ArtistRecommendation(1,1,true);
    }
    @Test
    public void shouldAddAndGetArtistRecommendationFromDatabase() {

        recommendation = repository.save(recommendation);

        ArtistRecommendation fromRepo = repository.findById(recommendation.getArtistRecommendationId()).get();
        assertEquals(recommendation, fromRepo);
    }

    @Test
    public void shouldUpdateArtistRecommendationInDatabase() {

        repository.save(recommendation);

        recommendation.setLiked(false);

        recommendation = repository.save(recommendation);

        ArtistRecommendation fromRepo = repository.findById(recommendation.getArtistRecommendationId()).get();
        assertEquals(recommendation, fromRepo);
    }

    @Test
    public void shouldDeleteArtistRecommendationFromDatabase() {

        recommendation = repository.save(recommendation);

        repository.deleteById(recommendation.getArtistRecommendationId());

        Optional<ArtistRecommendation> fromRepo = repository.findById(recommendation.getArtistRecommendationId());

        assertFalse(fromRepo.isPresent());
    }

}
