package com.tunes.musicstorerecommendations.controller;

import com.tunes.musicstorerecommendations.model.AlbumRecommendation;
import com.tunes.musicstorerecommendations.repository.AlbumRecommendationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/album-recommendation")
public class AlbumRecommendationController {

    @Autowired
    AlbumRecommendationRepository repository;

    //    create an album recommendation
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AlbumRecommendation createAlbumRecommendation(@RequestBody @Valid AlbumRecommendation albumRecommendation){ return  repository.save(albumRecommendation); }

    //    Get album recommendation by id
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Optional<AlbumRecommendation> getAlbumRecommendationById(@PathVariable Integer id) {
        return repository.findById(id);
    }

    //     Get All album recommendations
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<AlbumRecommendation> getAllAlbumRecommendations() { return repository.findAll(); }

    //     Update album recommendation
    @PutMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateAlbumRecommendation(@RequestBody @Valid AlbumRecommendation albumRecommendation) { repository.save(albumRecommendation); }

    //     Delete album recommendation
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAlbumRecommendation(@PathVariable Integer id) { repository.deleteById(id); }

}
