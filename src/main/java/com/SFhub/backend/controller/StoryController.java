package com.SFhub.backend.controller;

import com.SFhub.backend.entity.Story;
import com.SFhub.backend.repository.StoryRepository;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
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
    @PostMapping
    public Story createStory(@RequestBody Story story) {
    return storyRepository.save(story);
}
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

}
