package com.tunes.musicstorerecommendations.controller;

import com.tunes.musicstorerecommendations.model.LabelRecommendation;
import com.tunes.musicstorerecommendations.repository.LabelRecommendationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/label-recommendation")
public class LabelRecommendationController {
    @Autowired
    LabelRecommendationRepository repository;

    //    create a label recommendation
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public LabelRecommendation createLabelRecommendation(@RequestBody @Valid LabelRecommendation labelRecommendation){ return  repository.save(labelRecommendation); }

    //    Get label recommendation by id
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Optional<LabelRecommendation> getLabelRecommendationById(@PathVariable Integer id) {
        return repository.findById(id);
    }

    //     Get All label recommendations
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<LabelRecommendation> getAllLabelRecommendations() { return repository.findAll(); }

    //     Update label recommendation
    @PutMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateLabelRecommendation(@RequestBody @Valid LabelRecommendation labelRecommendation) { repository.save(labelRecommendation); }

    //     Delete label recommendation
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteLabelRecommendation(@PathVariable Integer id) { repository.deleteById(id); }
}

