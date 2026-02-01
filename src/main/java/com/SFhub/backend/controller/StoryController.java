package com.SFhub.backend.controller;

import com.SFhub.backend.entity.Story;
import com.SFhub.backend.repository.StoryRepository;
import org.springframework.web.bind.annotation.*;

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
}
