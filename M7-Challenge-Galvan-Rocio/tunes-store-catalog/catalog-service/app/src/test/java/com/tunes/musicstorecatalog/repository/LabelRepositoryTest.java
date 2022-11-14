package com.tunes.musicstorecatalog.repository;

import com.tunes.musicstorecatalog.model.Label;
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
public class LabelRepositoryTest {
    @Autowired
    LabelRepository repository;

    private Label label;

    @Before
    public void setUp() {
        repository.deleteAll();
        label = new Label("Columbia Records","www.Columbia-Records.com");
    }

    @Test
    public void shouldAddAndGetLabelFromDatabase() {

        label = repository.save(label);

        Label fromRepo = repository.findById(label.getLabelId()).get();
        assertEquals(label, fromRepo);
    }

    @Test
    public void shouldUpdateLabelInDatabase() {

        repository.save(label);

        label.setWebsite("www.Columbia-Records-Label.com");

        label = repository.save(label);

        Label fromRepo = repository.findById(label.getLabelId()).get();
        assertEquals(label, fromRepo);
    }

    @Test
    public void shouldDeleteLabelFromDatabase() {

        label = repository.save(label);

        repository.deleteById(label.getLabelId());

        Optional<Label> fromRepo = repository.findById(label.getLabelId());

        assertFalse(fromRepo.isPresent());
    }
}