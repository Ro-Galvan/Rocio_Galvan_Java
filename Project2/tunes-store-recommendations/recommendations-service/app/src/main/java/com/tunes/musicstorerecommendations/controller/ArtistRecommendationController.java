package com.tunes.musicstorerecommendations.controller;

import com.tunes.musicstorerecommendations.model.ArtistRecommendation;
import com.tunes.musicstorerecommendations.repository.ArtistRecommendationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/artist-recommendation")
public class ArtistRecommendationController {
    @Autowired
    ArtistRecommendationRepository repository;

    //    create an artist recommendation
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ArtistRecommendation createArtistRecommendation(@RequestBody @Valid ArtistRecommendation artistRecommendation){ return  repository.save(artistRecommendation); }

    //    Get artist recommendation by id
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Optional<ArtistRecommendation> getArtistRecommendationById(@PathVariable Integer id) {
        return repository.findById(id);
    }

    //     Get All artist recommendations
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ArtistRecommendation> getAllArtistRecommendations() { return repository.findAll(); }

    //     Update artist recommendation
    @PutMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateArtistRecommendation(@RequestBody @Valid ArtistRecommendation artistRecommendation) { repository.save(artistRecommendation); }

    //     Delete artist recommendation
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteArtistRecommendation(@PathVariable Integer id) { repository.deleteById(id); }
}
