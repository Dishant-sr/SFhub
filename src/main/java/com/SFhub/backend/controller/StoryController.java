package com.SFhub.backend.controller;

import com.SFhub.backend.entity.Story;
import com.SFhub.backend.repository.StoryRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/stories")
public class StoryController {

    private final StoryRepository storyRepository;

    public StoryController(StoryRepository storyRepository) {
        this.storyRepository = storyRepository;
    }

    // 🔹 Create a story
    @PostMapping
    public Story createStory(@RequestBody Story story) {
        return storyRepository.save(story);
    }

    // 🔹 Get all stories
    @GetMapping
    public List<Story> getAllStories() {
        return storyRepository.findAll();
    }

    // 🔹 Get story by ID
    @GetMapping("/{id}")
    public Story getStoryById(@PathVariable Long id) {
        return storyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Story not found"));
    }
}
