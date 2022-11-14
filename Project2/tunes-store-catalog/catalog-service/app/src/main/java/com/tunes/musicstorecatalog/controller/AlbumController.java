package com.tunes.musicstorecatalog.controller;

import com.tunes.musicstorecatalog.model.Album;
import com.tunes.musicstorecatalog.repository.AlbumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value="/album")
public class AlbumController {
    @Autowired
    AlbumRepository albumRepository;
//    create an album
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Album createAlbum(@RequestBody @Valid Album album){ return  albumRepository.save(album); }

//    Get album by Id
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Optional<Album> getAlbumById(@PathVariable Integer id) {
        return albumRepository.findById(id);
    }
//     Get All albums
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Album> getAllAlbums() { return albumRepository.findAll(); }

//     Update album
    @PutMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateAlbum(@RequestBody @Valid Album album) { albumRepository.save(album); }

//     Delete album
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAlbum(@PathVariable Integer id) { albumRepository.deleteById(id); }
}
