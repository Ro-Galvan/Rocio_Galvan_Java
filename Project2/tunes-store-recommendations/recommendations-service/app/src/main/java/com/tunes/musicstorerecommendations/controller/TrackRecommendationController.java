package com.tunes.musicstorerecommendations.controller;

import com.tunes.musicstorerecommendations.model.TrackRecommendation;
import com.tunes.musicstorerecommendations.repository.TrackRecommendationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/track-recommendation")
public class TrackRecommendationController {
    @Autowired
    TrackRecommendationRepository repository;

    //    create a track recommendation
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TrackRecommendation createTrackRecommendation(@RequestBody @Valid TrackRecommendation trackRecommendation){ return  repository.save(trackRecommendation); }

    //    Get track recommendation by id
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Optional<TrackRecommendation> getTrackRecommendationById(@PathVariable Integer id) {
        return repository.findById(id);
    }

    //     Get All track recommendations
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<TrackRecommendation> getAllTrackRecommendations() { return repository.findAll(); }

    //     Update track recommendation
    @PutMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateTrackRecommendation(@RequestBody @Valid TrackRecommendation trackRecommendation) { repository.save(trackRecommendation); }

    //     Delete track recommendation
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTrackRecommendation(@PathVariable Integer id) { repository.deleteById(id); }
}
