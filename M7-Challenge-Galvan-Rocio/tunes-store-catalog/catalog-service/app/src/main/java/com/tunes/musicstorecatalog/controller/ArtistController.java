package com.tunes.musicstorecatalog.controller;

import com.tunes.musicstorecatalog.model.Artist;
import com.tunes.musicstorecatalog.repository.ArtistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value="/artist")
public class ArtistController {

    @Autowired
    ArtistRepository artistRepository;

//     create an artist
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Artist createArtist(@RequestBody @Valid Artist artist){ return  artistRepository.save(artist); }

//    Get artist by Id
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Optional<Artist> getArtistById(@PathVariable Integer id) {
        return artistRepository.findById(id);
}
//    Get All artists
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Artist> getAllArtists() { return artistRepository.findAll(); }

//    Update artist
    @PutMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateArtist(@RequestBody @Valid Artist artist) { artistRepository.save(artist); }

//    Delete artist
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteArtist(@PathVariable Integer id) { artistRepository.deleteById(id); }
}
