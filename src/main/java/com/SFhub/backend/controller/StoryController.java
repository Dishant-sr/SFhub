package com.SFhub.backend.controller;

import com.SFhub.backend.entity.Story;
import com.SFhub.backend.repository.StoryRepository;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/stories")
@CrossOrigin(origins = "*")
public class StoryController {

    private final StoryRepository storyRepository;

    public StoryController(StoryRepository storyRepository) {
        this.storyRepository = storyRepository;
    }

    // GET all stories
    @GetMapping
    public List<Story> getAllStories() {
        return storyRepository.findAll();
    }

    //  CREATE story with validation
    @PostMapping
    public ResponseEntity<Story> createStory(@Valid @RequestBody Story story) {
        Story savedStory = storyRepository.save(story);
        return new ResponseEntity<>(savedStory, HttpStatus.CREATED);
    }

    // GET story by ID
    @GetMapping("/{id}")
    public Story getStoryById(@PathVariable Long id) {
        return storyRepository.findById(id)
                .orElseThrow(() ->
                        new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "Story not found"
                        )
                );
    }

    // UPDATE story with validation
    @PutMapping("/{id}")
    public ResponseEntity<Story> updateStory(
            @PathVariable Long id,
            @Valid @RequestBody Story updatedStory
    ) {

        return storyRepository.findById(id)
                .map(existingStory -> {

                    existingStory.setTitle(updatedStory.getTitle());
                    existingStory.setAuthorName(updatedStory.getAuthorName());
                    existingStory.setContent(updatedStory.getContent());
                    existingStory.setLicenseType(updatedStory.getLicenseType());

                    Story savedStory = storyRepository.save(existingStory);
                    return ResponseEntity.ok(savedStory);

                })
                .orElse(ResponseEntity.notFound().build());
    }

    //  DELETE story
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStory(@PathVariable Long id) {

        if (!storyRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        storyRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
