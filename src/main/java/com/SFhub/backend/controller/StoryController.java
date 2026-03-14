package com.SFhub.backend.controller;

import com.SFhub.backend.DTO.StoryRequestDTO;
import com.SFhub.backend.DTO.StoryResponseDTO;
import com.SFhub.backend.service.StoryService;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/stories")
@CrossOrigin(origins = "*")
public class StoryController {

    private final StoryService storyService;

    public StoryController(StoryService storyService) {
        this.storyService = storyService;
    }

    @GetMapping
    public List<StoryResponseDTO> getAllStories() {
        return storyService.getAllStories();
    }

    @PostMapping
    public ResponseEntity<StoryResponseDTO> createStory(
            @Valid @RequestBody StoryRequestDTO dto) {

        return new ResponseEntity<>(storyService.createStory(dto), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public StoryResponseDTO getStoryById(@PathVariable Long id) {
        return storyService.getStoryById(id);
    }

    @PutMapping("/{id}")
    public StoryResponseDTO updateStory(
            @PathVariable Long id,
            @Valid @RequestBody StoryRequestDTO dto) {

        return storyService.updateStory(id, dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStory(@PathVariable Long id) {

        storyService.deleteStory(id);
        return ResponseEntity.noContent().build();
    }
}