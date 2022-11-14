package com.tunes.musicstorerecommendations.repository;

import com.tunes.musicstorerecommendations.model.TrackRecommendation;
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
public class TrackRecommendationRepositoryTest {
    @Autowired
    TrackRecommendationRepository repository;

    private TrackRecommendation recommendation;

    @Before
    public void setUp() {
        repository.deleteAll();

        recommendation = new TrackRecommendation(1,1,true);
    }
    @Test
    public void shouldAddAndGetTrackRecommendationFromDatabase() {

        recommendation = repository.save(recommendation);

        TrackRecommendation fromRepo = repository.findById(recommendation.getTrackRecommendationId()).get();
        assertEquals(recommendation, fromRepo);
    }

    @Test
    public void shouldUpdateTrackRecommendationInDatabase() {

        repository.save(recommendation);

        recommendation.setLiked(false);

        recommendation = repository.save(recommendation);

        TrackRecommendation fromRepo = repository.findById(recommendation.getTrackRecommendationId()).get();
        assertEquals(recommendation, fromRepo);
    }

    @Test
    public void shouldDeleteTrackRecommendationFromDatabase() {

        recommendation = repository.save(recommendation);

        repository.deleteById(recommendation.getTrackRecommendationId());

        Optional<TrackRecommendation> fromRepo = repository.findById(recommendation.getTrackRecommendationId());

        assertFalse(fromRepo.isPresent());
    }

}
