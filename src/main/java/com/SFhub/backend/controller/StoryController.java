package com.SFhub.backend.controller;

import com.SFhub.backend.DTO.StoryRequestDTO;
import com.SFhub.backend.DTO.StoryResponseDTO;
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

    //  GET ALL STORIES
    @GetMapping
    public List<StoryResponseDTO> getAllStories() {
        return storyRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    // ✅ CREATE STORY
    @PostMapping
    public ResponseEntity<StoryResponseDTO> createStory(
            @Valid @RequestBody StoryRequestDTO dto) {

        Story story = new Story();
        story.setTitle(dto.getTitle());
        story.setContent(dto.getContent());
        story.setAuthorName(dto.getAuthorName());
        story.setLicenseType(dto.getLicenseType());

        Story savedStory = storyRepository.save(story);

        return new ResponseEntity<>(mapToResponse(savedStory), HttpStatus.CREATED);
    }

    //  GET STORY BY ID
    @GetMapping("/{id}")
    public StoryResponseDTO getStoryById(@PathVariable Long id) {

        Story story = storyRepository.findById(id)
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND, "Story not found"));

        return mapToResponse(story);
    }

    //  UPDATE STORY
    @PutMapping("/{id}")
    public ResponseEntity<StoryResponseDTO> updateStory(
            @PathVariable Long id,
            @Valid @RequestBody StoryRequestDTO dto
    ) {

        return storyRepository.findById(id)
                .map(existingStory -> {

                    existingStory.setTitle(dto.getTitle());
                    existingStory.setAuthorName(dto.getAuthorName());
                    existingStory.setContent(dto.getContent());
                    existingStory.setLicenseType(dto.getLicenseType());

                    Story updatedStory = storyRepository.save(existingStory);

                    return ResponseEntity.ok(mapToResponse(updatedStory));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // ✅ DELETE STORY
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStory(@PathVariable Long id) {

        if (!storyRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        storyRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    // ENTITY → RESPONSE DTO MAPPER
    private StoryResponseDTO mapToResponse(Story story) {

        StoryResponseDTO dto = new StoryResponseDTO();

        dto.setId(story.getId());
        dto.setTitle(story.getTitle());
        dto.setContent(story.getContent());
        dto.setAuthorName(story.getAuthorName());
        dto.setLicenseType(story.getLicenseType());
        dto.setIsVerified(story.getIsVerified());
        dto.setCreatedAt(story.getCreatedAt());

        return dto;
    }
}
