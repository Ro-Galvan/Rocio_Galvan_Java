package com.tunes.musicstorerecommendations.repository;

import com.tunes.musicstorerecommendations.model.LabelRecommendation;
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
public class LabelRecommendationRepositoryTest {
    @Autowired
    LabelRecommendationRepository repository;

    private LabelRecommendation recommendation;

    @Before
    public void setUp() {
        repository.deleteAll();

        recommendation = new LabelRecommendation(1,1,true);
    }
    @Test
    public void shouldAddAndGetLabelRecommendationFromDatabase() {

        recommendation = repository.save(recommendation);

        LabelRecommendation fromRepo = repository.findById(recommendation.getLabelRecommendationId()).get();
        assertEquals(recommendation, fromRepo);
    }

    @Test
    public void shouldUpdateLabelRecommendationInDatabase() {

        repository.save(recommendation);

        recommendation.setLiked(false);

        recommendation = repository.save(recommendation);

        LabelRecommendation fromRepo = repository.findById(recommendation.getLabelRecommendationId()).get();
        assertEquals(recommendation, fromRepo);
    }

    @Test
    public void shouldDeleteLabelRecommendationFromDatabase() {

        recommendation = repository.save(recommendation);

        repository.deleteById(recommendation.getLabelRecommendationId());

        Optional<LabelRecommendation> fromRepo = repository.findById(recommendation.getLabelRecommendationId());

        assertFalse(fromRepo.isPresent());
    }
}
