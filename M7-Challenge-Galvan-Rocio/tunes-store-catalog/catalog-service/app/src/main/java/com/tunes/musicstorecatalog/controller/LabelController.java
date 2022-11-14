package com.tunes.musicstorecatalog.controller;

import com.tunes.musicstorecatalog.model.Label;
import com.tunes.musicstorecatalog.repository.LabelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value="/label")
public class LabelController {

    @Autowired
    LabelRepository labelRepository;

//   create a label
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Label createLabel(@RequestBody @Valid Label label){ return  labelRepository.save(label); }

//    Get label by Id
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Optional<Label> getLabelById(@PathVariable Integer id) {
        return labelRepository.findById(id);
}

//   Get All labels
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Label> getAllLabels() { return labelRepository.findAll(); }

//   Update label
    @PutMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateLabel(@RequestBody @Valid Label label) { labelRepository.save(label); }

//   Delete label
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteLabel(@PathVariable Integer id) { labelRepository.deleteById(id); }
}
