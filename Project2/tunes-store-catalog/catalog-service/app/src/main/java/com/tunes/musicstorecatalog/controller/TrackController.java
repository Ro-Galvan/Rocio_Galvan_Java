package com.tunes.musicstorecatalog.controller;

import com.tunes.musicstorecatalog.model.Track;
import com.tunes.musicstorecatalog.repository.TrackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value="/track")
public class TrackController {

    @Autowired
    TrackRepository trackRepository;

//    create a track
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Track createTrack(@RequestBody @Valid Track track){ return  trackRepository.save(track); }

//    Get track by Id
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Optional<Track> getTrackById(@PathVariable Integer id) {
        return trackRepository.findById(id);
    }
//    Get All tracks
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Track> getAllTracks() { return trackRepository.findAll(); }

//    Update track
    @PutMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateTrack(@RequestBody @Valid Track track) { trackRepository.save(track); }

//    Delete track
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTrack(@PathVariable Integer id) { trackRepository.deleteById(id); }
}
